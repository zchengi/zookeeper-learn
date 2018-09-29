package com.cheng.zk.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.Watcher.Event.EventType;

/**
 * 6-8 获取节点数据的 demo 演示
 *
 * @author cheng
 *         2018/9/29 13:15
 */
public class ZkGetNodeData implements Watcher {

    private ZooKeeper zooKeeper = null;

    private static final String ZK_SERVER_PATH = "193.112.56.145:2181";
    private static final int TIMEOUT = 5000;
    private static CountDownLatch countDown = new CountDownLatch(1);

    private static Stat stat = new Stat();

    public ZkGetNodeData() {
    }

    public ZkGetNodeData(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, TIMEOUT, new ZkGetNodeData());
        } catch (IOException e) {
            e.printStackTrace();
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        ZkGetNodeData zkServer = new ZkGetNodeData(ZK_SERVER_PATH);

        /*
         * 参数：
         * path：节点路径
         * watch：true或者false，注册一个watch事件
         * stat：状态
         */
        byte[] resByte = zkServer.getZooKeeper().getData("/cheng", true, stat);
        String result = new String(resByte);
        System.out.println("当前值: " + result);
        countDown.await();
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            if (event.getType() == EventType.NodeDataChanged) {
                ZkGetNodeData zkServer = new ZkGetNodeData(ZK_SERVER_PATH);
                byte[] resByte = zkServer.getZooKeeper().getData("/cheng", false, stat);
                String result = new String(resByte);
                System.out.println("更改后的值: " + result);
                System.out.println("版本号变化 dataVersion: " + stat.getVersion());
                countDown.countDown();
            } else if (event.getType() == EventType.NodeCreated) {
                System.out.println("EventType.NodeCreated");
            } else if (event.getType() == EventType.NodeChildrenChanged) {
                System.out.println("EventType.NodeChildrenChanged");
            } else if (event.getType() == EventType.NodeDeleted) {
                System.out.println("EventType.NodeDeleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }
}
