package com.zime.consumerclient.mode;

public enum ReturnCode {

    SUCCESS("success",0),
    ERROR("error",1),
    TIMEOUT("timeout",401),
    WARNING("warning",100);

    private int code;
    private String type;
    private ReturnCode(String type,int code){
        this.type=type;
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
