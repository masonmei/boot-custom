package com.igitras.boot.validation.resolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mason on 11/16/15.
 */
public interface ResolvableResolver<T extends Resolvable> {
    Resolvable resolve(HttpServletRequest request);

    Class<T> methodArgumentResolvable();

    String currentAttributeName();

}
