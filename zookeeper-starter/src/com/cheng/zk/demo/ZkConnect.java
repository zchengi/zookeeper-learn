package com.cheng.zk.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 6-1 建立客户端与zk服务端的连接
 *
 * @author cheng
 *         2018/9/28 13:16
 */
public class ZkConnect implements Watcher {

    private static Logger logger = LoggerFactory.getLogger(ZkConnect.class);

    private static final String ZK_SERVER_PATH = "193.112.56.145:2181";

    private static final String ZK_COLONY_SERVER_PATH = "193.112.56.145:2181,193.112.56.145:2182";

    private static final int TIMEOUT = 5000;

    public static void main(String[] args) throws Exception {

        /*
         *
         * 客户端和zk服务端连接是一个异步的过程
         * 当连接成功后，客户端会收到一个 watch 通知
         *
         * 连接参数：
         * connectString：连接服务器的ip字符串，
         *      比如："193.112.56.145:2181,193.112.56.145:2182"
         *      可以是一个ip，也可以是多个ip，一个ip代表单机，多个ip代表集群，
         *      也可以在ip后加路径。
         * sessionTimeOut：超时时间，心跳收不到了，那就超时
         * watcher：通知事件，如果有对应的事件触发，则会收到一个通知，如果不需要，那就设置为 null
         * canBeReadOnly：可读，当这个物理机节点断开后，还是可以读到数据的，只是不能写，
         *                      此时数据被读取到的可能是旧数据，此处建议设置为 false，不推荐使用
         * sessionId：会话的id
         * sessionPassword：会话密码  当回话丢失后，可以依据 sessionId 和 SessionPassword 重新获取会话
         *
         */

//        ZooKeeper zk = new ZooKeeper(ZK_SERVER_PATH, TIMEOUT, new ZkConnect());
        ZooKeeper zk = new ZooKeeper(ZK_COLONY_SERVER_PATH, TIMEOUT, new ZkConnect());

        logger.warn("客户端开始连接 zookeeper 服务器...");
        logger.warn("连接状态: {}", zk.getState());

        Thread.sleep(2000);

        logger.warn("连接状态: {}", zk.getState());
    }

    @Override
    public void process(WatchedEvent event) {
        logger.warn("接收到 watch 通知: {}", event);
    }
}
