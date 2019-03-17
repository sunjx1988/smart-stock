package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import smart.stock.dto.TrusteeByDayDto;
import smart.stock.dto.TrusteeDto;
import smart.stock.dto.TrusteeTradeDto;
import smart.stock.entity.Fund;
import smart.stock.entity.FundByDay;
import smart.stock.entity.TrusteeTrade;
import smart.stock.mapper.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
                    trusteeByDayDto.setTotal(trusteeByDayDto.getNetUnitValue().multiply(new BigDecimal(trusteeByDayDto.getTotalUnit())));
                    //计算收益, 收益率
                    trusteeByDayDto.setIncome(trusteeByDayDto.getTotal().subtract(trusteeByDayDto.getPrincipal()));
                    trusteeByDayDto.setRateOfReturn(trusteeByDayDto.getIncome().divide(trusteeByDayDto.getPrincipal(), 3, BigDecimal.ROUND_HALF_UP));
                }else{
                    trusteeByDayDto.setTotalUnit(0);
                    trusteeByDayDto.setPrincipal(BigDecimal.ZERO);
                    trusteeByDayDto.setTotal(BigDecimal.ZERO);
                    //计算收益, 收益率
                    trusteeByDayDto.setIncome(BigDecimal.ZERO);
                    trusteeByDayDto.setRateOfReturn(BigDecimal.ZERO);
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
