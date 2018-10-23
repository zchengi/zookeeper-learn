package com.cheng.curator.utils;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cheng
 *         2018/10/23 13:20
 */
public class ZkCurator {

    /**
     * zk 客户端
     */
    private CuratorFramework client;

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkCurator.class);

    public ZkCurator(CuratorFramework client) {
        this.client = client;
    }

    /**
     * 初始化操作
     */
    public void init() {
        // 使用命名空间
        client = client.usingNamespace("zk-curator-connector");
    }

    /**
     * 判断 zk 是否连接
     */
    public String isZkAlive() {
        return client.getState().toString();
    }
}
