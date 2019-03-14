package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.Options;
import smart.stock.dto.StockTradeDto;
import smart.stock.dto.TrusteeTradeDto;
import smart.stock.entity.*;
import smart.stock.mapper.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/10 0010 17:17
 * @Description:
 */
@Slf4j
@Service
public class TrusteeTradeService {

    @Autowired
    private TrusteeTradeMapper trusteeTradeMapper;

    @Autowired
    private FundMapper fundMapper;

    @Autowired
    private TrusteeMapper trusteeMapper;

    @Autowired
    private FundByDayMapper fundByDayMapper;

    public List<TrusteeTradeDto> list(TrusteeTradeDto trusteeTradeDto) {
        List<TrusteeTradeDto> list = trusteeTradeMapper.list(trusteeTradeDto);
        if(!CollectionUtils.isEmpty(list)){
            for(TrusteeTradeDto dto: list){
                dto.setStatusText(Constants.TrusteeTradeStatus.getTextByKey(dto.getStatus()));
                dto.setInterestRateText(Constants.InterestRate.getTextByKey(dto.getInterestRate()));
                if(dto.getStatus() == Constants.TrusteeTradeStatus.Sold.getKey()){
                    dto.setIncome(dto.getSaleTotal().subtract(dto.getTotal()));
                    dto.setIncomeRate(dto.getIncome().divide(dto.getTotal(), 2, BigDecimal.ROUND_HALF_UP));
                }
            }
        }
        return list;
    }

    @Transactional
    public Long buy(TrusteeTradeDto trusteeTradeDto) {

        if(null == trusteeTradeDto.getFundId()){
            throw BaseException.error("基金代码不能为空",null);
        }

        if(null == trusteeTradeDto.getTrusteeId()){
            throw BaseException.error("信托人不能为空",null);
        }

        //持有周期
        if(0 == trusteeTradeDto.getCycle()){
            throw BaseException.error("持有周期不能为空",null);
        }

        //利率
        if(0 == trusteeTradeDto.getCycle()){
            throw BaseException.error("利率不能为空",null);
        }

        //金额
        if(null == trusteeTradeDto.getTotal()){
            throw BaseException.error("金额",null);
        }

        if(trusteeTradeDto.getTotal().doubleValue() <= 0){
            throw BaseException.error("金额必须大于0",null);
        }

        Fund fund = fundMapper.selectByPrimaryKey(trusteeTradeDto.getFundId());
        Trustee trustee = trusteeMapper.selectByPrimaryKey(trusteeTradeDto.getTrusteeId());

        //下个交易日确认价格 和 份额
        trusteeTradeDto.setStatus(Constants.TrusteeTradeStatus.Confirming.getKey());
        trusteeTradeDto.setUnit(0);
        trusteeTradeDto.setUnitPrice(BigDecimal.ZERO);

        trusteeTradeDto.setName(trustee.getName());
        trusteeTradeDto.setFundName(fund.getName());
        trusteeTradeDto.setCreateTime(new Date());
        trusteeTradeDto.setStartDate(DateUtils.addDays(DateUtils.truncate(trusteeTradeDto.getCreateTime(), Calendar.DATE),1));
        trusteeTradeDto.setEndDate(DateUtils.addYears(trusteeTradeDto.getStartDate(), trusteeTradeDto.getCycle()));
        trusteeTradeMapper.insert(trusteeTradeDto);
        return trusteeTradeDto.getId();
    }

    //买入确认
    @Transactional
    public TrusteeTradeDto confirm(Long id){
        TrusteeTradeDto trusteeTradeDto = trusteeTradeMapper.findById(id);

        //最新净值
        FundByDay fundByDay = fundByDayMapper.selectByLastDay(trusteeTradeDto.getFundId());
        Fund fund = fundMapper.selectByPrimaryKey(trusteeTradeDto.getFundId());
        Trustee trustee = trusteeMapper.selectByPrimaryKey(trusteeTradeDto.getTrusteeId());

        //初始净值为1元
        if(null != fundByDay){
            trusteeTradeDto.setUnitPrice(fundByDay.getNetUnitValue());
        }else{
            trusteeTradeDto.setUnitPrice(BigDecimal.ONE);
        }

        //份额
        trusteeTradeDto.setUnit(trusteeTradeDto.getTotal().divide(trusteeTradeDto.getUnitPrice(), 2, BigDecimal.ROUND_HALF_UP).intValue());
        //状态为已确认
        trusteeTradeDto.setStatus(Constants.TrusteeTradeStatus.Confirmed.getKey());
        trusteeTradeMapper.updateByPrimaryKey(trusteeTradeDto);

        //更新基金
        //基金变动现金余额
        fund.setBanlance(fund.getBanlance().add(trusteeTradeDto.getTotal()));
        fund.setPrincipal(fund.getPrincipal().add(trusteeTradeDto.getTotal()));
        fund.setTotalUnit(fund.getTotalUnit() + trusteeTradeDto.getUnit());

        if(fund.getPrincipal().compareTo(BigDecimal.ZERO) > 0){
            //重新计算仓位 保留两位,四舍五入
            fund.setPosition(fund.getPrincipal().subtract(fund.getBanlance()).divide(fund.getPrincipal(), 2, BigDecimal.ROUND_HALF_UP));
        }else{
            fund.setPosition(BigDecimal.ZERO);
        }

        fund.setUpdateTime(new Date());
        fundMapper.updateByPrimaryKey(fund);

        //更新信托人
        trustee.setPrincipal(trustee.getPrincipal().add(trusteeTradeDto.getTotal()));
        trustee.setTotalUnit(trustee.getTotalUnit() + trusteeTradeDto.getUnit());
        trusteeMapper.updateByPrimaryKey(trustee);
        return trusteeTradeDto;
    }

    //赎回
    @Transactional
    public TrusteeTradeDto sale(Long id){
        TrusteeTradeDto trusteeTradeDto = trusteeTradeMapper.findById(id);

        //最新净值
        FundByDay fundByDay = fundByDayMapper.selectByLastDay(trusteeTradeDto.getFundId());
        Fund fund = fundMapper.selectByPrimaryKey(trusteeTradeDto.getFundId());
        Trustee trustee = trusteeMapper.selectByPrimaryKey(trusteeTradeDto.getTrusteeId());

        if(null == fundByDay){
            trusteeTradeDto.setSaleUnitPrice(BigDecimal.ONE);
        }else {
            trusteeTradeDto.setSaleUnitPrice(fundByDay.getNetUnitValue());
        }

        // 赎回总金额 = 最新净值 * 份额 + 本金 * 利率 * 年限
        trusteeTradeDto.setSaleTotal(trusteeTradeDto.getSaleUnitPrice().multiply(new BigDecimal(trusteeTradeDto.getUnit())));
        trusteeTradeDto.setSaleTotal(trusteeTradeDto.getSaleTotal()
                .add(trusteeTradeDto.getTotal()
                        .multiply(new BigDecimal(trusteeTradeDto.getInterestRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP))
                        .multiply(new BigDecimal(trusteeTradeDto.getCycle()))));

        //计算盈利 \ 盈利率
        trusteeTradeDto.setIncome(trusteeTradeDto.getSaleTotal().subtract(trusteeTradeDto.getTotal()));
        trusteeTradeDto.setIncomeRate(trusteeTradeDto.getIncome().divide(trusteeTradeDto.getTotal(), 2, BigDecimal.ROUND_HALF_UP));

        //使用现金支付赎回金额
        fund.setBanlance(fund.getBanlance().subtract(trusteeTradeDto.getSaleTotal()));
        fund.setPrincipal(fund.getPrincipal().subtract(trusteeTradeDto.getTotal()));
        fund.setTotalUnit(fund.getTotalUnit() - trusteeTradeDto.getUnit());
        fundMapper.updateByPrimaryKey(fund);

        //信托人本金\份额
        trustee.setTotalUnit(trustee.getTotalUnit() - trusteeTradeDto.getUnit());
        trustee.setPrincipal(trustee.getPrincipal().subtract(trusteeTradeDto.getTotal()));
        trusteeMapper.updateByPrimaryKey(trustee);

        trusteeTradeDto.setStatus(Constants.TrusteeTradeStatus.Sold.getKey());
        trusteeTradeMapper.updateByPrimaryKey(trusteeTradeDto);
        return trusteeTradeDto;
    }

    public List<Options> interestRates() {
        List<Options> list = new ArrayList<>();
        for (Constants.InterestRate rate :Constants.InterestRate.values()){
            list.add(new Options(String.valueOf(rate.getKey()), rate.getText()));
        }
        return list;
    }
}
