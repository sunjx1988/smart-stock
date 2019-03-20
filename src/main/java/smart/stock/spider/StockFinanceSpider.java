package smart.stock.spider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import smart.stock.constant.Constants;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.Map;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 12:00
 * @Description: 爬取股票财务数据,保存到数据库
 */
@Slf4j
@Component
public abstract class StockFinanceSpider implements PageProcessor {

    private Site site = Site.me()
            .addHeader("X-AspNet-Version","4.0.30319")
            .addHeader("X-Via-JSL","5e011f7,-")
            .addHeader("X-Cache","bypass")
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setTimeOut(10000);

    @Override
    public void process(Page page) {
        Selectable tbody = page.getHtml().$("div#zaiyaocontent table tbody");
        if(null != tbody){
            Selectable trListSelectable = tbody.$("tr");
            if(null != trListSelectable){
                List<Selectable> trList = trListSelectable.nodes();
                if(!CollectionUtils.isEmpty(trList)){
                    for(Selectable tr: trList){
                        page.putField(tr.$("td.dotborder div.tishi").xpath("//strong/text()").toString(), tr.$("td:eq(1)").xpath("//div[@class='tishi']/text()").toString());
                    }
                }
            }
        }
    }

    //最新财务指标
    @Async
    public void fetch(String code, String year, int type){

        if(type > 0){
            run(getFinanceInfoTypes().getUrl(), code, year + Constants.FinanceDateTypes.getTextByKey(type), getStockFinancePipeline());
        }else{
            //时期类型小于等于0查全部
            for(Constants.FinanceDateTypes value : Constants.FinanceDateTypes.values()){
                run(getFinanceInfoTypes().getUrl(), code, year + value.getText(), getStockFinancePipeline());
            }
        }
    }

    protected abstract StockFinancePipeline getStockFinancePipeline();

    protected abstract Constants.FinanceInfoTypes getFinanceInfoTypes();

    protected Map<String, String> getHeaders(){
        return null;
    }

    private void run(String url, String code, String date, Pipeline pipeline){
        Map<String, String> headers = getHeaders();
        if(!MapUtils.isEmpty(headers)){
            for(Map.Entry<String, String> entry: headers.entrySet()){
                getSite().addHeader(entry.getKey(), entry.getValue());
            }
        }

        Spider.create(this)
                .addUrl(String.format(url, code, date))
                .thread(5)
                .addPipeline(pipeline)
                .run();
    }

    @Override
    public Site getSite() {
        return site;
    }

}
