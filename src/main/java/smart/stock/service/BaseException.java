package smart.stock.service;

import lombok.Data;
import smart.stock.constant.ResultCode;

/**
 * @Auther: sunjx
 * @Date: 2019/3/1 0001 17:03
 * @Description:
 */
@Data
public class BaseException extends RuntimeException{

    private String code;
    private String msg;
    private Object data;

    public BaseException(String code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BaseException error(String msg, Object data){
        return new BaseException(ResultCode.SYS.FAIL.getCode(), msg, data);
    }
}
