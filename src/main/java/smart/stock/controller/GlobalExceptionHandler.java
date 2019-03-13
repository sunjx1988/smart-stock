package smart.stock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import smart.stock.service.BaseException;

/**
 * 全局异常消息处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public BaseResult<?> jsonErrorHandler(Exception e) {
		if(e instanceof BaseException){
			BaseException exception = (BaseException)e;
			log.error("错误码 -> {}, 信息 -> {}",((BaseException) e).getCode(), ((BaseException) e).getMsg(), e);
			return new BaseResult<>(exception.getCode(), exception.getMsg(), exception.getData());
		}else{
			log.error("未知错误", e);
			return BaseResult.error(null);
		}
	}

}
