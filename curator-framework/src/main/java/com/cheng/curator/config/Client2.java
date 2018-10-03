package com.cheng.curator.config;

import com.cheng.utils.JsonUtils;
import com.cheng.utils.RedisConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.CountDownLatch;

/**
 * @author cheng
 *         2018/10/2 14:59
 */
public class Client2 {

    public CuratorFramework client;
    private static final String ZK_SERVER_PATH = "193.112.56.145:2181";

    public final static String CONFIG_NODE_PATH = "/super/cheng";
    public final static String SUB_PATH = "/redis-config";
    public static CountDownLatch countDown = new CountDownLatch(1);

    public Client2() {
        RetryPolicy retryPolicy = new RetryNTimes(3, 5000);
        client = CuratorFrameworkFactory.builder()
                .connectString(ZK_SERVER_PATH)
                .sessionTimeoutMs(10000).retryPolicy(retryPolicy)
                .namespace("workspace").build();
        client.start();
    }

    public static void main(String[] args) throws Exception {

        Client2 client2 = new Client2();
        System.out.println("client2 启动成功...");

        final PathChildrenCache childrenCache = new PathChildrenCache(client2.client, CONFIG_NODE_PATH, true);
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

        // 添加监听事件
        childrenCache.getListenable().addListener((client, event) -> {

            // 监听节点变化
            if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
                String configNodePath = event.getData().getPath();
                if ((CONFIG_NODE_PATH + SUB_PATH).equals(configNodePath)) {
                    System.out.println("监听到配置发生变化，节点路径为: " + configNodePath);

                    // 读取节点数据
                    String jsonConfig = new String(event.getData().getData());
                    System.out.println("节点 " + CONFIG_NODE_PATH + "的数据为: " + jsonConfig);

                    // 从 json 转换配置
                    RedisConfig redisConfig = null;
                    if (StringUtils.isNoneBlank(jsonConfig)) {
                        redisConfig = JsonUtils.jsonToPojo(jsonConfig, RedisConfig.class);
                    }

                    // 配置不为空则进行相应的操作
                    if (redisConfig != null) {
                        String type = redisConfig.getType();
                        String url = redisConfig.getUrl();
                        String remark = redisConfig.getRemark();

                        // 判断事件
                        if ("add".equals(type)) {
                            System.out.println("监听到新增的配置，准备下载...");
                            // ... 连接ftp服务器，根据url找到相应的配置
                            Thread.sleep(500);
                            System.out.println("开始下载新的配置文件，下载路径为<" + url + ">");
                            // ... 下载配置到你指定的目录
                            Thread.sleep(1000);
                            System.out.println("下载成功，已经添加到项目中");
                            // ... 拷贝文件到项目目录
                        } else if ("update".equals(type)) {
                            System.out.println("监听到更新的配置，准备下载...");
                            // ... 连接ftp服务器，根据url找到相应的配置
                            Thread.sleep(500);
                            System.out.println("开始下载配置文件，下载路径为<" + url + ">");
                            // ... 下载配置到你指定的目录
                            Thread.sleep(1000);
                            System.out.println("下载成功...");
                            System.out.println("删除项目中原配置文件...");
                            Thread.sleep(100);
                            // ... 删除原文件
                            System.out.println("拷贝配置文件到项目目录...");
                            // ... 拷贝文件到项目目录
                        } else if ("delete".equals(type)) {
                            System.out.println("监听到需要删除配置");
                            System.out.println("删除项目中原配置文件...");
                        }

                        // TODO 视情况统一重启服务
                    }
                }
            }
        });

        countDown.await();
        client2.closeZkClient();
    }

    public void closeZkClient() {
        if (client != null) {
            client.close();
        }
    }
}