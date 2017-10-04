package com.github.emboss.siphash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author <a href="mailto:Martin.Bosslet@googlemail.com">Martin Bosslet</a>
 */
 
public class SipHashTest {
    
    private static final SipKey SPEC_KEY = new SipKey(Utils.bytesOf(
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f
    ));
    
    private static final byte[] SPEC_MSG = Utils.bytesOf(
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e
    );

    private static final byte[] SPEC_MSG_CODEC = Utils.bytesOf(
            0x0a, 0x0b, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x08, 0x09, 0x0f, 0x0b, 0x0c, 0x0d, 0x0e
    );
    
    public SipHashTest() {
    }

    @Test
    public void spec() {
        long digest = SipHash.digest(SPEC_KEY, SPEC_MSG);
        assertEquals(0xa129ca6149be45e5L, digest);
    }

    @Test
    public void spec_codec() {
        long digest = SipHash.digest(SPEC_KEY, SPEC_MSG_CODEC);
        assertEquals(0xa129ca6149be45e5L, digest);
    }
    
    @Test
    public void emptyString() throws Exception {
        long digest = SipHash.digest(SPEC_KEY, "".getBytes("UTF8"));
        assertEquals(0x726fdb47dd0e0e31L, digest);
    }
    
    @Test
    public void oneByte() throws Exception {
        long digest = SipHash.digest(SPEC_KEY, "a".getBytes("UTF8"));
        assertEquals(0x2ba3e8e9a71148caL, digest);
    }

    @Test
    public void sixBytes() throws Exception {
        long digest = SipHash.digest(SPEC_KEY, "abcdef".getBytes("UTF8"));
        assertEquals(0x2a6e77e733c7c05dL, digest);
    }
    
    @Test
    public void sevenBytes() throws Exception {
        long digest = SipHash.digest(SPEC_KEY, "SipHash".getBytes("UTF8"));
        assertEquals(0x8325093242a96f60L, digest);
    }

    @Test
    public void eightBytes() throws Exception {
        long digest = SipHash.digest(SPEC_KEY, "12345678".getBytes("UTF8"));
        assertEquals(0x2130609caea37ebL, digest);
    }

    @Test
    public void oneMillionZeroBytes() throws Exception {
        long digest = SipHash.digest(SPEC_KEY, Utils.byteTimes(0, 1000000));
        assertEquals(0x28205108397aa742L, digest);
    }
}
