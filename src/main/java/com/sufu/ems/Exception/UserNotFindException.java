package com.sufu.ems.Exception;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/13 16:18
 * @description
 */
public class UserNotFindException extends Exception{
    public UserNotFindException() {
        super("無此用户，系统异常，请联系管理员！！！");
    }
    public UserNotFindException(String message) {
        super(message);
    }
}
