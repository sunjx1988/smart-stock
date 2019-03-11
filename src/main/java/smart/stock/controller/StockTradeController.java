package smart.stock.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import smart.stock.dto.StockTradeDto;
import smart.stock.entity.Stock;
import smart.stock.entity.StockTrade;
import smart.stock.service.StockTradeService;

/**
 * @Auther: sunjx
 * @Date: 2019/3/11 0011 15:59
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/stocktrade")
public class StockTradeController {
    private static final String LIST_PAGE = "stocktrade/list";

    @Autowired
    private StockTradeService stockTradeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listPage(Model model){
        return LIST_PAGE;
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageInfo<StockTradeDto> list(StockTradeDto stockTradeDto){
        PageHelper.startPage(stockTradeDto.getPage(), stockTradeDto.getRows());
        return new PageInfo(stockTradeService.list(stockTradeDto));
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public BaseResult<StockTrade> save(@RequestBody StockTrade stockTrade){
        Long id = stockTradeService.save(stockTrade);
        if(id > 0){
            return BaseResult.success(stockTrade);
        }else{
            return BaseResult.error(stockTrade);
        }
    }
}
