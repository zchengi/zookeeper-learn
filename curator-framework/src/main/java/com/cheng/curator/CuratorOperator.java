package com.cheng.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.data.Stat;

/**
 * 7-2 建立 curator 与 zkServer 的连接
 * 7-3 zk命名空间以及创建节点
 * 7-4 zk修改节点以及删除节点
 * 7-5 zk查询节点相关信息
 *
 * @author cheng
 *         2018/10/1 17:20
 */
public class CuratorOperator {

    public CuratorFramework client;
    private static final String ZK_SERVER_PATH = "193.112.56.145:2181";

    /**
     * 实例化zk客户端
     */
    public CuratorOperator() {
        /*
         * 同步创建zk示例，原生api是异步的
         *
         * curator 连接 zookeeper 的策略：ExponentialBackOffRetry
         * baseSleepTimeMs：初始 sleep 的时间
         * maxRetries：最大重试次数
         * maxSleepMs：最大重试时间
         */
//        RetryPolicy retryPolicy1 = new ExponentialBackoffRetry(1000, 5);

        /*
         * curator 连接 zookeeper 的策略：RetryNTimes
         * n：重试的次数
         * sleepMsBetweenRetries：每次重试间隔的时间
         */
        RetryPolicy retryPolicy2 = new RetryNTimes(3, 5000);

        /*
         * curator 连接 zookeeper 的策略：RetryOneTime
         * sleepMsBetweenRetry：重试时间
         */
//        RetryPolicy retryPolicy3 = new RetryOneTime(3000);

        /*
         * 永远重试，不推荐使用
         */
//        RetryPolicy retryPolicy4 = new RetryForever(3000);

        /*
         * curator 连接 zookeeper 的策略：RetryUntilElapsed
         * maxElapsedTimeMs：最大重试时间
         * sleepMsBetweenRetries：每次重试间隔
         * 重试时间超过 maxElapsedTimeMs 后，就不再重试
         */
//        RetryPolicy retryPolicy5 = new RetryUntilElapsed(2000, 3000);

        client = CuratorFrameworkFactory.builder()
                .connectString(ZK_SERVER_PATH)
                .sessionTimeoutMs(10000).retryPolicy(retryPolicy2)
                // 如果当前连接成功以后，那么之后的所有操作都基于 namespace
                .namespace("workspace")
                .build();
        client.start();
    }

    /**
     * 关闭zk客户端连接
     */
    public void closeZkClient() {
        if (client != null) {
            this.client.close();
        }
    }

    public static void main(String[] args) throws Exception {

        // 实例化
        CuratorOperator curator = new CuratorOperator();
        System.out.println("当前客户端的状态: " + curator.client.getState());

        // 创建节点
        String nodePath = "/super/cheng";
//        byte[] data = "superman".getBytes();
//        curator.client.create().creatingParentsIfNeeded()
//                .withMode(CreateMode.PERSISTENT)
//                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
//                .forPath(nodePath, data);

        // 更新节点数据
//        byte[] newData = "batman".getBytes();
//        curator.client.setData().withVersion(0).forPath(nodePath, newData);

        // 删除节点
//        curator.client.delete()
//                 如果删除失败，那么在后端还是继续会删除，直到成功
//                .guaranteed()
//                 如果有子节点，则删除
//                .deletingChildrenIfNeeded()
//                .withVersion(1)
//                .forPath(nodePath);

        // 读取节点数据
//        Stat stat = new Stat();
//        byte[] data = curator.client.getData().storingStatIn(stat).forPath(nodePath);
//        System.out.println("节点 " + nodePath + " 的数据为: " + new String(data));
//        System.out.println("该节点的版本号为: " + stat.getVersion());

        // 查询子节点
//        List<String> childNodes = curator.client.getChildren().forPath(nodePath);
//        System.out.println("开始打印子节点: ");
//        for (String childNode : childNodes) {
//            System.out.println(childNode);
//        }

        // 判断节点是否存在，如果不存在则为空
        Stat statExist = curator.client.checkExists().forPath(nodePath);
        System.out.println(statExist);


        Thread.sleep(3000);

        curator.closeZkClient();
        System.out.println("当前客户端的状态: " + curator.client.getState());
    }
}
