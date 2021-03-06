package com.igitras.boot.common;

/**
 * Created by mason on 10/29/15.
 */
public class RequestInfoHolder {
    private static final ThreadLocal<Boolean> THREAD_IGNORE_AUTH = new ThreadLocal<>();
    private static final ThreadLocal<String> THREAD_TRACE_ID = new ThreadLocal<>();
    private static final ThreadLocal<String> THREAD_TRACE_TIMESTAMP = new ThreadLocal<>();

    public static Boolean getThreadIgnoreAuth() {
        return THREAD_IGNORE_AUTH.get();
    }

    public static void setThreadIgnoreAuth(Boolean ignoreAuth) {
        THREAD_IGNORE_AUTH.set(ignoreAuth);
    }

    public static void removeThreadIgnoreAuth() {
        THREAD_IGNORE_AUTH.remove();
    }

    public static String getThreadTraceId() {
        return THREAD_TRACE_ID.get();
    }

    public static void setThreadTraceId(String requestId) {
        THREAD_TRACE_ID.set(requestId);
    }

    public static void removeThreadTraceId() {
        THREAD_TRACE_ID.remove();
    }

    public static String getThreadTraceTimestamp() {
        return THREAD_TRACE_TIMESTAMP.get();
    }

    public static void setThreadTraceTimestamp(String traceTimestamp) {
        THREAD_TRACE_TIMESTAMP.set(traceTimestamp);
    }

    public static void removeThreadTraceTimestamp() {
        THREAD_TRACE_TIMESTAMP.remove();
    }
}
