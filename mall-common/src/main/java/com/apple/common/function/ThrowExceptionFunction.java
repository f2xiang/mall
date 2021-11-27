package com.apple.common.function;

/**
 * @description: 根据传入的值是否抛出异常
 * @author: fengx
 * @create: 2021-11-27 12:59
 */
@FunctionalInterface
public interface ThrowExceptionFunction {

    void throwMessage(String message);


}
