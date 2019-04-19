//package com.tsfeng.cn.rabbitmq;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.util.ResourceBundle;
//import java.util.concurrent.TimeoutException;
//
///**
// * @author tsfeng
// * @version 创建时间 2018/11/30 15:54
// */
//public class MessageSender {
//
//    private final Logger logger = LoggerFactory.getLogger(MessageSender.class);
//
//    private static ConnectionFactory factory = new ConnectionFactory();
//    private static Connection connection;
//    private static Channel channel;
//
//    static {
//        try {
//            ResourceBundle resourceBundle = ResourceBundle.getBundle("decoration-logic-rabbitmq");
//            factory.setHost(resourceBundle.getString("host"));
//            factory.setPort(Integer.parseInt(resourceBundle.getString("port")));
//            factory.setUsername(resourceBundle.getString("username"));
//            factory.setPassword(resourceBundle.getString("password"));
//
//            connection = factory.newConnection();
////            connection.addShutdownListener(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 声明一个列队名字
//     */
//    private final static String QUEUE_NAME = "hello";
//
//    public boolean sendMessageMany(String message) {
//        try {
//            /* 声明一个列队:
//                1、queue队列的名字
//                2、是否持久化 为true则在rabbitMQ重启后生存
//                3、是否是排他性队列（别人看不到），只对当前连接有效，当前连接断开后，队列删除（设置了持久化也删除）
//                4、当没有任何消费者使用时，自动删除该队列
//                5、其他参数
//            **/
//            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//            /*发布消息,注意这里调用了getBytes()，发送的其实是byte数组，接收方收到消息后，需要重新组装成String * 1.交换模式 * 2.控制消息发送到哪个队列 * 3.其他参数 * 4.body 消息，byte数组 * */
//            //循环往列队发布消息
//            int i = 0;
//            String oldMessage = message;
//            while (true) {
//                message += i++;
//                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//                logger.info("已发送:" + message);
//                try {
//                    Thread.sleep(500);
//                    //500毫秒发送一次
//                } catch (InterruptedException e) {
//                    logger.info("线程被打断:" + message);
//                }
//                message = oldMessage;
//                //还原消息
//                //此代码没用,只是为了死循环出现防止编译出错
//                if (!"q".equals("q")) {
//                    break;
//                }
//            }
//
//        } catch (IOException e) {
//            logger.error("IO异常:" + e);
//            return false;
//        } catch (TimeoutException e) {
//            logger.error("超时异常:" + e);
//            return false;
//        } finally {
//            //关闭通道和链接(先关闭通道在关闭连接)
//            channel.close();
//            connection.close();
//
//        }
//        return true;
//    }
//}
