/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.igitras.boot.profile;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static com.igitras.boot.utils.Constrains.ENABLED;
import static com.igitras.boot.utils.Constrains.PROFILE_PREFIX;

/**
 * Created by meidongxu on 7/6/15.
 */
@Configuration
@ConditionalOnProperty(prefix = PROFILE_PREFIX, name = ENABLED, havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(ProfilingProperties.class)
public class ProfilingAutoConfiguration {
    @Autowired
    private ProfilingProperties properties;

    @Bean
    public ProfilingAdvisor profileAdvisor(Advice advice) {
        ProfilingAdvisor advisor = new ProfilingAdvisor(properties.getMethodNamePatterns());
        advisor.setAdvice(advice);
        return advisor;
    }

    @Bean
    public Advice advice(){
        return new ProfilingInterceptor(properties.getWarningThreshold(), properties.getLogFrequency());
    }

}
