package smart.stock.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import smart.stock.dto.TrusteeDto;
import smart.stock.service.TrusteeByDayService;
import smart.stock.service.TrusteeService;

import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/17 0017 14:07
 * @Description: 信托人报表 盈利, 回报率
 */
@Slf4j
@Component
public class TrusteeByDayTask {

    @Autowired
    private TrusteeByDayService trusteeByDayService;

    @Autowired
    private TrusteeService trusteeService;

    @Scheduled(cron = "0 1 0 * * ?")
    public void task(){
        log.info("信托人报表 -> 开始");
        int success = 0;
        int total = 0;
        int fail = 0;

        //查询基金所属股票上个交易日收盘价,计算总市值
        List<TrusteeDto> list = trusteeService.list(new TrusteeDto());
        if(!CollectionUtils.isEmpty(list)){
            total = list.size();
            for(TrusteeDto trustee: list){
                try {
                    trusteeByDayService.trusteeByDay(trustee.getId());
                    success ++;
                } catch (Exception e) {
                    log.error("信托人报表,ID={}", trustee.getId(), e);
                    fail ++;
                }
            }
        }

        log.info("信托人报表 -> 结束,结果: 成功 {}, 失败 {} ,总数 {}", success, fail, total);
    }
}
