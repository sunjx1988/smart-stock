package smart.stock.controller;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/2/28 0028 15:47
 * @Description:
 */
@Data
public class PageResult<T> {
    private int draw;

    private long recordsTotal;

    private List<T> data;

    public PageResult(List<T> data) {
        PageInfo pageInfo = new PageInfo(data);
        this.data = data;
        draw = pageInfo.getPageNum();
        recordsTotal = pageInfo.getTotal();
    }
}
