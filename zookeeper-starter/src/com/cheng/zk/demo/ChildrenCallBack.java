package com.cheng.zk.demo;

import java.util.List;

import static org.apache.zookeeper.AsyncCallback.ChildrenCallback;

/**
 * @author cheng
 *         2018/9/29 13:44
 */
public class ChildrenCallBack implements ChildrenCallback {

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children) {
        children.forEach(System.out::println);
        System.out.println("ChildrenCallback: " + path);
        System.out.println(ctx);
    }
}
