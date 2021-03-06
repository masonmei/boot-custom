package com.igitras.boot.limit;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by mason on 11/10/15.
 */
public class IPTracker {
    private final String ipAddress;
    private final List<Long> timestamps;
    private final int bandTimeInMillis;
    private final int periodInMillis;
    private Long bandUntilTime;

    public IPTracker(String ipAddress, long currentTimeInMillis, int periodInMillis, int bandTimeInMillis) {
        this.ipAddress = ipAddress;
        this.bandTimeInMillis = bandTimeInMillis;
        this.periodInMillis = periodInMillis;
        this.timestamps = new CopyOnWriteArrayList<>();
        addEntry(currentTimeInMillis);
        this.bandUntilTime = currentTimeInMillis - bandTimeInMillis;
    }


    public boolean hasReachedLimit(int maxRequestsPerPeriod, long currentTimeInMillis) {
        if (currentTimeInMillis < bandUntilTime) {
            return true;
        }

        addEntry(currentTimeInMillis);
        if (timestamps.size() >= maxRequestsPerPeriod) {
            this.bandUntilTime = bandTimeInMillis + currentTimeInMillis;
            return true;
        }

        return false;
    }

    private void addEntry(long currentTimeInMillis) {
        fixRange(currentTimeInMillis);
        timestamps.add(currentTimeInMillis);
    }

    private void fixRange(long currentTimeInMillis) {
        List<Long> toRemove = this.timestamps.stream()
                .filter(timestamp -> timestamp <= currentTimeInMillis - periodInMillis)
                .collect(Collectors.toList());

        if (toRemove.size() > 0) {
            timestamps.removeAll(toRemove);
        }
    }
}
