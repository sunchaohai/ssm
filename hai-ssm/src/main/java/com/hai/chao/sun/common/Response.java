package com.hai.chao.sun.common;

import com.hai.chao.sun.common.enu.CommonStatus;

public class Response {
    private String status;
    private String msg;
    private Object obj;
    
    private Response(String status,String msg,Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }
    
    public static Response success(String msg) {
        return new Response(CommonStatus.SUCCESSSTATUS.getCode(),msg,null);
    }
    public static Response fail(String msg) {
        return new Response(CommonStatus.FAILSTATUS.getCode(),msg,null);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
