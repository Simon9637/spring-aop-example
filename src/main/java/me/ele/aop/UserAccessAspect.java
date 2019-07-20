package me.ele.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuhuan
 * @CreateDate: 2019/6/19
 * <p>Copyright: Copyright (c) 2019</p>
 *
 *
 * 除了@Around外，每个方法里都可以加或者不加参数JoinPoint，如果有用JoinPoint的地方就加，不加也可以，JoinPoint里包含了类名、被切面的方法名，参数等属性，可供读取使用。@Around
 *  * 参数必须为ProceedingJoinPoint，pjp.proceed相应于执行被切面的方法。@AfterReturning方法里，可以加returning =
 *  * “XXX”，XXX即为在controller里方法的返回值，本例中的返回值是“first controller”。@AfterThrowing方法里，可以加throwing = "XXX"，供读取异常信息，
 */
@Aspect
@Component
public class UserAccessAspect {

    @Pointcut(value = "@annotation(me.ele.annotation.UserAccess)")
    public void access() {

    }

    @Before("access()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        System.out.println(
            "CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature()
                .getName());
        //System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //Object[] args = joinPoint.getArgs();
        //String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        //Map<String, String> tag = new HashMap<>();
        //for (int i = 0 ; i < argNames.length; i++) {
        //    tag.put(argNames[i], String.valueOf(args[i]));
        //}
        //System.out.println(tag.toString());

    }

    @AfterReturning(returning = "ret", pointcut = "access()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("方法的返回值 : " + JSON.toJSONString(ret));
    }

    //后置异常通知
    @AfterThrowing(throwing = "ex", pointcut = "access()")
    public void throwss(JoinPoint jp, Exception ex) {
        System.out.println("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("access()")
    public void after(JoinPoint jp) {
        System.out.println("方法最后执行.....");
    }

}
