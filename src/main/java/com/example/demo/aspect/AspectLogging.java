package com.example.demo.aspect;


import com.example.demo.log.LogMethods;
import com.example.demo.log.LogMethodsRepo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Aspect
@Component
@Slf4j

public class AspectLogging {

    @Autowired
    public LogMethodsRepo logMethodsRepo;

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
        return runMethod(jp);


    }

    @Transactional

    public Object runMethod(ProceedingJoinPoint jp) throws Throwable {

        LogMethods logMethods=new LogMethods(
                "Before Method Invoked :: " + jp.getSignature()
        );
        logMethodsRepo.save(logMethods);


        Object obj=jp.proceed();

        LogMethods logMethods2=new LogMethods(
                "After Method Invoked :: " + obj
        );
        logMethodsRepo.save(logMethods2);


        return obj;

    }


}
