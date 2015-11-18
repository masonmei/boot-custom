package com.igitras.boot.resolver;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.igitras.boot.utils.Constrains.ARGS_PREFIX;

/**
 * Created by mason on 11/18/15.
 */
@ConfigurationProperties(prefix = ARGS_PREFIX)
public class MethodArgumentResolverProperties {

}
