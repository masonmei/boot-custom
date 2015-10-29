package com.igitras.boot.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mason on 10/29/15.
 */
public class IpUtilsTest {
    private final String IP_IN_STRING = "192.168.1.1";
    private final String IP_IN_STRING_OUT_RANGE = "192.168.1.5/30";
    private final String IP_IN_STRING_IN_RANGE = "192.168.1.3/30";
    private final String INVALID_IP_IN_STRING = "900.10.1.2";

    private final long IP_IN_LONG = 3232235777L;
    @Test
    public void testLongToIpV4() throws Exception {
        long longIp = IpUtils.ipV4ToLong(IP_IN_STRING);
        assertEquals(IP_IN_LONG, longIp);
    }

    @Test
    public void testIpV4ToLong() throws Exception {
        String ipString = IpUtils.longToIpV4(IP_IN_LONG);
        assertEquals(IP_IN_STRING, ipString);
    }

    @Test
    public void testIsIPv4Private() throws Exception {
        boolean iPv4Private = IpUtils.isIPv4Private(IP_IN_STRING);
        assertTrue(iPv4Private);
    }

    @Test
    public void testIsIPv4Valid() throws Exception {
        boolean isIpV4Valid = IpUtils.isIPv4Valid(IP_IN_STRING);
        assertTrue(isIpV4Valid);
        isIpV4Valid = IpUtils.isIPv4Valid(INVALID_IP_IN_STRING);
        assertFalse(isIpV4Valid);
    }

    @Test
    public void testIsIPInRange() throws Exception {
        boolean ipInRange = IpUtils.isIPInRange(IP_IN_STRING_OUT_RANGE, IP_IN_STRING);
        assertFalse(ipInRange);
        ipInRange = IpUtils.isIPInRange(IP_IN_STRING_IN_RANGE, IP_IN_STRING);
        assertTrue(ipInRange);
    }
}