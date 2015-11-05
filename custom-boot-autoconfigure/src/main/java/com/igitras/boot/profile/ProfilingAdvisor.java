/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.igitras.boot.profile;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Profiling Advisor.
 * <p>
 * Created by meidongxu on 7/6/15.
 */
public class ProfilingAdvisor extends AbstractPointcutAdvisor {

    private final List<String> methodNamePatterns;

    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public ProfilingAdvisor(List<String> methodNamePatterns) {
        this.methodNamePatterns = methodNamePatterns;
    }

    @Override
    public Pointcut getPointcut() {
        return new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return method.isAnnotationPresent(Profiling.class) || match(getFullName(method));
            }
        };
    }

    public String getFullName(Method method){
        return String.format("%s.%s", method.getDeclaringClass().getName(), method.getName());
    }

    public boolean match(String methodName) {
        if (CollectionUtils.isEmpty(methodNamePatterns)) {
            return false;
        }

        for (String pattern : methodNamePatterns) {
            if (methodName.matches(pattern)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
