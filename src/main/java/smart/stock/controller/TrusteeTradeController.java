package smart.stock.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smart.stock.dto.Options;
import smart.stock.dto.StockTradeDto;
import smart.stock.dto.TrusteeTradeDto;
import smart.stock.entity.StockTrade;
import smart.stock.service.StockTradeService;
import smart.stock.service.TrusteeTradeService;

import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/13 0011 15:59
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/trusteetrade")
public class TrusteeTradeController {
    private static final String LIST_PAGE = "trusteetrade/list";

    @Autowired
    private TrusteeTradeService trusteeTradeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listPage(Model model){
        return LIST_PAGE;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo<TrusteeTradeDto> list(TrusteeTradeDto trusteeTradeDto){
        PageHelper.startPage(trusteeTradeDto.getPage(), trusteeTradeDto.getRows());
        return new PageInfo(trusteeTradeService.list(trusteeTradeDto));
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult<TrusteeTradeDto> save(@RequestBody TrusteeTradeDto trusteeTradeDto){
        Long id = trusteeTradeService.buy(trusteeTradeDto);
        if(id > 0){
            return BaseResult.success(trusteeTradeDto);
        }else{
            return BaseResult.error(trusteeTradeDto);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.POST)
    public BaseResult<TrusteeTradeDto> confirm(@PathVariable("id") Long id){
        return BaseResult.success(trusteeTradeService.confirm(id));
    }

    @ResponseBody
    @RequestMapping(value = "/interestrate/options", method = RequestMethod.POST)
    public BaseResult<List<Options>> interestRates(){
        return BaseResult.success(trusteeTradeService.interestRates());
    }

}
