package com.igitras.boot.springfox;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.igitras.boot.utils.Constrains.ENABLED;
import static com.igitras.boot.utils.Constrains.SPRING_FOX_PREFIX;
import static java.util.stream.Collectors.toSet;

/**
 * Spring Fox AutoConfiguration
 * <p>
 * Created by mason on 10/29/15.
 */
@Configuration
@ConditionalOnClass({Docket.class})
@ConditionalOnWebApplication
@EnableConfigurationProperties(SpringFoxProperties.class)
@ConditionalOnProperty(prefix = SPRING_FOX_PREFIX, name = ENABLED, havingValue = "true", matchIfMissing = true)
@EnableSwagger2
public class SpringFoxAutoConfiguration {
    @Autowired
    private SpringFoxProperties properties;

    @Bean
    public Docket apiDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.groupName(properties.getGroupName())
                .apiInfo(buildApiInfo())
                .select()
                .paths(buildPaths())
                .build();
        return docket;
    }

    private Predicate<String> buildPaths() {
        return Predicates.or(
                properties.getPatterns().stream()
                        .map(PathSelectors::ant)
                        .collect(toSet())
        );
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfo(properties.getApiInfo().getTitle(),
                properties.getApiInfo().getDescription(),
                properties.getApiInfo().getVersion(),
                properties.getApiInfo().getTermsOfServiceUrl(),
                properties.getApiInfo().getContact(),
                properties.getApiInfo().getLicense(),
                properties.getApiInfo().getLicenseUrl());
    }
}
