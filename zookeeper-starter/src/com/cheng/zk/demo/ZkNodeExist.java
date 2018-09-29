package com.cheng.zk.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.Watcher.Event.EventType;

/**
 * 6-10 判断zk节点是否存在
 *
 * @author cheng
 *         2018/9/29 13:58
 */
public class ZkNodeExist implements Watcher {

    private ZooKeeper zooKeeper = null;

    private static final String ZK_SERVER_PATH = "193.112.56.145:2181";
    private static final int TIMEOUT = 5000;
    private static CountDownLatch countDown = new CountDownLatch(1);

    public ZkNodeExist() {
    }

    public ZkNodeExist(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, TIMEOUT, new ZkNodeExist());
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

        ZkNodeExist zkServer = new ZkNodeExist(ZK_SERVER_PATH);

        /*
         * 参数：
         * path：节点路径
         * watch：watch
         */
//        Stat stat = zkServer.getZooKeeper().exists("/cheng", true);
        Stat stat = zkServer.getZooKeeper().exists("/new-node", true);
        if (stat != null) {
            System.out.println("查询的节点版本为 dataVersion: " + stat.getVersion());
        } else {
            System.out.println("该节点不存在...");
        }

        countDown.await();
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == EventType.NodeCreated) {
            System.out.println("节点创建");
            countDown.countDown();
        } else if (event.getType() == EventType.NodeDataChanged) {
            System.out.println("节点数据改变");
            countDown.countDown();
        } else if (event.getType() == EventType.NodeDeleted) {
            System.out.println("节点删除");
            countDown.countDown();
        }
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

}
