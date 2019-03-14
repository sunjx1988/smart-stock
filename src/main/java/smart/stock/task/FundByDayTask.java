package smart.stock.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import smart.stock.dto.FundDto;
import smart.stock.service.FundByDayService;
import smart.stock.service.FundService;

import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/14 0014 14:00
 * @Description: 每日00:01, 基金每日报表, 计算净值, 盈利, 盈利率, 市值, 总金额
 */
@Slf4j
@Component
public class FundByDayTask {

    @Autowired
    private FundService fundService;

    @Autowired
    private FundByDayService fundByDayService;

    @Scheduled(cron = "0 1 0 * * ?")
    public void task(){
        log.info("基金每日报表 -> 开始");
        int success = 0;
        int total = 0;
        int fail = 0;

        //查询基金所属股票上个交易日收盘价,计算总市值
        List<FundDto> list = fundService.list(new FundDto());
        if(!CollectionUtils.isEmpty(list)){
            total = list.size();
            for(FundDto fund: list){
                try {
                    fundByDayService.fundByDay(fund.getId());
                    success ++;
                } catch (Exception e) {
                    log.error("基金每日报表失败,ID={}", fund.getId(), e);
                    fail ++;
                }
            }
        }

        log.info("基金每日报表 -> 结束,结果: 成功 {}, 失败 {} ,总数 {}", success, fail, total);
    }
}
