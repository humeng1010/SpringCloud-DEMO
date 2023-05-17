package cn.wuluwulu.springcloud.exception;

import cn.wuluwulu.springcloud.entity.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public CommonResult<String> GlobalExceptionHandler(Exception e) {
        return new CommonResult<>(500, "服务异常", e.getMessage());
    }
    
}
