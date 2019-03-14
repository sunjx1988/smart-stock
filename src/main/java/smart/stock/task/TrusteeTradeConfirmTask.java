package smart.stock.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.FundDto;
import smart.stock.dto.Options;
import smart.stock.dto.TrusteeTradeDto;
import smart.stock.mapper.TrusteeTradeMapper;
import smart.stock.service.FundService;
import smart.stock.service.TrusteeTradeService;

import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/14 0014 10:03
 * @Description: 每日1点确认信托认购,前置条件是基金净值计算出来了
 */
@Slf4j
@Component
public class TrusteeTradeConfirmTask {

    @Autowired
    private TrusteeTradeService trusteeTradeService;

    @Autowired
    private FundService fundService;

    //每日一点执行
    @Scheduled(cron = "0 0 1 * * ?")
    public void task(){
        log.info("确认今日0点前的认购 -> 开始");
        int success = 0;
        int total = 0;
        int fail = 0;
        //查询今天0点前的未确认认购记录
        TrusteeTradeDto param = new TrusteeTradeDto();
        param.setStatus(Constants.TrusteeTradeStatus.Confirming.getKey());
        List<TrusteeTradeDto> list = trusteeTradeService.list(param);

        if(!CollectionUtils.isEmpty(list)){
            total = list.size();
            for(TrusteeTradeDto dto: list){
                try {
                    trusteeTradeService.confirm(dto.getId());
                    success ++;
                } catch (Exception e) {
                    log.error("确认失败认购失败,ID={}", dto.getId(), e);
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

        log.info("确认今日0点前的认购 -> 结束,结果: 成功 {}, 失败 {} ,总数 {}", success, fail, total);
    }
}
