package com.igitras.boot.limit;

import com.igitras.boot.utils.Constrains;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by mason on 10/29/15.
 */
@ConfigurationProperties(prefix = Constrains.LIMIT_PREFIX)
public class LimiterProperties {
    private String configPath;
    private String configFile;
    private int refreshInterval;
    private int maxRequestsPerPeriod;
    private int period;
    private int bandTime;

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(int refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    public int getMaxRequestsPerPeriod() {
        return maxRequestsPerPeriod;
    }

    public void setMaxRequestsPerPeriod(int maxRequestsPerPeriod) {
        this.maxRequestsPerPeriod = maxRequestsPerPeriod;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getBandTime() {
        return bandTime;
    }

    public void setBandTime(int bandTime) {
        this.bandTime = bandTime;
    }
}
