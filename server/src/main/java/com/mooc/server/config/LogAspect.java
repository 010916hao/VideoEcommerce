package com.mooc.server.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.mooc.server.util.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@Aspect
@Component
public class LogAspect {

    private final static Logger LOG = LoggerFactory.getLogger(com.mooc.server.config.LogAspect.class);

    /** Define a point */
    @Pointcut("execution(public * com.mooc.*.controller..*Controller.*(..))")
    public void controllerPointcut() {}

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // LOG ID
        MDC.put("UUID", UuidUtil.getShortUuid());

        // Print request log
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        LOG.info("TEST: " + name);
        // Print crud operations
        String nameCn = "";
        if (name.contains("list") || name.contains("query")) {
            nameCn = "QUERY";
        } else if (name.contains("save")) {
            nameCn = "SAVE";
        } else if (name.contains("delete")) {
            nameCn = "DELETE";
        } else {
            nameCn = "OPERATIONS";
        }

        // use reflection to get business name
        Class clazz = signature.getDeclaringType();
        Field field;
        String businessName = "";
        try {
            field = clazz.getField("BUSINESS_NAME");
            if (!StringUtils.isEmpty(field)) {
                businessName = (String) field.get(clazz);
            }
        } catch (NoSuchFieldException e) {
            LOG.error("Don't get business name");
        } catch (SecurityException e) {
            LOG.error("Fail to get business name", e);
        }

        // print request information
        LOG.info("------------- 【{}】{} starts -------------", businessName, nameCn);
        LOG.info("REQUEST ADDRESS: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("CLASS.METHOD: {}.{}", signature.getDeclaringTypeName(), name);
        LOG.info("REMOTE ADDRESS: {}", request.getRemoteAddr());

        // print request parameters
        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        // filter some string
        String[] excludeProperties = {"shard"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        // Cannot print Null, but can print long string, such as image.
        LOG.info("REQUEST PARAMETER: {}", JSONObject.toJSONString(arguments, excludefilter));
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // exclude some string
        String[] excludeProperties = {"password", "shard"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOG.info("RESPOND RESULT: {}", JSONObject.toJSONString(result, excludefilter));
        LOG.info("------------- END TIME：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

}