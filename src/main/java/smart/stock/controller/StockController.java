package smart.stock.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smart.stock.dto.StockDto;
import smart.stock.entity.Stock;
import smart.stock.service.StockService;

/**
 * @Auther: sunjx
 * @Date: 2019/2/28 0028 10:16
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/stock")
public class StockController {

    private static final String LIST_PAGE = "stock/list";

    private static final String DETAIL_PAGE = "stock/detail";

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listPage(Model model){
        return LIST_PAGE;
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageInfo<StockDto> list(StockDto stockDto){
        PageHelper.startPage(stockDto.getPage(), stockDto.getRows());
        return new PageInfo(stockService.list(stockDto));
    }

    @RequestMapping(value = "{mode}/{id}", method = RequestMethod.GET)
    public String detailPage(@ModelAttribute("mode") String mode,
                             @ModelAttribute("id") Long id,
                             Model model){
        return DETAIL_PAGE;
    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insertPage(Model model){
        model.addAttribute("mode","insert");
        return DETAIL_PAGE;
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public BaseResult<StockDto> detail(@PathVariable("id") Long id, Model model){
        return BaseResult.success(stockService.detail(id));
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public BaseResult<Stock> save(@RequestBody Stock stock){
        Long id = stockService.save(stock);
        if(id > 0){
            return BaseResult.success(stock);
        }else{
            return BaseResult.error(stock);
        }
    }
}


