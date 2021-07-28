package com.soft.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 野性的呼唤
 * @date 2021/7/14 17:38
 * @description
 */
public class StringUtil {

    public static final String  NUMBER_FORMAT = "^[0-9]*$";

    public static String mapToXml(SortedMap<String, Object> sortedMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            sb.append("<").append(entry.getKey()).append(">");
            sb.append(entry.getValue());
            sb.append("</").append(entry.getKey()).append(">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    public static String md5(String input) {
        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ignored) {
            ignored.printStackTrace();
        }
        if (messageDigest == null) {
            return null;
        }
        // 使用指定的字节更新摘要
        messageDigest.update(input.getBytes());
        // 获得密文
        byte[] md = messageDigest.digest();
        // 把密文转换成十六进制的字符串形式
        StringBuilder hexString = new StringBuilder();
        // 字节数组转换为 十六进制 数
        for (byte b : md) {
            String shaHex = Integer.toHexString(b & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString();
    }

    /**
     * 过滤字符串 取字符串中的数字
     * @param s
     * @return 结果
     */
    public static Integer filterStringToNumber(String s){
        if (s == null|| "".equals(s)){
            return 0;
        }else{
            String regEx="[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(s);
            return Integer.valueOf(m.replaceAll("").trim());
        }
    }

    /**
     * 随机生成UUID
     * @return 结果
     */
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        return s.replace("-", "");
    }

    /**
     * 自定义随机数
     * @param min   最大数
     * @param max   最大数
     * @return 结果
     */
    public static int randomNumber(int min,int max){
        Random random = new Random();
        int num = random.nextInt(max)%(max - min + 1) + min;
        return num;
    }

    /**
     * 截取网页链接最后的名字
     * 例如 http://www.baidu.com/img/1234.jpg 输出 1234.jpg
     * @param url 地址
     * @return 结果
     */
    public static String getLastUrl(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }

    /**
     * 截取网页文件路径
     * 例如 http://www.baidu.com/img/1234.jpg 输出 http://www.baidu.com/img/
     * @param url 地址
     * @return 结果
     */
    public static String getFirstUrl(String url){
        return url.substring(0,url.lastIndexOf("/") + 1);
    }

    /**
     * 将网页GET请求带参数的进行拼接
     * @param url 接口地址
     * @param map 参数<k,v>
     * @return 结果
     */
    public static String urlHandle(String url, Map<String, Object> map){
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : map.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(map.get(key));
            i++;
        }
        url += param;
        return url;
    }

    /**
     * 字符串是否为整数(允许小数)
     * @param str 字符
     * @return 结果
     */
    public static boolean isNumeric(String str) {
        return StringUtil.isNotEmpty(str) && str.matches(StringUtil.NUMBER_FORMAT);
    }

    /**
     * 判断字符串是否为空白，常见几种情况如下：
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (null == str || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空白，常见几种情况如下：
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断字符串是否为空，常见几种情况如下：
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     */
    public static boolean isEmpty(String str) {
        return null == str || str.length() == 0;
    }

    /**
     * 判断字符串是否不为空，常见几种情况如下：
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }

}
