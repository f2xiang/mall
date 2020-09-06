package com.apple.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @description: 自定义校验注解
 * @author: fengx
 * @create: 2020-09-06 12:35
 */
@Documented
// validatedBy，指定校验器，可以指定多个。
// 可以用我们自定义的ListValConstraintValidator来进行校验，也可以用多个系统带的各种校验器，
@Constraint(validatedBy = { ListValConstraintValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface ListVal {
    // 在配置文件ValidationMessages.properties中配置message的值
    // 或者在注解的时候通过指定message自定义
    String message() default "{com.apple.common.valid.ListVal.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    // 自定义需要传进来的值
    int[] values() default { };
}
