package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import smart.stock.constant.Constants;
import smart.stock.dto.StockFinanceDto;
import smart.stock.mapper.StockFinanceMapper;
import smart.stock.spider.ZxcwzbSpider;

import java.util.Date;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 14:46
 * @Description:
 */
@Slf4j
@Service
public class StockFinanceService {

    @Autowired
    private StockFinanceMapper stockFinanceMapper;

    @Autowired
    private ZxcwzbSpider zxcwzbSpider;

    @Transactional
    public void financeSpider(StockFinanceDto param){
        if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.ZXCWZB.getKey())){
            zxcwzbSpider.zxcwzb(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }

        //TODO 其他财务数据
    }

    @Transactional
    public StockFinanceDto save(StockFinanceDto stockFinance){
        stockFinance.setCreateTime(new Date());
        stockFinanceMapper.insert(stockFinance);
        return stockFinance;
    }

    @Transactional
    public Integer batchSave(List<StockFinanceDto> list){
        if(!CollectionUtils.isEmpty(list)){
            for(StockFinanceDto dto: list){
                dto.setCreateTime(new Date());
            }
            return stockFinanceMapper.insertBatch(list);
        }
        return 0;
    }
}
