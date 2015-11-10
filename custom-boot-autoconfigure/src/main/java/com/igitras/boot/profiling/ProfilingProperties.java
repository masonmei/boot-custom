package com.igitras.boot.profiling;

import com.igitras.boot.utils.Constrains;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by mason on 11/5/15.
 */
@ConfigurationProperties(prefix = Constrains.PROFILE_PREFIX)
public class ProfilingProperties {

    private long warningThreshold;
    private long logFrequency;

    public long getWarningThreshold() {
        return warningThreshold;
    }

    public void setWarningThreshold(long warningThreshold) {
        this.warningThreshold = warningThreshold;
    }

    public long getLogFrequency() {
        return logFrequency;
    }

    public void setLogFrequency(long logFrequency) {
        this.logFrequency = logFrequency;
    }
}
