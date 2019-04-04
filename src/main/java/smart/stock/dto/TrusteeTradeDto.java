package smart.stock.dto;

import lombok.Data;
import smart.stock.entity.TrusteeTrade;

import java.util.Date;

/**
 * @Auther: sunjx
 * @Date: 2019/3/10 0010 17:17
 * @Description:
 */
@Data
public class TrusteeTradeDto extends TrusteeTrade {

    //查询条件
    private int page = 1;
    private int rows = 10;

    private Long paramTrusteeId;
    private Date paramEndDateStart;
    private Date paramEndDateEnd;
    private Integer paramStatus;

    //查询结果
    private String interestRateText;
    private String statusText;


}
