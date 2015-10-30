package com.igitras.boot.trace;

import com.igitras.boot.utils.Constrains;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for Tracing purpose
 * <p>
 * Created by mason on 10/30/15.
 */
@ConfigurationProperties(prefix = Constrains.TRACE_PREFIX)
public class TraceProperties {

    private String traceHeaderName = Constrains.TRACE_HEADER_NAME;
    private String traceStartTimeHeaderName = Constrains.TRACE_TIMESTAMP_HEADER_NAME;

    public String getTraceHeaderName() {
        return traceHeaderName;
    }

    public void setTraceHeaderName(String traceHeaderName) {
        this.traceHeaderName = traceHeaderName;
    }

    public String getTraceStartTimeHeaderName() {
        return traceStartTimeHeaderName;
    }

    public void setTraceStartTimeHeaderName(String traceStartTimeHeaderName) {
        this.traceStartTimeHeaderName = traceStartTimeHeaderName;
    }
}
