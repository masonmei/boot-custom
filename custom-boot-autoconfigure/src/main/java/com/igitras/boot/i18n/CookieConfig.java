package com.igitras.boot.i18n;

import com.google.common.base.MoreObjects;

/**
 * Created by mason on 11/17/15.
 */
public class CookieConfig {
    private static final String I18N_COOKIE_NAME = "locale_cookie";
    private static final Integer MAX_COOKIE_AGE_IN_SECOND = 3600;

    private String name = I18N_COOKIE_NAME;
    private int maxAge = MAX_COOKIE_AGE_IN_SECOND;
    private String domain;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("maxAge", maxAge)
                .add("domain", domain)
                .toString();
    }
}
