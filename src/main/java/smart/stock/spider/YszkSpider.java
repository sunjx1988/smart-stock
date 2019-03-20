package smart.stock.spider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smart.stock.constant.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 15:15
 * @Description:
 */
@Slf4j
@Component
public class YszkSpider extends StockFinanceSpider {

    @Autowired
    private YszkPipeline yszkPipeline;

    @Override
    protected StockFinancePipeline getStockFinancePipeline() {
        return yszkPipeline;
    }

    @Override
    protected Constants.FinanceInfoTypes getFinanceInfoTypes() {
        return Constants.FinanceInfoTypes.YSZK;
    }

    @Override
    protected Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Via-JSL","63469a6,-");
        return headers;
    }
}
