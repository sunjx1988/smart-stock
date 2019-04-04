package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.stock.dto.FundDto;
import smart.stock.dto.Options;
import smart.stock.entity.Fund;
import smart.stock.mapper.FundMapper;
import smart.stock.mapper.TrusteeMapper;

import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/10 0010 15:35
 * @Description:
 */
@Slf4j
@Service
public class FundService {

    @Autowired
    private FundMapper fundMapper;

    @Autowired
    private TrusteeMapper trusteeMapper;

    public FundDto detail(Long id) {
        FundDto fundDto = fundMapper.detail(id);
        return fundDto;
    }

    @Transactional
    public Long save(Fund fund) {
        return null;
    }

    public List<Options> options() {
        return fundMapper.options();
    }

    public List<FundDto> list(FundDto param){
        return fundMapper.list(param);
    }

    @Transactional
    public void trusteeNum(Long fundId) {
        //查询份额不为0的信托人数
        int trusteeNum = trusteeMapper.trusteeNum();
        Fund fund = fundMapper.selectByPrimaryKey(fundId);
        fund.setNumOfTrustee(trusteeNum);
        fundMapper.updateByPrimaryKey(fund);
    }
}
