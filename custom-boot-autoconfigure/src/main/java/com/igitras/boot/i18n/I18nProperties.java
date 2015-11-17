package com.igitras.boot.i18n;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.igitras.boot.utils.Constrains.I18N_PREFIX;

/**
 * Created by mason on 11/17/15.
 */
@ConfigurationProperties(prefix = I18N_PREFIX)
public class I18nProperties {
    private static final String DEFAULT_I18N_MSG_BASE_NAME = "classpath:/i18n/messages";
    private static final String DEFAULT_LOCALE_PARAM = "locale";
    private static final String DEFAULT_LOCALE = "en";

    private String msgBaseName = DEFAULT_I18N_MSG_BASE_NAME;
    private String localParam = DEFAULT_LOCALE_PARAM;
    private String defaultLocale = DEFAULT_LOCALE;

    private ResolverType resolverType = ResolverType.COOKIE;

    private CookieConfig cookie;


    public String getMsgBaseName() {
        return msgBaseName;
    }

    public void setMsgBaseName(String msgBaseName) {
        this.msgBaseName = msgBaseName;
    }

    public String getLocalParam() {
        return localParam;
    }

    public void setLocalParam(String localParam) {
        this.localParam = localParam;
    }

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public void setDefaultLocale(String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public ResolverType getResolverType() {
        return resolverType;
    }

    public void setResolverType(ResolverType resolverType) {
        this.resolverType = resolverType;
    }

    public CookieConfig getCookie() {
        return cookie;
    }

    public void setCookie(CookieConfig cookie) {
        this.cookie = cookie;
    }

}
