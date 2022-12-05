package com.example.demo.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Aspect
@Component
@Slf4j
public class AspectLogging {

    @Pointcut("execution(* com.example.demo.User.UserController.*(..))")
    public void logging(){
    }

    @Around("logging()")
    public Object before(ProceedingJoinPoint jp) throws Throwable {
        log.info("Before Method Invoked :: "+ jp.getSignature());

        Object obj=jp.proceed();

        log.info("After Method Invoked :: " + jp.getSignature());

            return obj;
    }




}
