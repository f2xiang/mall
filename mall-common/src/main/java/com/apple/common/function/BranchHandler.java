package com.apple.common.function;

/**
 * @description: 两个分支选择两个Runnable处理
 * @author: fengx
 * @create: 2021-11-27 13:08
 */

@FunctionalInterface
public interface BranchHandler {

    void selectTrueOrFalseToHandle(Runnable trueHandler, Runnable falseHandler);
}
