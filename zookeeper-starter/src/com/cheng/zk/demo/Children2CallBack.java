package com.cheng.zk.demo;

import org.apache.zookeeper.data.Stat;

import java.util.List;

import static org.apache.zookeeper.AsyncCallback.Children2Callback;

/**
 * @author cheng
 *         2018/9/29 13:45
 */
public class Children2CallBack implements Children2Callback {

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        children.forEach(System.out::println);
        System.out.println("Children2CallBack: " + path);
        System.out.println(ctx);
        System.out.println(stat.toString());
    }
}
