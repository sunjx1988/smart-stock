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
import smart.stock.entity.FundByDay;
import smart.stock.entity.TrusteeTrade;
import smart.stock.mapper.FundByDayMapper;
import smart.stock.mapper.TrusteeByDayMapper;
import smart.stock.mapper.TrusteeMapper;
import smart.stock.mapper.TrusteeTradeMapper;

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


    @Transactional
    public List<TrusteeByDayDto> trusteeByDay(Long trusteeId){
        TrusteeDto trustee = trusteeMapper.selectById(trusteeId);
        TrusteeByDayDto trusteeByDayDto = initByTrustee(trustee);

        Set<Long> fundIdset = trusteeTradeMapper.fundIdsByTrusteeId(trusteeId);

        if(!CollectionUtils.isEmpty(fundIdset)){
            for(Long fundId : fundIdset){
                //净值
                FundByDay fundByDay = fundByDayMapper.selectByLastDay(fundId);
                trusteeByDayDto.setNetUnitValue(fundByDay.getNetUnitValue());
//                trusteeTradeMapper.unitByFundAndTrustee(fundId, trusteeId);
//                trusteeByDayDto.setTotalUnit();
//                trusteeByDayDto.setPrincipal();

                //计算资产
                //计算收益
            }
        }

        //回报率
        return null;
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
