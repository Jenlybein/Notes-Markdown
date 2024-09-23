package test.schedule.test;

import org.junit.Test;
import test.schedule.util.MD5Util;

public class TestMD5 {
    @Test
    public void testMD51(){
        String encrypt = MD5Util.encrypt("123456");
        System.out.println(encrypt);
    }
}
