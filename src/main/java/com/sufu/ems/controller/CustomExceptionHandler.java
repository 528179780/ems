package com.sufu.ems.controller;

import com.sufu.ems.Exception.ResourceNotFindException;
import com.sufu.ems.Exception.UserNotFindException;
import com.sufu.ems.dto.BaseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/13 15:50
 * @description 全局异常处理
 */
@ControllerAdvice
public class CustomExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = ResourceNotFindException.class)
    public BaseResult ResourceNotFindExceptionHandler(ResourceNotFindException exception){
        BaseResult result = BaseResult.fail(exception.getMessage());
        result.setCode(404);
        result.setData(null);
        return result;
    }
    @ResponseBody
    @ExceptionHandler(value = UserNotFindException.class)
    public BaseResult UserNotFindException(UserNotFindException exception){
        BaseResult result = BaseResult.fail(exception.getMessage());
        result.setData(null);
        return result;
    }
}
