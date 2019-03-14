package smart.stock.dto;

import lombok.Data;
import smart.stock.entity.FundStock;

/**
 * @Auther: sunjx
 * @Date: 2019/3/14 0014 14:50
 * @Description:
 */
@Data
public class FundStockDto extends FundStock {

    //查询条件
    private int page = 1;
    private int rows = 10;

    private Long paramFundId;
    private Integer paramStatus;
}
