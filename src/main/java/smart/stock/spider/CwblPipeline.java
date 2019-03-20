package smart.stock.spider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import smart.stock.constant.Constants;

import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 13:41
 * @Description:
 */
@Slf4j
@Component
public class CwblPipeline extends StockFinancePipeline{
    @Override
    protected Constants.FinanceInfoTypes getFinanceInfoType() {
        return Constants.FinanceInfoTypes.CWBL;
    }

}
