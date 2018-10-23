package com.cheng.curator.utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * 分布式锁的实现工具类
 *
 * @author cheng
 *         2018/10/23 13:56
 */
public class DistributedLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkCurator.class);

    /**
     * 分布式锁的总节点名
     */
    private static final String ZK_LOCK_PROJECT = "cheng-locks";
    /**
     * 分布式锁节点名
     */
    private static final String DISTRIBUTED_LOCK = "distributed_lock";

    /**
     * 用于挂起当前请求，并且等待上一个分布式锁释放
     */
    private static CountDownLatch zkLockLatch = new CountDownLatch(1);

    /**
     * zk 客户端
     */
    private CuratorFramework client;

    public DistributedLock(CuratorFramework client) {
        this.client = client;
    }

    /**
     * 初始化操作
     */
    public void init() {

        // 使用命名空间
        client = client.usingNamespace("ZkLocks-Namespace");

        /*
         * 创建zk锁的总节点，相当于 eclipse 的工作空间下的项目
         *      ZkLocks-Namespace
         *       丨
         *        —— cheng-locks
         *              丨
         *               —— distributed_lock
         */
        try {
            if (client.checkExists().forPath("/" + ZK_LOCK_PROJECT) == null) {
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath("/" + ZK_LOCK_PROJECT);
            }
            // 针对zk的分布式锁节点，创建相应的 watcher 事件监听
            addWatcherToLock("/" + ZK_LOCK_PROJECT);
        } catch (Exception e) {
            LOGGER.error("客户端连接 zookeeper 服务器错误... 请重试...");
        }
    }

    /**
     * 获取分布式锁
     */
    public void getLock() {

        // 使用死循环，当且仅当上一个锁释放并且当前请求获得锁成功后才会跳出
        while (true) {
            try {
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath("/" + ZK_LOCK_PROJECT + "/" + DISTRIBUTED_LOCK);

                // 如果锁的节点能够被创建成功，则锁没有被占用
                LOGGER.info("获取分布式锁成功...");
                return;
            } catch (Exception e) {
                LOGGER.info("获取分布式锁失败...");
                try {
                    // 如果没有获取到锁，需要重新设置同步资源值
                    if (zkLockLatch.getCount() <= 0) {
                        zkLockLatch = new CountDownLatch(1);
                    }

                    // 阻塞线程
                    zkLockLatch.await();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /**
     * 释放分布式锁
     *
     * @return
     */
    public boolean releaseLock() {
        try {
            if (client.checkExists().forPath("/" + ZK_LOCK_PROJECT + "/" + DISTRIBUTED_LOCK) != null) {
                client.delete().forPath("/" + ZK_LOCK_PROJECT + "/" + DISTRIBUTED_LOCK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        LOGGER.info("分布式锁释放完毕");
        return true;
    }

    /**
     * 创建 watcher 监听
     *
     * @param path
     */
    private void addWatcherToLock(String path) throws Exception {

        final PathChildrenCache cache = new PathChildrenCache(client, path, true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        cache.getListenable().addListener((client, event) -> {
            if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                String path1 = event.getData().getPath();
                LOGGER.info("上一个会话已经释放锁或该会话已断开，节点的路径为: " + path1);
                if (path1.contains(DISTRIBUTED_LOCK)) {
                    LOGGER.info("释放计数器，让当前请求来获取分布式锁...");
                    zkLockLatch.countDown();
                }
            }
        });
    }
}
