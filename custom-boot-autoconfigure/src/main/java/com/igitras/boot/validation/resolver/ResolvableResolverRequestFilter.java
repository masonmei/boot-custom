package com.igitras.boot.validation.resolver;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mason on 11/16/15.
 */
public class ResolvableResolverRequestFilter<T extends ResolvableResolver> extends OncePerRequestFilter {
    private final T resolvableResolver;

    public ResolvableResolverRequestFilter(T resolvableResolver) {
        this.resolvableResolver = resolvableResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Resolvable resolvable = resolvableResolver.resolve(request);
        request.setAttribute(resolvableResolver.currentAttributeName(), resolvable);
        filterChain.doFilter(request, response);
    }
}
