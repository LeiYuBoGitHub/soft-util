package com.soft.util.file;

import java.io.*;

/**
 * @author 野性的呼唤
 * @date 2021/7/27 18:54
 * @description
 */
public class FileUtil {

    /**
     * 输出文本到文件
     * @param text 文本
     * @param filePath 路径
     */
    public static void writeText(String text, String filePath) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(filePath);
            fos.write(text.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文本
     * @param file
     * File对象
     * @param encoding 字符编码
     */
    public static String readText(File file, String encoding){
        // 判断是否是文件或者文件是否存在
        if(!file.isFile() && !file.exists()) {
            System.out.println("无法读取 可能不是文件或者文件不存在");
            return null;
        }
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
            return readText(read);
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建文件
     * @param inputStream
     * 输入流
     * @param outFilePath
     * 输出地址
     * @param outFileName
     * 文件名称(带格式)
     */
    public static void addFile(InputStream inputStream, String outFilePath, String outFileName){
        // 创建文件夹
        addFolder(outFilePath);
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(outFilePath+"\\"+outFileName);
            outFlow(inputStream, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String readText(InputStream io, String encoding) {
        InputStreamReader read;
        try {
            read = new InputStreamReader(io,encoding);
            return readText(read);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void outFlow(InputStream input,OutputStream out){
        byte[] data = new byte[1024];
        int len;
        try {
            while ((len = input.read(data)) != -1) {
                out.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String readText(InputStreamReader inputStreamReader) {
        StringBuilder txt = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String lineTxt;
            while((lineTxt = bufferedReader.readLine()) != null){
                txt.append(lineTxt);
            }
            inputStreamReader.close();
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return txt.toString();
    }

    /**
     * 创建文件夹
     * @param folderName 文件夹名称
     */
    private static boolean addFolder(String folderName){
        File file = new File(folderName);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return false;
    }

}
