package smart.stock.controller;

import lombok.Data;

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
        return new BaseResult("0000", null, data);
    }

    public static BaseResult error(String msg, Object data){
        return new BaseResult("0001", msg, data);
    }

}
