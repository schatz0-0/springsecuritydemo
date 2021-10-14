package com.zime.consumerclient.enums;


import lombok.Getter;

/**
 * @author lin
 * 全局返回响应码
 */

@Getter
public enum ResultCodeEnum {
    /**
     * 200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
     * 201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
     * 202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
     * 204 NO CONTENT - [DELETE]：用户删除数据成功。
     * 400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
     * 401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
     * 403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
     * 404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
     * 406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
     * 410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
     * 422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
     * 500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。
     */


    SUCCESS(200, "请求成功"),
    CREATED_SUCCESS(201, "用户新建或修改数据成功"),
    ACCEPTED(202, "请求已经进入后台排队"),
    DELETE_SUCCESS(204, "用户新建或修改数据成功"),

    INVALID_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "没有访问权限"),
    NOT_AUTHENTICATION(402, "未登录认证"),
    FORBIDDEN(403, "访问是被禁止的"),
    NOT_FOUND(404, "找不到资源"),
    LOGIN_FAILED(405, "认证失败"),
    NOT_ACCEPTABLE(406, "用户请求的格式不可得"),
    GONE(407, "资源不存在"),
    REQUEST_FAILED(408, "请求失败"),
    ACCOUNT_IS_LOCKED(409, "账号已锁定，请联系管理员"),
    REPEAT_REQUEST(410, "请勿重复提交"),
    REQUEST_PARAM_ERROR(411, "参数格式错误"),
    TOKEN_EXPIRED(412, "Token无效"),
    TOKEN_ILLEGAL(413, "Token不合法"),
    SERVER_ERROR(500, "服务器内部错误"),

    /**
     * 用户模块 10001-10100 其他依次类推
     */
    VALIDATE_FAILED(10001, "参数校验失败"),
    USERNAME_NOT_NULL(10002, "用户名不能为空"),
    PASSWORD_NOT_NULL(10003, "密码不能为空"),
    ACCOUNT_NOT_EXIST(10004, "用户不存在"),
    ORGANIZATION_DELETE_FAILED(10007, "模块删除失败"),


    SESSION_EXPIRED(40000, "session过期");


    /**
     * 角色模块 10101-10200
     */

    private int code;
    private String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
