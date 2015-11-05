/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.igitras.boot.profile;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by meidongxu on 7/5/15.
 */
public class ProfilingInterceptor implements MethodInterceptor {
    private static final Logger log = LoggerFactory.getLogger(ProfilingInterceptor.class);

    private static final ConcurrentHashMap<String, MethodStat> METHOD_STATS_MAP = new ConcurrentHashMap<>();

    private final long methodWarningThreshold;
    private final long logFrequency;

    public ProfilingInterceptor(long methodWarningThreshold, long logFrequency) {
        if (methodWarningThreshold <= 0) {
            methodWarningThreshold = 5000;
        }

        if (logFrequency <= 0) {
            logFrequency = 100;
        }

        this.methodWarningThreshold = methodWarningThreshold;
        this.logFrequency = logFrequency;
    }

    @Override
    public Object invoke(MethodInvocation method) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = method.getMethod().getName();
        try {
            log.debug("start to process method: {} at: {}", methodName, startTime);
            return method.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long elapsed = endTime - startTime;
            log.info("finished to process method: {} at {}, using: {}ms", methodName, endTime, elapsed);
            if (elapsed > methodWarningThreshold) {
                log.warn("found slow method execution: methodName : {}, elapsed: {}ms", methodName, elapsed);
            }

            updateStat(methodName, elapsed);
        }
    }

    private void updateStat(String methodName, long elapsedInMillis) {
        MethodStat methodStat = METHOD_STATS_MAP.get(methodName);
        if (methodStat == null) {
            METHOD_STATS_MAP.put(methodName, new MethodStat(methodName));
            methodStat = METHOD_STATS_MAP.get(methodName);
        }

        methodStat.count++;
        methodStat.totalTime += elapsedInMillis;
        if (elapsedInMillis > methodStat.maxTime) {
            methodStat.maxTime = elapsedInMillis;
        }

        if (elapsedInMillis < methodStat.minTime) {
            methodStat.minTime = elapsedInMillis;
        }

        if (methodStat.count % logFrequency == 0) {
            logStats(methodStat);
        }
    }

    private void logStats(MethodStat stat) {
        long avgTime = stat.totalTime / stat.count;
        log.info("MethodStat: [methodName: {}, totalTime: {}ms, execution:{}, averageTime: {}ms, min:{}ms, max:{}ms]",
                stat.methodName, stat.totalTime, stat.count, avgTime, stat.minTime, stat.maxTime);
        stat.reset();
    }

    static class MethodStat {
        private final String methodName;
        private long count;
        private long totalTime;
        private long maxTime = Long.MIN_VALUE;
        private long minTime = Long.MAX_VALUE;

        public MethodStat(String methodName) {
            this.methodName = methodName;
        }

        public String getMethodName() {
            return methodName;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        public long getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(long totalTime) {
            this.totalTime = totalTime;
        }

        public long getMaxTime() {
            return maxTime;
        }

        public void setMaxTime(long maxTime) {
            this.maxTime = maxTime;
        }

        public long getMinTime() {
            return minTime;
        }

        public void setMinTime(long minTime) {
            this.minTime = minTime;
        }

        public void reset() {
            count = 0;
            totalTime = 0;
            maxTime = Long.MIN_VALUE;
            minTime = Long.MAX_VALUE;
        }
    }
}
