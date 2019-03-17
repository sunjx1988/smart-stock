package smart.stock.dto;

import lombok.Data;
import smart.stock.entity.FundByDay;

/**
 * @Auther: sunjx
 * @Date: 2019/3/17 0017 10:17
 * @Description:
 */
@Data
public class FundByDayDto extends FundByDay {

    //查询条件
    private int page=1;
    private int rows=30;

    private Long paramFundId;

}
