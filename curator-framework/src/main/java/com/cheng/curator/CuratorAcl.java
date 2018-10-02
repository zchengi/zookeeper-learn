package com.cheng.curator;

import com.cheng.utils.AclUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.util.ArrayList;
import java.util.List;

import static org.apache.zookeeper.ZooDefs.Perms;

/**
 * 7-10 curator 之acl权限操作与认证授权
 *
 * @author cheng
 *         2018/10/2 15:16
 */
public class CuratorAcl {

    public CuratorFramework client;
    private static final String ZK_SERVER_PATH = "193.112.56.145:2181";

    public CuratorAcl() {
        RetryPolicy retryPolicy = new RetryNTimes(3, 5000);
        client = CuratorFrameworkFactory.builder().authorization("digest", "cheng1:123456".getBytes())
                .connectString(ZK_SERVER_PATH)
                .sessionTimeoutMs(10000).retryPolicy(retryPolicy)
                .namespace("workspace").build();
        client.start();
    }

    public static void main(String[] args) throws Exception {

        // 实例化
        CuratorAcl curator = new CuratorAcl();
        System.out.println("当前客户端的状态: " + curator.client.getState());

        // 如果要递归创建，则每一级都必须有创建的权限
        String nodePath = "/acl/father/child/sub";

        List<ACL> acls = new ArrayList<>();
        Id cheng1 = new Id("digest", AclUtils.getDigestUserPwd("cheng1:123456"));
        Id cheng2 = new Id("digest", AclUtils.getDigestUserPwd("cheng2:123456"));
        acls.add(new ACL(Perms.ALL, cheng1));
        acls.add(new ACL(Perms.READ, cheng2));
        acls.add(new ACL(Perms.DELETE | Perms.CREATE, cheng2));

        // 创建节点
        byte[] data = "spider-man".getBytes();
        curator.client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(acls, true)
                .forPath(nodePath, data);


//        curator.client.setACL().withACL(acls).forPath("/curatorNode");

        // 更新节点数据
// 		byte[] newData = "batman".getBytes();
//       curator.client.setData().withVersion(0).forPath(nodePath, newData);
//
        // 删除节点
//       curator.client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(0).forPath(nodePath);
//
        // 读取节点数据
//		Stat stat = new Stat();
//		byte[] data = curator.client.getData().storingStatIn(stat).forPath(nodePath);
//		System.out.println("节点" + nodePath + "的数据为: " + new String(data));
//		System.out.println("该节点的版本号为: " + stat.getVersion());

        curator.closeZkClient();
        System.out.println("当前客户端的状态: " + curator.client.getState());
    }

    public void closeZkClient() {
        if (client != null) {
            client.close();
        }
    }
}
