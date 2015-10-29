package com.igitras.boot.common;

/**
 * Created by mason on 10/29/15.
 */
public class RequestInfoHolder {
    private static final ThreadLocal<Boolean> THREAD_IGNORE_AUTH = new ThreadLocal<>();

    public static Boolean getThreadIgnoreAuth() {
        return THREAD_IGNORE_AUTH.get();
    }

    public static void setThreadIgnoreAuth(Boolean threadIgnoreIam) {
        THREAD_IGNORE_AUTH.set(threadIgnoreIam);
    }

    public static void removeThreadIgnoreAuth() {
        THREAD_IGNORE_AUTH.remove();
    }
}
