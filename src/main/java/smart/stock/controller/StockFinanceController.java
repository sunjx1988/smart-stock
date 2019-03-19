package smart.stock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smart.stock.constant.Constants;
import smart.stock.dto.Options;
import smart.stock.dto.StockFinanceDto;
import smart.stock.service.StockFinanceService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 16:55
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/stock_finance")
public class StockFinanceController {

    private static final String ZXCWZB_CHART = "stock/zxcwzb_chart";

    @Autowired
    private StockFinanceService stockFinanceService;

    @ResponseBody
    @RequestMapping(value = "admin/finance_fetch", method = RequestMethod.POST)
    public BaseResult financeFetch(StockFinanceDto param){
        stockFinanceService.financeFetch(param);
        return BaseResult.success(null);
    }

    @ResponseBody
    @RequestMapping(value = "admin/finance_fetch_all", method = RequestMethod.POST)
    public BaseResult financeFetchAll(){
        stockFinanceService.financeFetchAll();
        return BaseResult.success(null);
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public BaseResult list(@RequestBody StockFinanceDto param){
        return BaseResult.success(stockFinanceService.list(param));
    }

    @ResponseBody
    @RequestMapping(value = "date_options", method = RequestMethod.POST)
    public BaseResult dateOptions(@RequestParam("stockId")Long stockId){
        return BaseResult.success(stockFinanceService.dateOptions(stockId));
    }

    @ResponseBody
    @RequestMapping(value = "type_options", method = RequestMethod.POST)
    public BaseResult typeOptions(){
        List<Options> options = new ArrayList<>();
        for(Constants.FinanceInfoTypes type: Constants.FinanceInfoTypes.values()){
            options.add(new Options(type.getKey(), type.getText()));
        }
        return BaseResult.success(options);
    }

    @ResponseBody
    @RequestMapping(value = "date_type_options", method = RequestMethod.POST)
    public BaseResult dateTypeOptions(){
        List<Options> options = new ArrayList<>();
        for(Constants.FinanceDateTypes type: Constants.FinanceDateTypes.values()){
            options.add(new Options(String.valueOf(type.getKey()), type.getTitle()));
        }
        return BaseResult.success(options);
    }

    @RequestMapping(value = "zxcwzb_chart", method = RequestMethod.GET)
    public String zxcwzbChart(Model model){
        return ZXCWZB_CHART;
    }
}
