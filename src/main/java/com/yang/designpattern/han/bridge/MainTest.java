package com.yang.designpattern.han.bridge;

/**
 * 桥接模式
 */
public class MainTest {

    public static void main(String[] args) {
        FoldedPhone xiaomiF = new FoldedPhone(new XiaoMi());
        xiaomiF.open();
        xiaomiF.call();
        xiaomiF.close();

        System.out.println("==========================================");

        FoldedPhone vivoF = new FoldedPhone(new Vivo());
        vivoF.open();
        vivoF.call();
        vivoF.close();

        System.out.println("==========================================");

        UpRightPhone xiaomiU = new UpRightPhone(new XiaoMi());
        xiaomiU.open();
        xiaomiU.call();
        xiaomiU.close();

        System.out.println("==========================================");

        UpRightPhone vivoU = new UpRightPhone(new Vivo());
        vivoU.open();
        vivoU.call();
        vivoU.close();

    }
}
