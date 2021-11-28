package com.apple.common.el;

import com.apple.common.to.SpuBoundsTo;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * @description: 解析el表达式
 * @author: fengx
 * @create: 2021-11-28 14:07
 */
@Component
public class ParseExceptionTest {


    public static void main(String[] args) {
        SpuBoundsTo boundsTo = new SpuBoundsTo();
        boundsTo.setBuyBounds(1);
        boundsTo.setGrowBounds(5);
        boundsTo.setSpuId(111);
        parse(boundsTo);
    }

    /**
     * EL表达式解析测试
     */
    private static void parse(SpuBoundsTo boundsTo) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        // 在上下文里设置数据
        context.setVariable("boundsTo", boundsTo);
        context.setVariable("id", 1);
        // 从上下文里获取数据
        String result = parser.parseExpression("#boundsTo.spuId").getValue(context, String.class);
        String id = parser.parseExpression("#id").getValue(context, String.class);
        System.out.println(result);
        System.out.println(id);
    }

}
