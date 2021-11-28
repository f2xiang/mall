package com.apple.common.el;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description: 缓存切面
 * @author: fengx
 * @create: 2021-11-28 14:20
 */
@Component
@Aspect
public class CacheableAspect {

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.apple.common.el.Cacheable)")
    public void pointcut() {

    }


    @Around("@annotation(cache)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint proceedingJoinPoint, Cacheable cache) {
        // 获取方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取方法的参数名
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);

        // 获取实际参数的数据
        Object[] args = proceedingJoinPoint.getArgs();

        ExpressionParser expressionParser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        // 把方法的参数都放到 表达式解析上下文里 ， 以供表达式从里面拿数据
        for (int i = 0; i < args.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        // 根据ognl，获取到key，从redis查缓存
        Object elValue = expressionParser.parseExpression(cache.key()).getValue();
        System.out.println(elValue);
//        Object redisCacheVal = redisTemplate.opsForValue().get(elValue);
//        // 没查到缓存执行方法，否则直接返回缓存数据
//        if(redisCacheVal == null) {
//            Object result = proceedingJoinPoint.proceed();
//            redisTemplate.opsForValue().set(elValue, result);
//            return result;
//        }else {
//            return redisCacheVal;
//        }
        return "";

    }

}