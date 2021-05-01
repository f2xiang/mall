package com.apple.common.exception;

/**
 * @description: 统一的错误校验码 业务(2位)+错误码(3位)
 *  10 通用 11 商品 12 订单 13 购物车 14 物流
 * @author: fengx
 * @create: 2020-09-06 11:07
 */
public enum BizCodeEnum {
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VALID_EXCEPTION(10001, "参数格式校验异常"),
    TOO_MANY_REQUEST(10002, "请求过多，挤爆了"),
    ;

    private Integer code;
    private String msg;

    BizCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
