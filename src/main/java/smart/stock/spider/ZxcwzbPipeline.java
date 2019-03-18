package smart.stock.spider;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.StockDto;
import smart.stock.dto.StockFinanceDto;
import smart.stock.mapper.StockMapper;
import smart.stock.service.StockFinanceService;
import smart.stock.service.StockService;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 13:41
 * @Description:
 */
@Slf4j
@Component
public class ZxcwzbPipeline implements Pipeline {

    @Autowired
    private StockFinanceService stockFinanceService;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<String> params = SpiderUtil.getParamFromTemplate(resultItems.getRequest().getUrl(), SpiderUrlConst.ZXCWZB);
        String code = params.get(0);
        String dateParam = params.get(1);
        String dateParamSuffix = dateParam.substring(4);
        StockDto stockDto = stockMapper.selectByCode(code);

        if(!CollectionUtils.isEmpty(resultItems.getAll())){
            StockFinanceDto stockFinance = new StockFinanceDto();
            stockFinance.setName(stockDto.getName());
            stockFinance.setCode(code);
            stockFinance.setType(Constants.FinanceInfoTypes.ZXCWZB.getKey());
            stockFinance.setDate(dateParam);
            stockFinance.setDateType(Constants.FinanceDateTypes.getKeyByText(dateParamSuffix));
            stockFinance.setInfo(JSON.toJSONString(resultItems.getAll()));
            stockFinance.setInfoVersion(0);
            stockFinanceService.save(stockFinance);
        }
    }
}
