package smart.stock.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.FundDto;
import smart.stock.dto.TrusteeTradeDto;
import smart.stock.service.FundService;
import smart.stock.service.TrusteeTradeService;

import java.util.Date;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/14 0014 10:27
 * @Description: 每日2点清算到期赎回,并邮件通知管理员和信托人
 */
@Slf4j
@Component
public class TrusteeTradeAutoSaleTask {

    @Autowired
    private TrusteeTradeService trusteeTradeService;

    @Autowired
    private FundService fundService;

    @Scheduled(cron = "0 0 2 * * ?")
    public void task(){
        log.info("清算到期赎回 -> 开始");
        int success = 0;
        int total = 0;
        int fail = 0;
        //查询今天0点前的未确认认购记录
        TrusteeTradeDto param = new TrusteeTradeDto();
        param.setParamEndDateEnd(new Date());
        param.setStatus(Constants.TrusteeTradeStatus.Confirmed.getKey());
        List<TrusteeTradeDto> list = trusteeTradeService.list(param);

        if(!CollectionUtils.isEmpty(list)){
            total = list.size();
            for(TrusteeTradeDto dto: list){
                try {
                    trusteeTradeService.sale(dto.getId());
                    success ++;
                } catch (Exception e) {
                    log.error("清算到期赎回失败,ID={}", dto.getId(), e);
                    fail ++;
                }
            }

            List<FundDto> funds = fundService.list(null);
            if(!CollectionUtils.isEmpty(funds)){
                for(FundDto fund: funds){
                    //计算信托人数
                    fundService.trusteeNum(fund.getId());
                }
            }
        }
        log.info("清算到期赎回 -> 结束,结果: 成功 {}, 失败 {} ,总数 {}", success, fail, total);

    }
}
