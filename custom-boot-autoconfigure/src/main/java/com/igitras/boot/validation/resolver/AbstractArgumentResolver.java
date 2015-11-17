package com.igitras.boot.validation.resolver;

import org.springframework.web.context.request.RequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mason on 11/16/15.
 */
public abstract class AbstractArgumentResolver<T extends Resolvable> implements ResolvableResolver<T> {

    /**
     * Static utility method that extracts the current device from the web request.
     * Encapsulates the {@link HttpServletRequest#getAttribute(String)} lookup.
     *
     * @param request the servlet request
     * @return the current device, or null if no device has been resolved for the request
     */
    public T getCurrentResolvable(HttpServletRequest request) {
        Object attribute = request.getAttribute(currentAttributeName());
        if (attribute != null) {
            return (T) attribute;
        }
        return null;
    }

    /**
     * Static utility method that extracts the current resolvable from the web request.
     * Encapsulates the {@link HttpServletRequest#getAttribute(String)} lookup.
     * Throws a runtime exception if the current resolvable has not been resolved.
     *
     * @param request the servlet request
     * @return the current resolvable
     */
    public T getRequiredCurrentResolvable(HttpServletRequest request) {
        T resolvable = getCurrentResolvable(request);
        if (resolvable == null) {
            throw new IllegalStateException("No current resolvable is set in this request and one is required");
        }
        return resolvable;
    }

    /**
     * Static utility method that extracts the current resolvable from the request attributes map.
     * Encapsulates the {@link HttpServletRequest#getAttribute(String)} lookup.
     *
     * @param attributes the request attributes
     * @return the current resolvable, or null if no device has been resolved for the request
     */
    public T getCurrentResolvable(RequestAttributes attributes) {
        return (T) attributes.getAttribute(currentAttributeName(), RequestAttributes.SCOPE_REQUEST);
    }
}
