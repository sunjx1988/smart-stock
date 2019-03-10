package smart.stock.dto;

import lombok.Data;
import smart.stock.entity.Fund;

/**
 * @Auther: sunjx
 * @Date: 2019/3/10 0010 15:36
 * @Description:
 */
@Data
public class FundDto extends Fund {

    //查询条件
    private int page = 1;
    private int rows = 10;
}
