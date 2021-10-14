package com.zime.consumerclient.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @Author: lin
 * @Description: 返回数据统一格式：分页信息
 */

@Data
@NoArgsConstructor
public class Pagination implements Serializable {
    private static final long serialVersionUID = 2839813471092132921L;

//  当前页
    private Long page = 1L;
//  每页记录数
    private Long perPage = 25L;
//  总数
    private Long total;

    public Pagination(Long page, Long perPage, Long total) {
        this.page = page;
        this.perPage = perPage;
        this.total = total;
    }

    public Pagination(Long total) {
        this.total = total;
    }
}
