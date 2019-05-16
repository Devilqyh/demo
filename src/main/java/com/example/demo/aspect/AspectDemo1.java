package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * order 定义横切的顺序
 *
 * @author xuweizhi
 * @date 2019/05/16 15:19
 */
@Component
@Aspect
@Order(1)
public class AspectDemo1 {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 专业术语
     * 1.连接点（JoinPoint）:对用具体的拦截对象，Spring只支持拦截方法，拦截对象是指特定的对象
     * 2.切点  （point cut）:有时候需要拦截的方法，不止一个方法而是很多类的方法，这时候需要定义一系列的规则适配
     * 3.通知  （advice）   :
     * <p>
     * 前置通知（before advice）
     * 后置通知（after advice）
     * 环绕通知（around advice）
     * 事后返回通知（afterReturning advice）
     * 异常通知（afterThrowing advice）
     * 4.切面（aspect）: 是一个可以定义切点，各类通知和引入的内容。
     * <p>
     * 定义切面正则表达式，第一个 * 代表任意返回值，第二个* 代表 com.example.demo.controller 下的任意类
     * <p>
     * find* 代表其中find 开头的方法，最终会在
     */
    @Pointcut("execution(public * com.example.demo.controller.*.find*(..))")
    public void pointCut() {
    }

    /**
     * 方法执行前进行拦截处理
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取类名
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        //获取切入方法名
        String methodName = joinPoint.getSignature().getName();
        //获取切入点方法参数类型
        Object[] args = joinPoint.getArgs();

        log.info("请求的类名:" + declaringTypeName + ",方法名:" + methodName + ",入参:" + Arrays.toString(args));
    }

    /**
     * 放啊
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            //调用目标原有的方法
            log.info("AspectDemo1 环绕通知是一个很强大的通知，使用不当，可能会影响程序的进行");
            // 其中是执行方法的逻辑
            Object o = pjp.proceed();
            log.info("AspectDemo1 方法环绕proceed，结果是 :{}", o);
            return "返回 AspectDemo1 的结果，替换原方法的结果";
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    @AfterThrowing("pointCut()")
    public void throwsL(JoinPoint jp) {
        System.out.println("方法异常时执行.....");
    }

    /**
     * 无论如何最终都执行
     */
    @After("pointCut()")
    public void after() {
        log.info("After Advice={}", "This is a after advice!");
    }

    /**
     * 获取返回值之前
     */
    @AfterReturning(pointcut = "pointCut()", returning = "object")
    public void getAfterReturn(Object object) {
        log.info("afterReturning={}", object.toString());
    }

}
