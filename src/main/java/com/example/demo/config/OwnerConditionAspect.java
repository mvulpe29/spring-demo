package com.example.demo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;

@Aspect
@Configuration
public class OwnerConditionAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    protected void restControllerMethods() {
    }

    @Pointcut("within(@org.springframework.data.rest.webmvc.RepositoryRestController *)")
    protected void repositoryRestControllerMethods() {
    }


    @Around("restControllerMethods() || repositoryRestControllerMethods()")
    public Object before(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //get original args
        Object[] args = proceedingJoinPoint.getArgs();

        //get all annotations for arguments
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        Annotation[][] annotations;
        try {
            annotations = proceedingJoinPoint.getTarget().getClass().
                    getMethod(methodName, parameterTypes).getParameterAnnotations();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return proceedingJoinPoint.proceed(args);
    }

}