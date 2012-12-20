package com.github.emboss.siphash;

/**
 * 
 * @author <a href="mailto:Martin.Bosslet@googlemail.com">Martin Bosslet</a>
 */
public class Utils {
    
    private Utils() {}
    
    public static byte[] bytesOf(Integer... bytes) {
        byte[] ret = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            ret[i] = bytes[i].byteValue();
        }
        return ret;
    }
    
    public static byte[] byteTimes(int b, int times) {
        byte[] ret = new byte[times];
        for (int i = 0; i < times; i++) {
            ret[i] = (byte)b;
        }
        return ret;
    }
}
