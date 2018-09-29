package com.cheng.zk.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.Watcher.Event.EventType;

/**
 * 6-9 获取zk子节点列表
 *
 * @author cheng
 *         2018/9/29 13:13
 */
public class ZkGetChildrenList implements Watcher {

    private ZooKeeper zooKeeper = null;

    private static final String ZK_SERVER_PATH = "193.112.56.145:2181";
    private static final int TIMEOUT = 5000;
    private static CountDownLatch countDown = new CountDownLatch(1);

    public ZkGetChildrenList() {
    }

    public ZkGetChildrenList(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, TIMEOUT, new ZkGetChildrenList());
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

        ZkGetChildrenList zkServer = new ZkGetChildrenList(ZK_SERVER_PATH);

        /*
         * 参数：
         * path：节点路径
         * watch：true或者false，注册一个watch事件
         */
//        List<String> strChildList = zkServer.getZooKeeper().getChildren("/cheng", true);
//        strChildList.forEach(System.out::println);

        // 异步调用
        String ctx = "{'callback':'ChildrenCallback'}";
//        zkServer.getZooKeeper().getChildren("/cheng", true, new ChildrenCallBack(), ctx);
        zkServer.getZooKeeper().getChildren("/cheng", true, new Children2CallBack(), ctx);

        countDown.await();
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            if (event.getType() == EventType.NodeChildrenChanged) {
                System.out.println("EventType.NodeChildrenChanged");

                ZkGetChildrenList zkServer = new ZkGetChildrenList(ZK_SERVER_PATH);
                List<String> strChildList = zkServer.getZooKeeper().getChildren(event.getPath(), false);
                strChildList.forEach(System.out::println);

                countDown.countDown();

            } else if (event.getType() == EventType.NodeCreated) {
                System.out.println("EventType.NodeCreated");
            } else if (event.getType() == EventType.NodeDataChanged) {
                System.out.println("EventType.NodeDataChanged");
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
