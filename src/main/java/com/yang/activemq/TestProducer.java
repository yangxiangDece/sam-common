package com.yang.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息生产者
 */
public class TestProducer {

    //ActiveMq 的默认用户名
    private static final String USERNAME = "admin";
    //ActiveMq 的默认登录密码
    private static final String PASSWORD = "admin";
    //ActiveMQ 的链接地址
    private static final String BROKEN_URL = "tcp://192.168.75.128:61616";

    //事务管理
    private Session session;

    private static final String QUEUE_NAME="username";

    private void init() {
        //创建一个连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
        try {
            //从工厂中创建一个连接
            Connection connection = connectionFactory.createConnection();
            //开启连接
            connection.start();
            //创建一个事务，true-表示是否开启事务，当发送多条消息时，可以一并提交。第二个参数表示消息应答模式
            session = connection.createSession(true,Session.SESSION_TRANSACTED);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String disName){
        try {
            //创建一个消息队列
            Queue queue=session.createQueue(disName);
            //消息生产者
            MessageProducer messageProducer=session.createProducer(queue);
            int num = 0;
            while (true) {
                Thread.sleep(1000);
                TextMessage msg = session.createTextMessage("生产者生产消息,编号"+(++num));
                System.out.println("打印：生产者生产消息,编号"+num);
                //发送消息
                messageProducer.send(msg);
                session.commit();
            }
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestProducer producer = new TestProducer();
        producer.init();
        producer.sendMessage(QUEUE_NAME);
    }
}
