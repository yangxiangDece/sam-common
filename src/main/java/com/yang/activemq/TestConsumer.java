package com.yang.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;

import javax.jms.*;

/**
 * 消息消费者
 */
public class TestConsumer {

    //ActiveMq 的默认用户名
    private static final String USERNAME = "admin";
    //ActiveMq 的默认登录密码
    private static final String PASSWORD = "admin";
    //ActiveMQ 的链接地址 Openwire协议
    private static final String BROKEN_URL = "tcp://192.168.75.128:61616";

    //事务管理
    private Session session;

    private static final String QUEUE_NAME="username";

    public void init(){
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
            ActiveMQPrefetchPolicy prefetchPolicy = connectionFactory.getPrefetchPolicy();
            //设置Queue的预存取数量为50
            prefetchPolicy.setQueuePrefetch(50);
            connectionFactory.setPrefetchPolicy(prefetchPolicy);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void getMessage(String disName){
        try {
            Queue queue = session.createQueue(disName);
            MessageConsumer consumer = session.createConsumer(queue);
            while (true){
                Thread.sleep(1000);
                TextMessage msg = (TextMessage) consumer.receive();
                if(msg!=null) {
                    msg.acknowledge();
                    System.out.println("消费者收到消息：" + msg.getText());
                }
            }
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestConsumer consumer=new TestConsumer();
        consumer.init();
        consumer.getMessage(QUEUE_NAME);
    }
}
