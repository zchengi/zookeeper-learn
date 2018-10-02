package com.cheng.curator;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author cheng
 *         2018/10/2 13:14
 */
public class MyWatcher implements Watcher {
    @Override
    public void process(WatchedEvent event) {
        System.out.println("触发 watcher，节点路径为: " + event.getPath());
    }
}
