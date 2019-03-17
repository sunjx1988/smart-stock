package smart.stock.dto;

import lombok.Data;
import smart.stock.entity.TrusteeByDay;

/**
 * @Auther: sunjx
 * @Date: 2019/3/17 0017 14:11
 * @Description:
 */
@Data
public class TrusteeByDayDto extends TrusteeByDay {
    //查询条件
    private int page=1;
    private int rows=30;
}
