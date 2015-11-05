package com.igitras.boot.profile;

import com.igitras.boot.utils.Constrains;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by mason on 11/5/15.
 */
@ConfigurationProperties(prefix = Constrains.PROFILE_PREFIX)
public class ProfilingProperties {

    private List<String> methodNamePatterns;
    private long warningThreshold;
    private long logFrequency;

    public List<String> getMethodNamePatterns() {
        return methodNamePatterns;
    }

    public void setMethodNamePatterns(List<String> methodNamePatterns) {
        this.methodNamePatterns = methodNamePatterns;
    }


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
