package com.cheng.zk.demo;

import static org.apache.zookeeper.AsyncCallback.StringCallback;

/**
 * @author cheng
 *         2018/9/28 16:47
 */
public class CreateCallBack implements StringCallback {
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("创建节点: " + path);
        System.out.println(ctx);
    }
}
