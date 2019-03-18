package smart.stock.spider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 12:00
 * @Description: 爬取股票财务数据,保存到数据库
 */
@Slf4j
@Component
public abstract class StockFinanceSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(10000);

    protected void run(String url, String code, String date, Pipeline pipeline){
        Spider.create(this)
                .addUrl(String.format(url, code, date))
                .thread(10)
                .addPipeline(pipeline)
                .run();
    }

    @Override
    public Site getSite() {
        return site;
    }

}
