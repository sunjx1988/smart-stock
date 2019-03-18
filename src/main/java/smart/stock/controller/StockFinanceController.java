package smart.stock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import smart.stock.dto.StockFinanceDto;
import smart.stock.service.StockFinanceService;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 16:55
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/stock_finance")
public class StockFinanceController {

    @Autowired
    private StockFinanceService stockFinanceService;

    @ResponseBody
    @RequestMapping(value = "finance_spider", method = RequestMethod.POST)
    public BaseResult financeSpider(StockFinanceDto param){
        stockFinanceService.financeSpider(param);
        return BaseResult.success(null);
    }
}
