package com.igitras.boot.validation.resolver;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mason on 11/16/15.
 */
public class AnnotationResolvableResolver<T extends Resolvable> implements ResolvableResolver {

    private final Class<T> classType;
    private final String attributeName;

    public AnnotationResolvableResolver(Class<T> classType, String attributeName) {
        Assert.notNull(classType, "Resolvable class name must not be null.");
        this.classType = classType;
        if (StringUtils.isEmpty(attributeName)) {
            attributeName = String.format("current%s", classType.getSimpleName());
        }
        this.attributeName = attributeName;
    }

    @Override
    public T resolve(HttpServletRequest request) {
        try {
            T t = classType.newInstance();



            return t;
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        }
        return null;
    }

    @Override
    public Class<T> methodArgumentResolvable() {
        return classType;
    }

    @Override
    public String currentAttributeName() {
        return attributeName;
    }



}
