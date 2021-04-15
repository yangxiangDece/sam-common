package com.yang.spring;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class NITest {

    public static void main(String[] args) throws Exception {
        File[] files = new File("C:\\soft\\workspace - test/").listFiles();
        for (File file : files) {
            for (File listFile : file.listFiles()) {
                String name = listFile.getName();
                if (StringUtils.startsWith(name, ".git")
                        || StringUtils.startsWith(name, ".idea")
                        || StringUtils.startsWith(name, "cache")
                        || StringUtils.startsWith(name, "disconf")
                        || StringUtils.startsWith(name, "logs")
                        || StringUtils.startsWith(name, "target")
                        || StringUtils.endsWith(name, ".iml")
                        || StringUtils.endsWith(name, ".log")
                ) {
                    FileUtils.deleteQuietly(listFile);
                    System.out.println(listFile.getPath() + "   ----------------已经删除");
                }
            }
        }
    }
}
