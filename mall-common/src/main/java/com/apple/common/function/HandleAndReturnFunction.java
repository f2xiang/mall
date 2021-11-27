package com.apple.common.function;

import java.util.function.Function;

/**
 * @description: 处理function并返回一个输出结果
 * @author: fengx
 * @create: 2021-11-27 13:35
 */

@FunctionalInterface
public interface HandleAndReturnFunction<T, R> {

    R handleAndReturn(Function<T, R> function);
}
