package com.cheng.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.List;

import static org.apache.zookeeper.ZooDefs.Ids;

/**
 * 6-3 zk节点的增删改查
 *
 * @author cheng
 *         2018/9/28 15:36
 */
public class ZkNodeOperator implements Watcher {

    private static final String ZK_SERVER_PATH = "193.112.56.145:2181";

    private static final int TIMEOUT = 5000;

    private ZooKeeper zooKeeper = null;

    public ZkNodeOperator() {
    }

    public ZkNodeOperator(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, TIMEOUT, new ZkNodeOperator());
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

    /**
     * 创建zk节点
     *
     * @param path
     * @param data
     * @param acls
     */
    public void createZkNode(String path, byte[] data, List<ACL> acls) {
        try {
            /*
             *
             * 同步或者异步创建节点，都不支持子节点的递归创建，异步有一个 callback 函数
             * 参数：
             * path: 创建的路径
             * data: 存储的数据的 byte[]
             * acl: 控制权限策略
             *          Ids.OPEN_ACL_UNSAFE --> world:anyone:cdrwa
             *          CREATOR_ALL_ACL --> auth:user:password:cdrwa
             * createMode: 节点类型，是一个枚举
             *          PERSISTENT: 持久节点
             *          PERSISTENT_SEQUENTIAL：持久顺序节点
             * 			EPHEMERAL：临时节点
             * 			EPHEMERAL_SEQUENTIAL：临时顺序节点
             *
             */

            // 同步创建
            String result = zooKeeper.create(path, data, acls, CreateMode.PERSISTENT);
            System.out.println("创建节点：\t" + result + "\t成功...");

            // 异步创建
//            String ctx = "{'create':'success'}";
//            zooKeeper.create(path, data, acls, CreateMode.PERSISTENT, new CreateCallBack(), ctx);
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        ZkNodeOperator zkServer = new ZkNodeOperator(ZK_SERVER_PATH);

        // 创建zk节点
//        zkServer.createZkNode("/testNode", "testNode".getBytes(), Ids.OPEN_ACL_UNSAFE);

        // 修改zk节点
//        Stat status = zkServer.getZooKeeper().setData("/testNode", "xyz".getBytes(), 0);
//        System.out.println(status.getVersion());

        // 删除zk节点
        zkServer.createZkNode("/test-delete-node", "123".getBytes(), Ids.OPEN_ACL_UNSAFE);
        // 同步
//        zkServer.getZooKeeper().delete("/test-delete-node", 0);
        // 异步
        String ctx = "{'delete':'success'}";
        zkServer.getZooKeeper().delete("/test-delete-node", 0, new DeleteCallBack(), ctx);
        Thread.sleep(2000);
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
    }
}
