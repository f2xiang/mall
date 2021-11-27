package com.apple.common.function;

import java.util.function.Function;

/**
 * @description: 函数式编程测试工具类
 * @author: fengx
 * @create: 2021-11-27 13:00
 */
public class FunctionUtil {


    /**
     * 函数式编程，根据传参来确定是否抛出异常
     * @param b
     * @return
     */
    public static ThrowExceptionFunction isTrue(boolean b) {
        return (error) -> {
            if (b) {
                throw new RuntimeException(error);
            }
        };
    }

    /**
     * 分支处理选择
     * @param b true 选择true分支进行处理 ， false 用false分支处理
     */
    public static BranchHandler selectTrueOrFalseHandler(boolean b) {
        return ((trueHandler, falseHandler) -> {
            if (b) {
                trueHandler.run();
            }else {
                falseHandler.run();
            }
        });
    }


    /**
     * 如果有参数，consumer
     * 如果没有参数，选择runnable
     */
    public static PresentOrElseHandler isBlankOrNoBlank(String s) {
        return ((consumer, runnable) -> {
            if (s == null || "".equals(s)) {
                runnable.run();
            }else {
                consumer.accept(s);
            }
        });
    }

    /**
     * 根据入参， 和处理方式，输出不同的结果
     * 方式一：function为入参
     */
    public static String handleVal(String val, Function<String, String> function) {
        return function.apply(val);
    }

    /**
     * 根据入参， 和处理方式，输出不同的结果
     * 方式二： function为流式编程的方式注入
     */
    public static HandleAndReturnFunction<String, String> handleVal(String val) {
        return (function) -> function.apply(val);
    }


    public static void main(String[] args) {
//        isTrue(true).throwMessage("为true，抛出异常");
//        isTrue(false).throwMessage("为false，不抛出异常");


        selectTrueOrFalseHandler(true)
                .selectTrueOrFalseToHandle(
                        () -> System.out.println("i am true handler"),
                        () -> System.out.println("false handle is me")
                );

        isBlankOrNoBlank("需要处理的数据， 看是否empty, empty空跑，否则打印").presentOrElseHandle(
                System.out::println,
                () -> System.out.println("没参数处理, 空跑")
        );


        System.out.println(handleVal("555", (a) -> a + "***" + a + "***"));
        System.out.println(handleVal("呜呜呜呜", (a) -> a + "T T" ));


        String s1 = handleVal("aaa").handleAndReturn((a) -> a + "***" + a + "***");
        String s2 = handleVal("呜呜呜呜").handleAndReturn((a) -> a + "T T" );
        System.out.println(s1);
        System.out.println(s2);


    }


}
