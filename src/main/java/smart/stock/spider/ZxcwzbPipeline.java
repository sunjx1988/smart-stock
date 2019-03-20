package smart.stock.spider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 13:41
 * @Description:
 */
@Slf4j
@Component
public class ZxcwzbPipeline extends StockFinancePipeline{

    @Override
    protected List<String> processCodeAndDate(String url, String urlTemplate) {
        return SpiderUtil.getParamFromTemplate(url, SpiderUrlConst.ZXCWZB);
    }
}
