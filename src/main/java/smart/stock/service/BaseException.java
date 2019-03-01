package smart.stock.service;

import lombok.Data;

/**
 * @Auther: sunjx
 * @Date: 2019/3/1 0001 17:03
 * @Description:
 */
@Data
public class BaseException extends RuntimeException{

    private static final String SYS_ERROR = "0001";

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
        return new BaseException(SYS_ERROR, msg, data);
    }
}
