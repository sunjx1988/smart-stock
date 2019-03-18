package smart.stock.dto;

import lombok.Data;
import smart.stock.entity.StockFinance;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 15:04
 * @Description:
 */
@Data
public class StockFinanceDto extends StockFinance {

    private int page = 1;
    private int rows = 10;

    //spider 抓取条件
    private String spiderParamStockCode;
    private int spiderParamDateType;
    private String spiderParamYear;
    private String spiderParamType;
}
