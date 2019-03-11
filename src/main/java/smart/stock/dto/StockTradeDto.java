package smart.stock.dto;

import lombok.Data;
import smart.stock.entity.StockTrade;

/**
 * @Auther: sunjx
 * @Date: 2019/3/10 0010 17:17
 * @Description:
 */
@Data
public class StockTradeDto extends StockTrade {

    //查询结果
    private String typeText;

    //查询条件
    private int page = 1;
    private int rows = 10;
}
