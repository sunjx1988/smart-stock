package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.stock.dto.Options;
import smart.stock.dto.StockDto;
import smart.stock.entity.Stock;
import smart.stock.mapper.StockMapper;

import java.util.Date;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/10 0010 17:17
 * @Description:
 */
@Slf4j
@Service
public class StockService {

    @Autowired
    private StockMapper stockMapper;

    public List<StockDto> list(StockDto stockDto) {
        return stockMapper.list(stockDto);
    }

    public StockDto detail(Long id) {
        StockDto stockDto = stockMapper.detail(id);
        return stockDto;
    }

    @Transactional
    public Long save(Stock stock) {
        //名字不能为空
        if(StringUtils.isEmpty(stock.getName())){
            throw BaseException.error("名字不能为空",null);
        }

        //代码不能为空
        if(StringUtils.isEmpty(stock.getCode())){
            throw BaseException.error("代码不能为空",null);
        }

        if(null != stock.getId()){
            stockMapper.updateByPrimaryKey(stock);
        }else{
            stock.setCreateTime(new Date());
            stockMapper.insert(stock);
        }
        return stock.getId();
    }

    public List<Options> options() {
        return stockMapper.options();
    }
}
