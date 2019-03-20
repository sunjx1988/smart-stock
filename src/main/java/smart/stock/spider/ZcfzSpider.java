package smart.stock.spider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smart.stock.constant.Constants;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 15:15
 * @Description:
 */
@Slf4j
@Component
public class ZcfzSpider extends StockFinanceSpider {

    @Autowired
    private ZcfzPipeline zcfzPipeline;

    @Override
    protected StockFinancePipeline getStockFinancePipeline() {
        return zcfzPipeline;
    }

    @Override
    protected Constants.FinanceInfoTypes getFinanceInfoTypes() {
        return Constants.FinanceInfoTypes.ZCFZ;
    }

}
