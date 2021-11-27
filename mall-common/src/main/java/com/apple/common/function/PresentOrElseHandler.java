package com.apple.common.function;

import java.util.function.Consumer;

/**
 * @description: 有值则执行consumer， 没有值则执行Runnable
 * @author: fengx
 * @create: 2021-11-27 13:24
 */
@FunctionalInterface
public interface PresentOrElseHandler {

    void presentOrElseHandle(Consumer<String> consumer, Runnable runnable);
}
