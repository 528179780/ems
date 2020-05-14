package com.sufu.ems.Exception;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/13 16:07
 * @description
 */
public class ResourceNotFindException extends Exception{
    public ResourceNotFindException() {
        super("访问的资源未找到");
    }
    public ResourceNotFindException(String message) {
        super(message);
    }
}
