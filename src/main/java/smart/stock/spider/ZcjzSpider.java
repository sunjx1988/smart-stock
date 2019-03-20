package smart.stock.spider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smart.stock.constant.Constants;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 15:15
 * @Description:
 */
@Slf4j
@Component
public class ZcjzSpider extends StockFinanceSpider {

    @Autowired
    private ZcjzPipeline zcjzPipeline;


    @Override
    protected StockFinancePipeline getStockFinancePipeline() {
        return zcjzPipeline;
    }

    @Override
    protected Constants.FinanceInfoTypes getFinanceInfoTypes() {
        return Constants.FinanceInfoTypes.ZCJZ;
    }
}
