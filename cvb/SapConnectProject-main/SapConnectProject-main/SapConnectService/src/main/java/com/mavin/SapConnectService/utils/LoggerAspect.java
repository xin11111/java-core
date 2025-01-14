package com.mavin.SapConnectService.utils;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mavin.SapConnectService.dto.UserActionDto;
import com.mavin.SapConnectService.manager.UserActionManager;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggerAspect {
	@Autowired 
	private TransCodeHelper transCodeHelper;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserActionManager userActionManager;
	
    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
        " || within(@org.springframework.stereotype.Service *)" +
        " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.mavin.SapConnectService.controller..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	String keyString = transCodeHelper.generateRandomNumber(8);
    	HttpServletRequest request = null;
    	UserActionDto userAct = new UserActionDto();
    	
    	userAct.setLogKey(keyString);
    	userAct.setMethodName(String.join(".", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()));
    	userAct.setInput(Arrays.toString(joinPoint.getArgs()));
    	
    	try {
    		ServletRequestAttributes requestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    		request = requestAttribute.getRequest();
    	} catch (IllegalStateException inlegalEx) {}
    	
    	if(request != null) {
    		String username = jwtTokenProvider.getUserName(request);
    		userAct.setUser(username);
    		
    		log.info("LogKey: {} - User: {} - Enter: {}.{}() with argument[s] = {}",
                    keyString, username, joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    	} else {
    		log.info("LogKey: {} - Enter: {}.{}() with argument[s] = {}", keyString, joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    	}

        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("LogKey: {} - Exit: {}.{}()", keyString, joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("LogKey: {} - Illegal argument: {} in {}.{}()", keyString, Arrays.toString(joinPoint.getArgs()),
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        } catch (Exception e) {
        	log.error("LogKey: {} - Exception: {} in {}.{}()", keyString, e.getMessage(),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        } finally {
        	userActionManager.save(userAct);
        }
        
    }
}
