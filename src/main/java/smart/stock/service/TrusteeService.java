package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.TrusteeDto;
import smart.stock.entity.Trustee;
import smart.stock.mapper.TrusteeMapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/2/28 0028 15:52
 * @Description:
 */
@Slf4j
@Service
public class TrusteeService {

    @Autowired
    private TrusteeMapper trusteeMapper;

    public List<TrusteeDto> list(){
        List<TrusteeDto> list = trusteeMapper.list();
        if(CollectionUtils.isEmpty(list)){
           return null;
        }

        for(TrusteeDto dto: list){
            dto.setStatusText(Constants.TrusteeStatus.getTextByKey(dto.getStatus()));
        }

        return list;
    }

    public TrusteeDto detail(Long id) {
        TrusteeDto dto = trusteeMapper.selectById(id);
        dto.setStatusText(Constants.TrusteeStatus.getTextByKey(dto.getStatus()));
        return dto;
    }

    public Long save(Trustee trustee) {
        //名字不能为空
        if(StringUtils.isEmpty(trustee.getName())){
            throw BaseException.error("名字不能为空",null);
        }

        //与其他人名字相同
        if(trusteeMapper.countByNameAndId(trustee) > 0){
            throw BaseException.error("不能与他人名字相同",null);
        }

        trustee.setPrincipal(BigDecimal.ZERO);
        trustee.setTotal(BigDecimal.ZERO);
        trustee.setTotalUnit(0);
        trustee.setStatus(Constants.TrusteeStatus.Disabled.getKey());

        trusteeMapper.insert(trustee);
        return trustee.getId();
    }
}
