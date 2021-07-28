package com.soft.util;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author 野性的呼唤
 * @date 2021/7/28 17:40
 * @description
 */
public class WeChatSignUtil {

    // 参考文档 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3

    public static String getSign(SortedMap<String, String> map, String privateKey) {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String, String>> es = map.entrySet();
        for (Map.Entry<String, String> e : es) {
            String k = e.getKey();
            String v = e.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                builder.append(k).append("=").append(v).append("&");
            }
        }
        return encrypt(builder, privateKey);
    }

    public static String getSign(Object o, String privateKey) {
        ArrayList<String> list = new ArrayList<>();
        Class<?> cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            // 如果参数的值为空不参与签名 传送的sign参数不参与签名 key为密钥不能作为用户字段参数
            try {
                if (f.get(o) != null && f.get(o) != ""&&!"sign".equals(f.getName())&&!"key".equals(f.getName())) {
                    list.add(f.getName() + "=" + f.get(o) + "&");
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                return null;
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(arrayToSort[i]);
        }
        return encrypt(builder, privateKey);
    }

    private static String encrypt(StringBuilder builder, String privateKey) {
        String result = builder.toString();
        System.out.println(result);
        if (StringUtil.isBlank(result)) {
            return null;
        }
        result += "key=" + privateKey;
        System.out.println(result);
        result = StringUtil.md5(result);
        if (StringUtil.isBlank(result)) {
            return null;
        }
        return result.toUpperCase();
    }

}
