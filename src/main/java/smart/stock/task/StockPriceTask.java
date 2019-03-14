package smart.stock.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import smart.stock.entity.StockPrice;
import smart.stock.mapper.StockPriceMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/14 0014 14:40
 * @Description: 每天18点执行,收盘价定时任务
 */
@Slf4j
@Component
public class StockPriceTask {

    private static final String STOCK_PRICE_API_URL = "http://quote.tool.hexun.com/hqzx/quote.aspx?type=2&market=0&sorttype=0&updown=down&page=1&count=5000&time=";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockPriceMapper stockPriceMapper;

    @Scheduled(cron = "0 0 18 * * ?")
    public void task(){
        log.info("收盘价定时任务 -> 开始");

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(STOCK_PRICE_API_URL, String.class);
        String responseEntityBody = responseEntity.getBody();
        String[] stockLines = responseEntityBody.split("\\[\r\n\\[")[1].split("\\]\\]")[0].split("\\]\\,\r\n\\[");
        List<StockPrice> list = new ArrayList<>();

        Date now = new Date();
        String nowString = DateFormatUtils.format(now, "yyyyMMdd");

        for(String line : stockLines){
            String[] stockFeildArray = line.split("\\,");
            // '代码','名称',收盘价,涨幅(%),昨收,今开,最高,最低,成交量(手),成交额(元),换手率(%),振幅(%),量比
            StockPrice stockPrice = new StockPrice();
            stockPrice.setCode(stockFeildArray[0].split("'")[1]);
            stockPrice.setName(stockFeildArray[1].split("'")[1]);
            stockPrice.setPrice(new BigDecimal(stockFeildArray[2]));
            stockPrice.setCreateTime(now);
            stockPrice.setDate(nowString);
            list.add(stockPrice);
        }

        int total = stockPriceMapper.insertBatch(list);

        log.info("收盘价定时任务 -> 结束,结果: 成功{}", total);
    }
}
