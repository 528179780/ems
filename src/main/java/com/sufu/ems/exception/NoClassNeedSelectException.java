package com.sufu.ems.exception;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/15 17:47
 * @description
 */
public class NoClassNeedSelectException extends Exception{
    public NoClassNeedSelectException() {
        super("没有课选，请联系管理员");
    }
    public NoClassNeedSelectException(String message) {
        super(message);
    }
}
