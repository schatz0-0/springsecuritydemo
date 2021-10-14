package com.zime.consumerclient.vo;

import com.zime.consumerclient.enums.ResultCodeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 *
 * @Author: lin
 * @Description: 返回统一格式
 */

@Data
@NoArgsConstructor

public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = 7511725167268812226L;
    private Integer code;

    @Getter
    private Boolean success = false;

    private Message message = new Message();

//  数据体
    private T data;

//  错误信息
    private Error errors = new Error();

//  分页信息
    private Pagination pagination = new Pagination();

    public ResultVo(ResultCodeEnum resultCodeEnum, T data) {
        this.success = true;
        this.code = resultCodeEnum.getCode();
        this.message.setCnt(resultCodeEnum.getMsg());
        this.data = data;
    }

    public ResultVo(ResultCodeEnum resultCodeEnum, T data, Pagination pagination) {
        this.code = resultCodeEnum.getCode();
        if (200 == resultCodeEnum.getCode()) {
            this.success = true;
            this.message.setType("success");
        }
        this.message.setCnt(resultCodeEnum.getMsg());
        this.data = data;
        this.pagination = pagination;
    }

    public ResultVo(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message.setCnt(resultCodeEnum.getMsg());
    }

    public ResultVo(ResultCodeEnum resultCodeEnum, Message message) {
        this.code = resultCodeEnum.getCode();
        if (resultCodeEnum.getCode() == 200)
            this.success = true;
        this.message = message;
        if (StringUtils.isEmpty(message.getCnt())) {
            this.message.setCnt(resultCodeEnum.getMsg());
        }
    }

    public ResultVo(ResultCodeEnum resultCodeEnum, Message message, T data) {
        this.code = resultCodeEnum.getCode();
        this.success = true;
        this.message.setType(message.getType());
        this.message.setCnt(message.getCnt());
        if (StringUtils.isEmpty(message.getCnt())) {
            this.message.setCnt(resultCodeEnum.getMsg());
        }
        this.data = data;
    }

    public ResultVo(ResultCodeEnum resultCodeEnum, Message message, Error errors) {
        this.code = resultCodeEnum.getCode();
        this.message.setType(message.getType());
        this.message.setCnt(message.getCnt());
        if (StringUtils.isEmpty(message.getCnt())) {
            this.message.setCnt(resultCodeEnum.getMsg());
        }
        this.errors = errors;
    }
}
