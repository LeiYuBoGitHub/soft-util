package com.soft.util;

import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 野性的呼唤
 * @date 2021/7/28 17:55
 * @description
 */
class WeChatSignUtilTest {

    @Test
    void getSign() {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("appid", "wxd930ea5d5a258f4f");
        map.put("mch_id", "10000100");
        map.put("device_info", "1000");
        map.put("body", "test");
        map.put("nonce_str", "ibuaiVcKdpRxkhJA");
        String result = WeChatSignUtil.getSign(map, "192006250b4c09247ec02edce69f6a2d");
        System.out.println(result);
    }

    @Test
    void testGetSign() {
    }
}