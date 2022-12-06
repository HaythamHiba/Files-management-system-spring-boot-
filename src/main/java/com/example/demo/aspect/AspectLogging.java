package com.example.demo.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;



@Aspect
@Component
@Slf4j
public class AspectLogging {

    @Pointcut("execution(* com.example.demo.User.UserController.*(..))")
    public void loggingUser(){
    }
    @Pointcut("execution(* com.example.demo.GroupFile.FileController.*(..))")
    public void loggingGroupFile(){
    }
    @Pointcut("execution(* com.example.demo.Group.GroupController.*(..))")
    public void loggingGroup(){
    }

    @Around("loggingUser() || loggingGroup() || loggingGroupFile()")
    public Object before(ProceedingJoinPoint jp) throws Throwable {
        log.info("Before Method Invoked :: "+ jp.getSignature());

        Object obj=jp.proceed();


        log.info("After Method Invoked :: " + obj);

            return obj;
    }




}
