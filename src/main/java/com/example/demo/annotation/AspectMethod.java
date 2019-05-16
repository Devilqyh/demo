package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @date 2019/05/16 15:16
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectMethod {

    /**
     * 定义注解的属性值
     */
    String value() default "";

    /**
     * 定义方法的描述
     */
    String description() default "";

    /**
     * 定义方法的返回值
     */
    String returnValue() default "";
}
