package smart.stock.dto;

import lombok.Data;
import smart.stock.entity.Stock;

/**
 * @Auther: sunjx
 * @Date: 2019/3/10 0010 17:17
 * @Description:
 */
@Data
public class StockDto extends Stock {
    //查询条件
    private int page = 1;
    private int rows = 10;
}
