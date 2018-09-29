package com.cheng.zk.demo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.zookeeper.ZooDefs.Perms;

/**
 * 6-11 watch与acl的相关操作
 *
 * @author cheng
 *         2018/9/29 14:09
 */
public class ZkNodeAcl implements Watcher {

    private ZooKeeper zooKeeper = null;

    private static final String ZK_SERVER_PATH = "193.112.56.145:2181";
    private static final int TIMEOUT = 5000;

    public ZkNodeAcl() {
    }

    public ZkNodeAcl(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, TIMEOUT, new ZkNodeAcl());
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

    public void createZkNode(String path, byte[] data, List<ACL> acls) {
        try {
            // 同步创建
            String result = zooKeeper.create(path, data, acls, CreateMode.PERSISTENT);
            System.out.println("创建节点：\t" + result + "\t成功...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        ZkNodeAcl zkServer = new ZkNodeAcl(ZK_SERVER_PATH);

        /// ----------------------------- 创建 node start -----------------------------

        // 任何人都可以访问
//        zkServer.createZkNode("/acl-test", "test".getBytes(), Ids.OPEN_ACL_UNSAFE);

        // 自定义用户认证访问
//        List<ACL> acls = new ArrayList<>();
//        Id cheng1 = new Id("digest", AclUtils.getDigestUserPwd("cheng1:123456"));
//        Id cheng2 = new Id("digest", AclUtils.getDigestUserPwd("cheng2:123456"));
//        acls.add(new ACL(Perms.ALL, cheng1));
//        acls.add(new ACL(Perms.READ, cheng2));
//        acls.add(new ACL(Perms.DELETE | Perms.CREATE, cheng2));
//        zkServer.createZkNode("/acl-test/test-digest", "test-digest".getBytes(), acls);

        // 注册用过的用户必须通过 addAuthInfo 才能操作节点，参考命令 addAuth
//        zkServer.getZooKeeper().addAuthInfo("digest", "cheng1:123456".getBytes());
//        zkServer.createZkNode("/acl-test/test-digest/child-test", "child-test".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL);

//        Stat stat = new Stat();
//        byte[] data = zkServer.getZooKeeper().getData("/acl-test/test-digest", false, stat);
//        System.out.println(new String(data));
//        zkServer.getZooKeeper().setData("/acl-test/test-digest", "now".getBytes(), 1);

        // ip方式的acl
        List<ACL> aclsIP = new ArrayList<>();
        // 在 www.ip138.com 查询本机ip地址，即可远程控制服务端 zk
        Id ipId1 = new Id("ip", "124.90.67.195");
        Id ipId2 = new Id("ip", "127.0.0.1");
        aclsIP.add(new ACL(Perms.ALL, ipId1));
        aclsIP.add(new ACL(Perms.ALL, ipId2));
        zkServer.createZkNode("/acl-test/ip-test", "ip-test".getBytes(), aclsIP);

        // 验证ip是否有权限
        zkServer.getZooKeeper().setData("/acl-test/ip-test", "now".getBytes(), 0);
        Stat stat = new Stat();
        byte[] data = zkServer.getZooKeeper().getData("/acl-test/ip-test", false, stat);
        System.out.println(new String(data));
        System.out.println(stat.getVersion());
    }

    @Override
    public void process(WatchedEvent event) {
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }
}
