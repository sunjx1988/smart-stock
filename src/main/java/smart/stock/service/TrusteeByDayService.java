package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.TrusteeByDayDto;
import smart.stock.dto.TrusteeDto;
import smart.stock.dto.TrusteeTradeDto;
import smart.stock.entity.Fund;
import smart.stock.entity.FundByDay;
import smart.stock.entity.TrusteeTrade;
import smart.stock.mapper.*;
import smart.stock.util.DateUtil;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Auther: sunjx
 * @Date: 2019/3/17 0017 14:09
 * @Description:
 */
@Slf4j
@Service
public class TrusteeByDayService {

    @Autowired
    private TrusteeByDayMapper trusteeByDayMapper;

    @Autowired
    private TrusteeMapper trusteeMapper;

    @Autowired
    private FundByDayMapper fundByDayMapper;

    @Autowired
    private TrusteeTradeMapper trusteeTradeMapper;

    @Autowired
    private FundMapper fundMapper;

    @Transactional
    public List<TrusteeByDayDto> trusteeByDay(Long trusteeId){
        TrusteeDto trustee = trusteeMapper.selectById(trusteeId);
        TrusteeByDayDto trusteeByDayDto = initByTrustee(trustee);
        List<TrusteeByDayDto> list = new ArrayList<>();

        Set<Long> fundIdset = trusteeTradeMapper.fundIdsByTrusteeId(trusteeId);

        if(!CollectionUtils.isEmpty(fundIdset)){
            for(Long fundId : fundIdset){
                //净值
                FundByDay fundByDay = fundByDayMapper.selectByLastDay(fundId);
                Fund fund = fundMapper.selectByPrimaryKey(fundId);
                trusteeByDayDto.setFundId(fundId);
                trusteeByDayDto.setFundName(fund.getName());
                trusteeByDayDto.setTotalUnit(0);
                trusteeByDayDto.setPrincipal(BigDecimal.ZERO);
                trusteeByDayDto.setTotal(BigDecimal.ZERO);
                //计算收益, 收益率
                trusteeByDayDto.setIncome(BigDecimal.ZERO);
                trusteeByDayDto.setRateOfReturn(BigDecimal.ZERO);

                if(null != fundByDay){
                    trusteeByDayDto.setNetUnitValue(fundByDay.getNetUnitValue());
                }else{
                    trusteeByDayDto.setNetUnitValue(BigDecimal.ONE);
                }

                //总份额, 总本金, 总资产
                TrusteeTradeDto sumUnitAndTotal = trusteeTradeMapper.sumUnitAndTotal(fundId, trusteeId);

                if(null != sumUnitAndTotal){
                    trusteeByDayDto.setTotalUnit(sumUnitAndTotal.getUnit());
                    trusteeByDayDto.setPrincipal(sumUnitAndTotal.getTotal());

                    //认购记录
                    TrusteeTradeDto trusteeTradeParam = new TrusteeTradeDto();
                    trusteeTradeParam.setParamTrusteeId(trusteeId);
                    trusteeTradeParam.setParamStatus(Constants.TrusteeTradeStatus.Confirmed.getKey());
                    trusteeTradeParam.setParamEndDateStart(new Date());
                    List<TrusteeTradeDto> trusteeTradeRecord = trusteeTradeMapper.list(trusteeTradeParam);

                    if(!CollectionUtils.isEmpty(trusteeTradeRecord)){
                        for(TrusteeTradeDto tt : trusteeTradeRecord){
                            //锁定已满年数
                            int year = DateUtil.floorDiffYear(new Date(), tt.getStartDate());

                            //基金的分红率
                            BigDecimal fundShareRate = trusteeByDayDto.getNetUnitValue()
                                    .subtract(BigDecimal.ONE)
                                    .subtract(new BigDecimal(tt.getInterestRate() * year))
                                    .multiply(new BigDecimal(0.6));

                            //如果净值小于1,基金无分红
                            if(trusteeByDayDto.getNetUnitValue().compareTo(BigDecimal.ONE) < 0){
                                fundShareRate = BigDecimal.ZERO;
                            }

                            //总资产需要扣除基金分红

                            //基金的分红率 = (基金净值 - 1 - (利息率 * 锁定已满年数)) * 0.6
                            //基金净值 - 1 表示基金盈利率
                            //盈利率 - (利息率 * 锁定已满年数) 表示基金扣除利息部分后的盈利率
                            //基金扣除利息部分后的盈利率 * 0.6 表示盈利扣除利息率后,基金的分红率

                            //如果基金分红率 <= 0, 则信托人净值 = 1 + (利息率 * 锁定已满年数)

                            //累加 (扣除基金分红后的净值 * 份额) 得到总资产
                            trusteeByDayDto.setTotal(trusteeByDayDto.getTotal().add(new BigDecimal(tt.getUnit()).multiply(trusteeByDayDto.getNetUnitValue().subtract(fundShareRate))));
                        }
                    }

                    //计算收益, 收益率
                    trusteeByDayDto.setIncome(trusteeByDayDto.getTotal().subtract(trusteeByDayDto.getPrincipal()));
                    trusteeByDayDto.setRateOfReturn(trusteeByDayDto.getIncome().divide(trusteeByDayDto.getPrincipal(), 3));
                }

                trusteeByDayMapper.insert(trusteeByDayDto);
                list.add(trusteeByDayDto);
            }
        }

        return list;
    }

    private TrusteeByDayDto initByTrustee(TrusteeDto trustee) {
        TrusteeByDayDto trusteeByDayDto = new TrusteeByDayDto();
        trusteeByDayDto.setTrusteeId(trustee.getId());
        trusteeByDayDto.setName(trustee.getName());
        trusteeByDayDto.setCreateTime(new Date());
        trusteeByDayDto.setDate(DateFormatUtils.format(trusteeByDayDto.getCreateTime(), "yyyyMMdd"));
        return trusteeByDayDto;
    }

    public List<TrusteeByDayDto> list(TrusteeByDayDto trusteeByDayDto) {
        return trusteeByDayMapper.list(trusteeByDayDto);
    }
}
