package com.igitras.boot.utils;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mason on 10/29/15.
 */
public class IpV4UtilsTest {
    private final String IP_IN_STRING = "192.168.1.1";
    private final String IP_IN_STRING_OUT_RANGE = "192.168.1.5/30";
    private final String IP_IN_STRING_IN_RANGE = "192.168.1.3/30";
    private final String INVALID_IP_IN_STRING = "900.10.1.2";

    private final long IP_IN_LONG = 3232235777L;
    @Test
    public void testLongToIp() throws Exception {
        long longIp = IpV4Utils.ipToLong(IP_IN_STRING);
        assertEquals(IP_IN_LONG, longIp);
    }

    @Test
    public void testIpToLong() throws Exception {
        String ipString = IpV4Utils.longToIp(IP_IN_LONG);
        assertEquals(IP_IN_STRING, ipString);
    }

    @Test
    public void testIsPrivate() throws Exception {
        boolean iPv4Private = IpV4Utils.isPrivate(IP_IN_STRING);
        assertTrue(iPv4Private);
    }

    @Test
    public void testIsValid() throws Exception {
        boolean isIpV4Valid = IpV4Utils.isValid(IP_IN_STRING);
        assertTrue(isIpV4Valid);
        isIpV4Valid = IpV4Utils.isValid(INVALID_IP_IN_STRING);
        assertFalse(isIpV4Valid);
    }

    @Test
    public void testIsInRange() throws Exception {
        boolean ipInRange = IpV4Utils.isInRange(IP_IN_STRING_OUT_RANGE, IP_IN_STRING);
        assertFalse(ipInRange);
        ipInRange = IpV4Utils.isInRange(IP_IN_STRING_IN_RANGE, IP_IN_STRING);
        assertTrue(ipInRange);
    }

    @Test
    public void testIsInRanges() throws Exception {
        boolean res = IpV4Utils.isInRanges(Arrays.asList(IP_IN_STRING_IN_RANGE, IP_IN_STRING_OUT_RANGE), IP_IN_STRING);
        assertTrue(res);
    }
}