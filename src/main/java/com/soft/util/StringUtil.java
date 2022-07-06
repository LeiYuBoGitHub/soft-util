package com.soft.util;

import cn.hutool.core.util.StrUtil;

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
public class StringUtil extends StrUtil {

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
        return random.nextInt(max)%(max - min + 1) + min;
    }

    /**
     * 生成多少位数字
     * @param seat 位
     * @return 结果
     */
    public static String randomNumber(int seat){
        StringBuilder count = new StringBuilder();
        for (int i = 0;i < 9; i++){
            int a = randomNumber(0, 9);
            count.append(a);
        }
        return count.toString();
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
     * 字符串切割
     * @param txt 文本
     * @param spot 字符
     * @param position 左/右
     * @return 结果
     */
    public static String cutting(String txt, String spot, String position){
        int length = txt.length();
        int spotLength = spot.length();
        int a = txt.indexOf(spot);
        if (position.equals("l")){
            return txt.substring(0,a);
        }else if (position.equals("r")){
            return txt.substring(a+spotLength,length);
        }else{
            return null;
        }
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

}
