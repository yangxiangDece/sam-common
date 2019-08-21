package com.yang.dubbo.spi;

import org.apache.dubbo.common.extension.SPI;

@SPI
public interface Robot {

    void sayHello();
}
