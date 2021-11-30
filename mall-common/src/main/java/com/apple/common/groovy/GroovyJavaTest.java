package com.apple.common.groovy;

import groovy.lang.GroovyClassLoader;

/**
 * @description: 用GroovyClassLoader来执行字符串的java代码
 * @author: fengx
 * @create: 2021-11-30 21:55
 */
public class GroovyJavaTest {
    public static void main(String[] args) throws Exception{
        test_01();
    }

    /**
     * 注意package必须存在！否则报错！！！
     * @throws Exception
     */
    private static void test_01() throws Exception{
        String codeSource = "package com.apple.common.groovy;\r\n" +
                "public class DemoHandler implements IGroovyHandler {\r\n" +
                "	@Override\r\n" +
                "	public void handle(String obj) {\r\n" +
                "		System.out.println(\"############### \"+ obj);\r\n" +
                "	}\r\n" +
                "}";
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        Class<?> clazz = groovyClassLoader.parseClass(codeSource);
        if (clazz != null) {
            Object instance = clazz.newInstance();
            if (instance!=null) {
                if (instance instanceof IGroovyHandler) {
                    IGroovyHandler handler = (IGroovyHandler) instance;
                    handler.handle("天气不错！");
                } else {
                    System.err.println("转换失败！");
                }
            }
        }
    }
}
