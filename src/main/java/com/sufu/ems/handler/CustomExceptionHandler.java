package com.sufu.ems.handler;

import com.sufu.ems.exception.*;
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
@ResponseBody
public class CustomExceptionHandler {
    @ExceptionHandler(value = ResourceNotFindException.class)
    public BaseResult resourceNotFindExceptionHandler(ResourceNotFindException exception){
        BaseResult result = BaseResult.fail(exception.getMessage());
        result.setCode(404);
        result.setData(null);
        return result;
    }
    @ExceptionHandler(value = UserNotFindException.class)
    public BaseResult userNotFindExceptionHandler(UserNotFindException exception){
        BaseResult result = BaseResult.fail(exception.getMessage());
        result.setData(null);
        return result;
    }
    @ExceptionHandler(value = RepeatedOperationException.class)
    public BaseResult repeatedOperationExceptionHandler(RepeatedOperationException exception){
        BaseResult result = BaseResult.fail(exception.getMessage());
        result.setData(null);
        return result;
    }
    @ExceptionHandler(value = MajorNotMatchException.class)
    public BaseResult MajorNotMatchExceptionHandler(MajorNotMatchException exception){
        BaseResult result = BaseResult.fail(exception.getMessage());
        result.setData(null);
        return result;
    }
}
