package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.stock.dto.FundDto;
import smart.stock.entity.Fund;
import smart.stock.mapper.FundMapper;

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

    public FundDto detail(Long id) {
        FundDto fundDto = fundMapper.detail(id);
        return fundDto;
    }

    public Long save(Fund fund) {
        return null;
    }
}
