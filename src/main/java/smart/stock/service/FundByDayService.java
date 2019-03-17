package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.FundByDayDto;
import smart.stock.dto.FundDto;
import smart.stock.dto.FundStockDto;
import smart.stock.entity.Fund;
import smart.stock.entity.FundByDay;
import smart.stock.entity.StockPrice;
import smart.stock.mapper.FundByDayMapper;
import smart.stock.mapper.FundMapper;
import smart.stock.mapper.FundStockMapper;
import smart.stock.mapper.StockPriceMapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/14 0014 14:46
 * @Description:
 */
@Slf4j
@Service
public class FundByDayService {

    @Autowired
    private StockPriceMapper stockPriceMapper;

    @Autowired
    private FundStockMapper fundStockMapper;

    @Autowired
    private FundMapper fundMapper;

    @Autowired
    private FundByDayMapper fundByDayMapper;

    @Transactional
    public FundByDay fundByDay(Long fundId){
        FundDto fund = fundMapper.detail(fundId);
        FundByDay fundByDay = initFundByDay(fund);

        //查询基金投入的股票
        FundStockDto fundStockParam = new FundStockDto();
        fundStockParam.setParamFundId(fundId);
        fundStockParam.setParamStatus(Constants.FundStockStatus.Holding.getKey());
        List<FundStockDto> fundStockDtoList = fundStockMapper.list(fundStockParam);

        if(!CollectionUtils.isEmpty(fundStockDtoList)){
            for(FundStockDto fundStock: fundStockDtoList){
                //查询最新价格
                StockPrice lastPrice = stockPriceMapper.getLastPrice(fundStock.getCode());
                if(null != lastPrice){
                    //更新基金持有股票的股价,总市值
                    fundStock.setNowUnitPrice(lastPrice.getPrice());
                    fundStock.setNowTotal(lastPrice.getPrice().multiply(new BigDecimal(fundStock.getUnit())));
                    fundStockMapper.updateByPrimaryKey(fundStock);

                    //每日报表 市值 , 总资产
                    fundByDay.setMarketValue(fundByDay.getMarketValue().add(fundStock.getNowTotal()));
                    fundByDay.setTotal(fundByDay.getTotal().add(fundStock.getNowTotal()));
                }
            }

            //计算净值
            fundByDay.setNetUnitValue(fundByDay.getTotal().divide(new BigDecimal(fundByDay.getTotalUnit()), 2, BigDecimal.ROUND_HALF_UP));
            //计算盈利
            fundByDay.setIncome(fundByDay.getTotal().subtract(fundByDay.getPrincipal()));
            //计算盈利率
            fundByDay.setRateOfReturn(fundByDay.getIncome().divide(fundByDay.getPrincipal(), 2, BigDecimal.ROUND_HALF_UP));
        }

        fundByDayMapper.insert(fundByDay);

        return fundByDay;
    }

    public List<FundByDayDto> list(FundByDayDto param){
        return fundByDayMapper.list(param);
    }

    private FundByDay initFundByDay(Fund fund){
        FundByDay fundByDay = new FundByDay();
        fundByDay.setCreateTime(new Date());
        fundByDay.setDate(DateFormatUtils.format(fundByDay.getCreateTime(),"yyyyMMdd"));
        fundByDay.setFundId(fund.getId());
        fundByDay.setName(fund.getName());
        fundByDay.setPosition(fund.getPosition());
        fundByDay.setPrincipal(fund.getPrincipal());
        fundByDay.setBanlance(fund.getBanlance());
        fundByDay.setTotalUnit(fund.getTotalUnit());
        fundByDay.setMarketValue(BigDecimal.ZERO);
        fundByDay.setTotal(fund.getBanlance());
        fundByDay.setIncome(BigDecimal.ZERO);
        fundByDay.setNetUnitValue(BigDecimal.ZERO);
        fundByDay.setRateOfReturn(BigDecimal.ZERO);
        fundByDay.setNumOfTrustee(fund.getNumOfTrustee());
        return fundByDay;
    }
}
