package com.yang.dubbo.singlelongconnection.utils;

import java.io.Closeable;
import java.io.IOException;

public class CloseStreamUtil {

    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
