package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.Options;
import smart.stock.dto.TrusteeDto;
import smart.stock.mapper.TrusteeMapper;

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

    public List<Options> options() {
        return trusteeMapper.options();
    }

}
