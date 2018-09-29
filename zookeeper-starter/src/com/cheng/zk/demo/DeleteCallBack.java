package com.cheng.zk.demo;

import org.apache.zookeeper.AsyncCallback;

/**
 * @author cheng
 *         2018/9/28 16:47
 */
public class DeleteCallBack implements AsyncCallback.VoidCallback {
    @Override
    public void processResult(int rc, String path, Object ctx) {
        System.out.println("删除节点: " + path);
        System.out.println(ctx);
    }
}
