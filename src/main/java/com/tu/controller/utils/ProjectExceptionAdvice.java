package com.tu.controller.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//作为springMVC的异常处理器
//@ControllerAdvice
//rest风格的异常处理器的注解
@RestControllerAdvice
public class ProjectExceptionAdvice {
    // 拦截所有的异常
    @ExceptionHandler
    public R doException(Exception ex){
        //记录日子
        //通知运维
        //通知开发
        ex.printStackTrace();  //出异常的话，控制台会有信息（否则抛了异常之后，控制台一点信息也没有）
        return new R("服务器故障，请稍后再试！");  //返回R格式的数据，以便信息格式的统一
    }
}
