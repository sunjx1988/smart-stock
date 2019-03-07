package smart.stock.controller;

import lombok.Data;
import smart.stock.constant.ResultCode;

/**
 * @Auther: sunjx
 * @Date: 2019/2/28 0028 15:47
 * @Description:
 */
@Data
public class BaseResult<T> {
    private String code;
    private String msg;
    private T data;

    public BaseResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BaseResult success(Object data){
        return new BaseResult(ResultCode.SYS.SUCCESS.getCode(), ResultCode.SYS.SUCCESS.getText(), data);
    }

    public static BaseResult error(Object data){
        return new BaseResult(ResultCode.SYS.FAIL.getCode(), ResultCode.SYS.FAIL.getText(), data);
    }

}
