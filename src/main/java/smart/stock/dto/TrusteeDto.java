package smart.stock.dto;

import lombok.Data;
import smart.stock.entity.Trustee;

/**
 * @Auther: sunjx
 * @Date: 2019/2/28 0028 16:44
 * @Description:
 */
@Data
public class TrusteeDto extends Trustee {

    private String statusText;

    //查询条件
    private int page = 1;
    private int rows = 10;
}
