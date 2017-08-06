package com.hai.chao.sun.common.enu;

public enum CommonStatus {

    SUCCESSSTATUS("1","成功"),
    FAILSTATUS("0","失败");
    private String code;
    private String msg;
    
    //构造方法
    private CommonStatus(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    

}
