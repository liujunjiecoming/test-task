package com.bocang.task.init;

import com.bocang.task.job.CronScheduler;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.apache.qpid.jms.JmsConnection;
import org.apache.qpid.jms.JmsConnectionListener;
import org.apache.qpid.jms.message.JmsInboundMessageDispatch;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.net.URI;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author LJJ
 * @version 1.0
 * @Description 初始化资源
 * @date 2020/8/25 下午5:26
 */
@Log4j2
@Component
public class ResourceRunner implements CommandLineRunner {

    @Autowired
    private CronScheduler cronScheduler;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    //业务处理异步线程池，线程池参数可以根据您的业务特点调整，或者您也可以用其他异步方式处理接收到的消息。
    private final static ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(50000));

    @Override
    public void run(String... args) throws Exception {
//        Scheduler scheduler = schedulerFactoryBean.getScheduler();
//        //scheduler.deleteJob(JobKey.jobKey("job1", "group1"));
////        cronScheduler.scheduleJobs();
////        log.info("====== 系统错误代码加载完成 ======");
//
//        //参数说明，请参见AMQP客户端接入说明文档。
//        String accessKey = "LTAIzvFVaU4P5eIR";
//        String accessSecret = "B1PyRV9ELM5RpNTxJkiJJCEk4M8mvS";
//        String consumerGroupId = "DEFAULT_GROUP";
//        //iotInstanceId：购买的实例请填写实例ID，公共实例请填空字符串""。
//        String iotInstanceId = "";
//        long timeStamp = System.currentTimeMillis();
//        //签名方法：支持hmacmd5、hmacsha1和hmacsha256。
//        String signMethod = "hmacsha1";
//        //控制台服务端订阅中消费组状态页客户端ID一栏将显示clientId参数。
//        //建议使用机器UUID、MAC地址、IP等唯一标识等作为clientId。便于您区分识别不同的客户端。
//        String clientId = "192.168.0.145";
//
//        //userName组装方法，请参见AMQP客户端接入说明文档。
//        String userName = clientId + "|authMode=aksign"
//                + ",signMethod=" + signMethod
//                + ",timestamp=" + timeStamp
//                + ",authId=" + accessKey
//                + ",iotInstanceId=" + iotInstanceId
//                + ",consumerGroupId=" + consumerGroupId
//                + "|";
//        //计算签名，password组装方法，请参见AMQP客户端接入说明文档。
//        String signContent = "authId=" + accessKey + "&timestamp=" + timeStamp;
//        String password = doSign(signContent, accessSecret, signMethod);
//        //接入域名，请参见AMQP客户端接入说明文档。
////        String connectionUrl = "failover:(amqps://ilop.iot-amqp.cn-shanghai.aliyuncs.com:5671?amqp.idleTimeout=80000)"
////                + "?failover.reconnectDelay=30";
//        String connectionUrl = "failover:(amqps://1720736872654226.iot-amqp.cn-shanghai.aliyuncs.com:5671?amqp.idleTimeout=80000)"
//                + "?failover.reconnectDelay=30";
//
//
//        Hashtable<String, String> hashtable = new Hashtable<>();
//        hashtable.put("connectionfactory.SBCF", connectionUrl);
//        hashtable.put("queue.QUEUE", "default");
//        hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.qpid.jms.jndi.JmsInitialContextFactory");
//        Context context = new InitialContext(hashtable);
//        ConnectionFactory cf = (ConnectionFactory) context.lookup("SBCF");
//        Destination queue = (Destination) context.lookup("QUEUE");
//        // 创建连接。
//        Connection connection = cf.createConnection(userName, password);
//        ((JmsConnection) connection).addConnectionListener(myJmsConnectionListener);
//        // 创建会话。
//        // Session.CLIENT_ACKNOWLEDGE: 收到消息后，需要手动调用message.acknowledge()。
//        // Session.AUTO_ACKNOWLEDGE: SDK自动ACK（推荐）。
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        connection.start();
//        // 创建Receiver连接。
//        MessageConsumer consumer = session.createConsumer(queue);
//        consumer.setMessageListener(messageListener);

    }

    private static MessageListener messageListener = new MessageListener() {
        @Override
        public void onMessage(Message message) {
            try {
                //1.收到消息之后一定要ACK。
                // 推荐做法：创建Session选择Session.AUTO_ACKNOWLEDGE，这里会自动ACK。
                // 其他做法：创建Session选择Session.CLIENT_ACKNOWLEDGE，这里一定要调message.acknowledge()来ACK。
                // message.acknowledge();
                //2.建议异步处理收到的消息，确保onMessage函数里没有耗时逻辑。
                // 如果业务处理耗时过程过长阻塞住线程，可能会影响SDK收到消息后的正常回调。
                executorService.submit(() -> processMessage(message));
            } catch (Exception e) {
                log.error("submit task occurs exception ", e);
            }
        }
    };

    /**
     * 在这里处理您收到消息后的具体业务逻辑。
     */
    private static void processMessage(Message message) {
        try {
            byte[] body = message.getBody(byte[].class);
            String content = new String(body);
            String topic = message.getStringProperty("topic");
            String messageId = message.getStringProperty("messageId");
            log.info("receive message"
                    + ", topic = " + topic
                    + ", messageId = " + messageId
                    + ", content = " + content);
        } catch (Exception e) {
            log.error("processMessage occurs error ", e);
        }
    }

    private static JmsConnectionListener myJmsConnectionListener = new JmsConnectionListener() {
        /**
         * 连接成功建立。
         */
        @Override
        public void onConnectionEstablished(URI remoteURI) {
            log.info("onConnectionEstablished, remoteUri:{}", remoteURI);
        }

        /**
         * 尝试过最大重试次数之后，最终连接失败。
         */
        @Override
        public void onConnectionFailure(Throwable error) {
            log.error("onConnectionFailure, {}", error.getMessage());
        }

        /**
         * 连接中断。
         */
        @Override
        public void onConnectionInterrupted(URI remoteURI) {
            log.info("onConnectionInterrupted, remoteUri:{}", remoteURI);
        }

        /**
         * 连接中断后又自动重连上。
         */
        @Override
        public void onConnectionRestored(URI remoteURI) {
            log.info("onConnectionRestored, remoteUri:{}", remoteURI);
        }

        @Override
        public void onInboundMessage(JmsInboundMessageDispatch envelope) {
        }

        @Override
        public void onSessionClosed(Session session, Throwable cause) {
        }

        @Override
        public void onConsumerClosed(MessageConsumer consumer, Throwable cause) {
        }

        @Override
        public void onProducerClosed(MessageProducer producer, Throwable cause) {
        }
    };

    /**
     * 计算签名，password组装方法，请参见AMQP客户端接入说明文档。
     */
    private static String doSign(String toSignString, String secret, String signMethod) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), signMethod);
        Mac mac = Mac.getInstance(signMethod);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(toSignString.getBytes());
        return Base64.encodeBase64String(rawHmac);
    }


}
