package com.sufu.ems.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/9 0:04
 * @description 自定义的返回结果基类
 */
@Data
public class BaseResult implements Serializable {
    private Integer code;
    private String message;
    private Map<String,Object> data;
    public static BaseResult success(){
        BaseResult result = new BaseResult();
        result.setCode(200);
        result.setMessage("请求成功！");
        return result;
    }
    public static BaseResult success(String msg){
        BaseResult result = new BaseResult();
        result.setCode(200);
        result.setMessage(msg);
        return result;
    }
    public static BaseResult success(String msg,Map<String,Object> data){
        BaseResult result = new BaseResult();
        result.setCode(200);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }
    public static BaseResult fail(){
        BaseResult result = new BaseResult();
        result.setCode(500);
        result.setMessage("请求失败！");
        return result;
    }
    public static BaseResult fail(String msg){
        BaseResult result = new BaseResult();
        result.setCode(500);
        result.setMessage(msg);
        return result;
    }
}
