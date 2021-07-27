package com.soft.util.file;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author 野性的呼唤
 * @date 2021/7/27 19:10
 * @description
 */
class FileUtilTest {

    @Test
    void writeText() {
        FileUtil.writeText("内容", "text.txt");
    }

    @Test
    void readText() {
        String text = FileUtil.readText(new File("text.txt"), "UTF-8");
        System.out.println(text);
    }
}