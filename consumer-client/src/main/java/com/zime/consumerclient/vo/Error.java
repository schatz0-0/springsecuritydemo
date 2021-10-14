package com.zime.consumerclient.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @Author: lin
 * @Description: 响应格式：只返回错误的map类型信息
 */
@Data
public class Error implements Serializable {
    private static final long serialVersionUID = 6219129262184199654L;
    private Map<Object, List<Object>> errors = new HashMap<Object, List<Object>>();

    public Error(Map<Object, List<Object>> errors) {
        this.errors = errors;
    }

    public Error() {
    }
}
