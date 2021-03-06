package com.igitras.boot.iplist;

import java.util.Collections;
import java.util.List;

/**
 * Created by mason on 10/29/15.
 */
public class IpHolder {
    private List<String> deny;
    private List<String> allow;

    public List<String> getDeny() {
        return deny;
    }

    public void setDeny(List<String> deny) {
        this.deny = Collections.unmodifiableList(deny == null ? Collections.emptyList() : deny);
    }

    public List<String> getAllow() {
        return allow;
    }

    public void setAllow(List<String> allow) {
        this.allow = Collections.unmodifiableList(allow == null ? Collections.emptyList() : allow);
    }

}
