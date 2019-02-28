package smart.stock.dto;

import lombok.Data;
import smart.stock.entity.User;

/**
 * @Auther: sunjx
 * @Date: 2019/2/28 0028 16:44
 * @Description:
 */
@Data
public class UserDto extends User {

    //查询条件
    private int page = 1;
    private int rows = 10;
}
