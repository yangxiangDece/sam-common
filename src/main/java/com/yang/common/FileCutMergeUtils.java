package com.yang.common;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 文件的拆剪与合并.
 * <p/>
 *
 * @author <a href="sam@kingyon.com">Yang Xiang</a>
 * 2017/7/10
 * @since 0.1.0
 */
public class FileCutMergeUtils {

    /**
     * 创建文件夹
     *
     * @param path 路径
     */
    private static void createFile(String path) {
        File createFile = new File(path);
        if (!createFile.exists()) {
            createFile.mkdir();
        }
    }

    /**
     * 判断是否是一个文件夹
     *
     * @param path 路径
     * @return 是否
     */
    private static boolean isFolder(String path) {
        File createFile = new File(path);
        return createFile.isDirectory();
    }

    /**
     * 文件的拆剪 拆剪到当前目录
     *
     * @param sourcePath 源文件路径
     * @param part       拆分份数
     */
    public static void cutFile(String sourcePath, int part) {
        //源文件
        File sourceFile = new File(sourcePath);
        //文件根路径
        String tempPath = sourceFile.getParent() + "/temp";
        //创建临时目录
        createFile(tempPath);
        //文件前部分名称 abc.zip 得到：abc
        String sourceFileStart = sourceFile.getName().substring(0, sourceFile.getName().indexOf("."));
        //文件后部分名称 abc.zip 得到：.zip
        String sourceFileEnd = sourceFile.getName().substring(sourceFile.getName().indexOf("."));
        //每一份文件的大小
        Long partSize = sourceFile.length() / part;
        //创建源文件输入流
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(sourceFile);
            //创建输出流
            FileOutputStream fileOutputStream;
            for (int i = 1; i <= part; i++) {
                //获取拆分文件的名称
                //拆分文件
                File tempFile = new File(tempPath + "\\" + sourceFileStart + "_data" + i + sourceFileEnd);
                //创建缓冲区
                byte[] bytes = new byte[partSize.intValue()];
                //创建输出流
                fileOutputStream = new FileOutputStream(tempFile);
                //判断读取文件不能为-1
                int read;
                //读取文件输出到拆分文件中
                while ((read = fileInputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, read);
                    fileOutputStream.flush();
                    //拆分文件大小 等于 每一份文件的大小时 说明已经输出完整 一个拆分文件已经完成
                    if (tempFile.length() == partSize) {
                        break;
                    }
                }
                //关闭输出流
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭输入流
            close(fileInputStream);
        }
    }

    /**
     * 合并文件 合并文件夹下面的所有文件
     *
     * @param path       文件夹路径
     * @param targetName 文件合并路径和名称
     * @param removed    是否删除拆分文件以及文件夹
     */
    public static void mergeFile(String path, String targetName, boolean removed) {
        //检查是否是文件夹
        if (!isFolder(path)) {
            return;
        }
        //文件夹
        File dirFile = new File(path);
        //获取文件夹下面的所有文件
        File[] files = dirFile.listFiles();
        //文件输入流集合 使用vector集合线程安全
        Vector<FileInputStream> vectors = new Vector<>();
        //创建串联输入流对象
        SequenceInputStream sequenceInputStream = null;
        //创建输入流
        FileOutputStream fileOutputStream = null;
        try {
            if (files == null || files.length == 0) return;
            //将文件夹下面的所有文件 加入的输入流集合中
            for (File file : files) {
                //如果是文件 就创建输入流 并加入到集合中
                if (file.isFile()) {
                    vectors.add(new FileInputStream(file));
                }
            }
            //组装串联需要的文件
            Enumeration<FileInputStream> enumerations = vectors.elements();
            sequenceInputStream = new SequenceInputStream(enumerations);
            fileOutputStream = new FileOutputStream(targetName);

            //创建缓冲区
            byte[] bytes = new byte[1024 * 1024];
            int read;
            //读取文件 输出到文件中
            while ((read = sequenceInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
                fileOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭串联流、关闭输出流
            close(sequenceInputStream, fileOutputStream);
        }
        if (removed) {//删除文件
            //删除文件
            deleteFile(files);
            //删除空文件夹
            dirFile.delete();
        }
    }

    private static void deleteFile(File[] files) {
        for (File file : files) {
            file.delete();
        }
    }

    private static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}