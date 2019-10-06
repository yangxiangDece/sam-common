package com.yang.designmode.responsibility;

/**
 * ConcreteHandler（具体处理者）：它是抽象处理者的子类，可以处理用户请求，在具体处理者类中实现了抽象处理者中定义的抽象请求处理方法，在处理请求之前需要进行判断，
 * 看是否有相应的处理权限，如果可以处理请求就处理它，否则将请求转发给后继者；在具体处理者中可以访问链中下一个对象，以便请求的转发。
 * <p>
 * 副董事长
 */
public class VicePresident extends Approver {

    public VicePresident(String name) {
        super(name);
    }

    //具体请求处理方法
    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 100000) {
            System.out.println("副董事长 " + this.name + "审批采购单：" + request.getNumber() + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");
        } else {
            successor.processRequest(request);
        }
    }
}
