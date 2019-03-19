package smart.stock.spider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
public class ZxcwzbSpider extends StockFinanceSpider {

    @Autowired
    private ZxcwzbPipeline zxcwzbPipeline;

    @Override
    public void process(Page page) {
        Selectable tbody = page.getHtml().$("div#zaiyaocontent table.web2 tbody");
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
    public void zxcwzb(String code, String year, int type){

        if(type > 0){
            run(SpiderUrlConst.ZXCWZB, code, year + Constants.FinanceDateTypes.getTextByKey(type), zxcwzbPipeline);
        }else{
            //时期类型小于等于0查全部
            for(Constants.FinanceDateTypes value : Constants.FinanceDateTypes.values()){
                run(SpiderUrlConst.ZXCWZB, code, year + value.getText(), zxcwzbPipeline);
            }
        }
    }
}
