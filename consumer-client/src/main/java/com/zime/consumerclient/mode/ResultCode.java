package com.zime.consumerclient.mode;

public enum ResultCode {

    SUCCESS("success",0),
    ERROR("error",1),
    TIMEOUT("timeout",401),
    WARNING("warning",100);
    private int code;
    private String type;

    private ResultCode(String type, int code) {

        this.code=code;
        this.type=type;
    }

    public int getCode() {
        return code;
    }
}
