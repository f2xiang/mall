package com.apple.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 自定义校验器
 * @author: fengx
 * @create: 2020-09-06 12:45
 */
public class ListValConstraintValidator implements ConstraintValidator<ListVal, Integer> {

    private final Set<Integer> set = new HashSet<>();
    /**
     * 初始化方法， 能获取到配置的注解的详细信息
     * @param constraintAnnotation 注解
     */
    @Override
    public void initialize(ListVal constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        for (int value : values) {
            set.add(value);
        }
    }

    /**
     * 判断是否校验成功
     * @param value 需要校验的值
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }
}
