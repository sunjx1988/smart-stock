package smart.stock.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import smart.stock.constant.Constants;
import smart.stock.dto.*;
import smart.stock.entity.Stock;
import smart.stock.entity.StockPrice;
import smart.stock.mapper.FundStockMapper;
import smart.stock.mapper.StockMapper;
import smart.stock.mapper.StockPriceMapper;
import smart.stock.service.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/14 0014 14:40
 * @Description:
 * 每天18点执行
 * 收盘价定时任务
 * 基金每日报表, 计算净值, 盈利, 盈利率, 市值, 总金额 , 当天认购(赎回)的,由于系统是第二天确认, 所以数据计入第二日的报表
 * 信托人报表 盈利, 回报率
 * 确认信托认购,前置条件是基金净值计算出来了
 * 清算到期赎回,并邮件通知管理员和信托人
 */
@Slf4j
@Component
public class MainTask {

    private static final String STOCK_PRICE_API_URL = "http://quote.tool.hexun.com/hqzx/quote.aspx?type=2&market=0&sorttype=0&updown=down&page=1&count=5000&time=";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockPriceMapper stockPriceMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private FundService fundService;

    @Autowired
    private FundByDayService fundByDayService;

    @Autowired
    private TrusteeByDayService trusteeByDayService;

    @Autowired
    private TrusteeService trusteeService;

    @Autowired
    private TrusteeTradeService trusteeTradeService;

    @Autowired
    private FundStockMapper fundStockMapper;


    //周六日两天无交易,所以这两天不执行收盘价任务,和报表统计
    @Scheduled(cron = "0 0 18 * * ?")
//    @Scheduled(cron = "0 */1 * * * ?")
    public void task(){
        try {
            //收盘价任务不能失败,后续任务运行失败,不能影响此任务的成功
            stockPriceTask();
            try {
                //基金净值计算不能失败,否则影响后续任务计算,后续任务运行失败,不能影响此任务的成功
                fundByDayTask();
                try {
                    trusteeByDayTask();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("信托人报表任务失败", e);
                }

                try {
                    trusteeTradeConfirmTask();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("信托人认购确认任务失败", e);
                }

                try {
                    trusteeTradeAutoSaleTask();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("信托人到期自动赎回任务失败", e);
                }

            } catch (Exception e) {
                log.error("基金报表任务失败", e);
            }
        } catch (Exception e) {
            log.error("收盘价任务失败", e);
        }
    }

    @Transactional
    public void stockPriceTask(){
        log.info("收盘价定时任务 -> 开始");
        int total = 0;

        //查询基金持有股票
        List<FundStockDto> fundStocks = fundStockMapper.list(new FundStockDto());

        if(!CollectionUtils.isEmpty(fundStocks)){
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(STOCK_PRICE_API_URL, String.class);
            String responseEntityBody = responseEntity.getBody();
            String[] stockLines = responseEntityBody.split("\\[\r\n\\[")[1].split("\\]\\]")[0].split("\\]\\,\r\n\\[");
            List<StockPrice> list = new ArrayList<>();

            Date now = new Date();
            String nowString = DateFormatUtils.format(now, "yyyyMMdd");

            for(String line : stockLines){
                // '代码','名称',收盘价,涨幅(%),昨收,今开,最高,最低,成交量(手),成交额(元),换手率(%),振幅(%),量比
                String[] stockFeildArray = line.split("\\,");

                //只记录fundstock表中的股票股价
                for(FundStockDto dto: fundStocks){
                    if(dto.getCode().equals(stockFeildArray[0].split("'")[1])){
                        StockPrice stockPrice = new StockPrice();
                        stockPrice.setCode(stockFeildArray[0].split("'")[1]);
                        stockPrice.setName(stockFeildArray[1].split("'")[1]);
                        stockPrice.setPrice(new BigDecimal(stockFeildArray[2]));
                        stockPrice.setCreateTime(now);
                        stockPrice.setDate(nowString);
                        list.add(stockPrice);
                        break ;
                    }
                }
            }

            if(list.size() > 0){
                total = stockPriceMapper.insertBatch(list);
            }
        }

        log.info("收盘价定时任务 -> 结束,结果: 成功{}", total);
    }

    @Transactional
    public void fundByDayTask(){
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

    @Transactional
    public void trusteeByDayTask(){
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

    @Transactional
    public void trusteeTradeConfirmTask(){
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

    @Transactional
    public void trusteeTradeAutoSaleTask(){
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
