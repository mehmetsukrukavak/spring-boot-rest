package com.mehmetsukrukavak.spring_boot_rest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.mehmetsukrukavak.spring_boot_rest.service.JobService.getJob(..)) && args(Id)")
    public Object validateAndUpdate(ProceedingJoinPoint jp, int Id) throws Throwable {

        if (Id < 0) {
            LOGGER.error("Invalid Id: " + Id);
            Id = -Id;  }
        Object object = jp.proceed(new Object[]{ Id });


        return object;
    }

}
