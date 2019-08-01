package com.yang.dubbo.singlelongconnection.utils;

import java.io.*;

/**
 * 序列化/反序列化工具
 */
public class SerializeUtil {

    public static byte[] serialize(Object object) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            System.err.println("序列化出错了...");
            e.printStackTrace();
            return null;
        } finally {
            CloseStreamUtil.close(oos, bos);
        }
    }

    public static Object unSerialize(byte[] bytes) {
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("反序列化出错了...");
            e.printStackTrace();
            return null;
        } finally {
            CloseStreamUtil.close(ois, bis);
        }
    }
}
