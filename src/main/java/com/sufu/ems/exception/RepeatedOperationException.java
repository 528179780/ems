package com.sufu.ems.exception;

/**
 * @author sufu
 * @date 2020/6/3 下午4:41
 * @description 重复操作异常
 **/
public class RepeatedOperationException extends Exception{
    public RepeatedOperationException() {
        super("重复操作！");
    }
    public RepeatedOperationException(String message) {
        super(message);
    }
}
