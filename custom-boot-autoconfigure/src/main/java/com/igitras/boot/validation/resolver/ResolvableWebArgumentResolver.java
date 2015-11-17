package com.igitras.boot.validation.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created by mason on 11/16/15.
 */
public abstract class ResolvableWebArgumentResolver extends AbstractArgumentResolver implements WebArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
        if (Resolvable.class.isAssignableFrom(methodParameter.getParameterType())) {
            return getCurrentResolvable(webRequest);
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }

}
