package com.igitras.boot.utils;

/**
 * Created by mason on 10/29/15.
 */
public abstract class Constrains {
    public static final String CUSTOMER_BOOT_BASE = "custom.boot.";
    public static final String ENABLED = "enabled";

    public static final String SPRING_FOX_PREFIX = CUSTOMER_BOOT_BASE + "spring.fox";

    public static final String IP_PERMISSION_PREFIX = CUSTOMER_BOOT_BASE + "iplist";

    public static final String TRACE_PREFIX = CUSTOMER_BOOT_BASE + "trace";
    public static final String TRACE_HEADER_NAME = "x-trace-header-name";
    public static final String TRACE_TIMESTAMP_HEADER_NAME = "x-trace-timestamp-header-name";

    public static final String ASYNC_PREFIX = CUSTOMER_BOOT_BASE + "async";

    public static final String PROFILE_PREFIX = CUSTOMER_BOOT_BASE + "profiling";

    public static final String LIMIT_PREFIX = CUSTOMER_BOOT_BASE + "limit";

    public static final String I18N_PREFIX = CUSTOMER_BOOT_BASE + "i18n";

    public static final String ARGS_PREFIX = CUSTOMER_BOOT_BASE + "args";

    public static final String SSL_DUAL_PREFIX = CUSTOMER_BOOT_BASE + "ssl.dual";

}
