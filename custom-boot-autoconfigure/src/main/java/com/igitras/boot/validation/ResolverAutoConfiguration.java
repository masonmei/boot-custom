package com.igitras.boot.validation;

import com.igitras.boot.validation.resolver.AbstractArgumentResolver;
import com.igitras.boot.validation.resolver.ResolvableHandlerInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

import static com.igitras.boot.utils.Constrains.ARGS_PREFIX;
import static com.igitras.boot.utils.Constrains.ENABLED;

/**
 * Created by mason on 11/16/15.
 */
@Configuration
@ConditionalOnProperty(prefix = ARGS_PREFIX, name = ENABLED, havingValue = "true", matchIfMissing = false)
@ConditionalOnClass({ResolvableHandlerInterceptor.class, AbstractArgumentResolver.class})
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class ResolverAutoConfiguration {

    @Configuration
    @ConditionalOnWebApplication
    protected static class DeviceResolverMvcConfiguration extends WebMvcConfigurerAdapter {


        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//            List<HandlerMethodArgumentResolver> resolvers = buildResolvers();
//            argumentResolvers.addAll(resolvers);
        }

        private List<HandlerMethodArgumentResolver> buildResolvers() {
            return null;
        }

    }

}
