package smart.stock.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: sunjx
 * @Date: 2019/3/14 0014 14:40
 * @Description: 每天18点执行,收盘价定时任务
 */
@Slf4j
@Component
public class StockPriceTask {

    @Scheduled(cron = "0 0 18 * * ?")
    public void task(){

    }
}
