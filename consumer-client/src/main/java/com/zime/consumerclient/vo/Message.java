package com.zime.consumerclient.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @Author: lin
 * @Description: 返回响应格式
 */

@Data
public class Message implements Serializable {

    private static final long serialVersionUID = -7099519718916403776L;
    private String type = "success";
    private String cnt = "请求成功";

    public Message(String type, String cnt) {
        this.type = type;
        this.cnt = cnt;
    }

    public Message(String cnt) {
        this.cnt = cnt;
    }

    public Message() {
    }
}
