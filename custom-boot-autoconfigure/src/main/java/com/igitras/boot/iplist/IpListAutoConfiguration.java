package com.igitras.boot.iplist;

import com.igitras.boot.utils.FileWatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.io.File;

/**
 * Created by mason on 10/29/15.
 */
@Configuration
@ConditionalOnClass({Servlet.class, Filter.class})
@ConditionalOnWebApplication
@EnableConfigurationProperties(IpListProperties.class)
public class IpListAutoConfiguration {

    @Autowired
    private IpListProperties properties;

    @Bean
    public FilterRegistrationBean ipListFilterRegistrationBean(){
        IpFilter filter = new IpFilter(buildFileWatcher());
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    private FileWatcher<IpHolder> buildFileWatcher() {
        int refreshInterval = properties.getRefreshInterval();
        return new FileWatcher<IpHolder>(refreshInterval, resolveConfigFile(), IpHolder.class);
    }

    private File resolveConfigFile(){
        String fileDirectory = properties.getConfigPath();
        String fileName = properties.getConfigFile();
        File configFile;
        if(StringUtils.hasText(fileDirectory)){
            configFile = new File(fileDirectory.concat(fileName));
        } else {
            String configFilePath = getClass().getClassLoader().getResource(fileName).getFile();
            configFile = new File(configFilePath);
        }

        if(!configFile.exists()){
            configFile = null;
        }
        return configFile;
    }
}
