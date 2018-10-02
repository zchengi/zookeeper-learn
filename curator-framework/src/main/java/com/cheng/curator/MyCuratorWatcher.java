package com.cheng.curator;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

/**
 * @author cheng
 *         2018/10/2 13:14
 */
public class MyCuratorWatcher implements CuratorWatcher {
    @Override
    public void process(WatchedEvent event) {
        System.out.println("触发 watcher，节点路径为: " + event.getPath());
    }
}
