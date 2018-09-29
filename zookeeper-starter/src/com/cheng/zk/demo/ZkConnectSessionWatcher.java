package com.cheng.zk.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 6-2 zk会话重连机制
 *
 * @author cheng
 *         2018/9/28 14:59
 */
public class ZkConnectSessionWatcher implements Watcher {

    private static Logger logger = LoggerFactory.getLogger(ZkConnectSessionWatcher.class);

    private static final String ZK_SERVER_PATH = "193.112.56.145:2181";

    private static final int TIMEOUT = 5000;

    public static void main(String[] args) throws Exception {

        ZooKeeper zk = new ZooKeeper(ZK_SERVER_PATH, TIMEOUT, new ZkConnectSessionWatcher());

        long sessionId = zk.getSessionId();

        // idea debug Thread 模式下可以打印出来
        String ssid = "0x" + Long.toHexString(sessionId);
        System.out.println(ssid);

        byte[] sessionPassword = zk.getSessionPasswd();

        logger.warn("客户端开始连接 zookeeper 服务器...");
        logger.warn("连接状态: {}", zk.getState());

        Thread.sleep(200);

        logger.warn("连接状态：{}", zk.getState());

        // 开始会话重连
        logger.warn("开始会话重连...");

        ZooKeeper zkSession = new ZooKeeper(ZK_SERVER_PATH,
                TIMEOUT,
                new ZkConnectSessionWatcher(),
                sessionId,
                sessionPassword);

        logger.warn("重新连接状态 zkSession: {}", zkSession.getState());
        Thread.sleep(1000);
        logger.warn("重新连接状态 zkSession: {}", zkSession.getState());
    }

    @Override
    public void process(WatchedEvent event) {
        logger.warn("接收到 watch 通知: {}", event);
    }
}
