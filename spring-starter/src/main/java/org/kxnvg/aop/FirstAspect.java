package org.kxnvg.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Component
public class FirstAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {

    }

    @Pointcut("within(org.kxnvg.service.*Service)")
    public void isServiceLayer() {

    }

    @Pointcut("this(org.springframework.data.repository.Repository)")
    public void isRepositoryLayer() {

    }

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {

    }

    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelParam() {

    }

    @Pointcut("isControllerLayer() && @args(org.kxnvg.validation.UserInfo,..)")
    public void hasUserInfoParamAnnotation() {

    }

    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {

    }

    @Pointcut("execution(public * org.kxnvg.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod() {

    }

    @Before("anyFindByIdServiceMethod() && args(id) && target(service) && this(serviceProxy) && @annotation(transactional)")
    public void addLogging(JoinPoint joinPoint, Object id, Object service, Object serviceProxy, Transactional transactional) {
        log.info("invoked findById method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "anyFindByIdServiceMethod() && target(service)", returning = "result")
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info("after returning - invoked findById method in class {}, result {}", service, result);
    }

    @AfterThrowing(value = "anyFindByIdServiceMethod() && target(service)",  throwing = "ex")
    public void addLoggingAfterThrowing(Throwable ex, Object service) {
        log.info("after throwing - invoked findById method in class {}, exception {}", service, ex.getClass());
    }

    @After("anyFindByIdServiceMethod() && target(service)")
    public void addLoggingAfterFinally(Object service) {
        log.info("after (finally) - invoked findById method in class {}", service);
    }

    @Around("anyFindByIdServiceMethod() && target(service) && args(id)")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
        log.info("AROUND before - invoked findById method in class {}, with id {}", service, id);
        try {
            Object result = joinPoint.proceed();
            log.info("AROUND after returning - invoked findById method in class {}, result {}", service, result);
            return result;
        } catch (Throwable ex) {
            log.info("AROUND after throwing - invoked findById method in class {}, exception {}", service, ex.getClass());
            throw ex;
        } finally {
            log.info("AROUND after (finally) - invoked findById method in class {}", service);
        }
    }
}
