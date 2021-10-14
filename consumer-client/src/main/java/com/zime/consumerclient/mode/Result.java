package com.zime.consumerclient.mode;

import lombok.Data;

@Data
public class Result<T> {

    private int code;
    private String type;
    private String message;
    private T result;


    public Result(int code, String type,String message,T result){
        this.code=code;
        this.type=type;
        this.message=message;
        this.result=result;
    }

}
