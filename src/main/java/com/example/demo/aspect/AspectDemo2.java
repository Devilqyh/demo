package com.example.demo.aspect;

import com.example.demo.annotation.AspectMethod;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @date 2019/05/16 15:44
 */
@Component
@Aspect
@Order(3) // 数字越大的越后面执行
public class AspectDemo2 {
    private final Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 有 AspectMethod 注解都会被拦截
     */
    @Around("@annotation(as)")
    public Object around(ProceedingJoinPoint pjp, AspectMethod as) {
        try {
            //调用目标原有的方法
            log.info("AspectDemo2 环绕通知是一个很强大的通知，使用不当，可能会影响程序的进行");
            // 其中是执行方法的逻辑
            Object o = pjp.proceed();
            log.info("AspectDemo2 方法环绕proceed，结果是:"+o);
            log.info("AspectDemo2 方法描述"+as.description()+as.returnValue()+as.value());
            return "哈哈";
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
