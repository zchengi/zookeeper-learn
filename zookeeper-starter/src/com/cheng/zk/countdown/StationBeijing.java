package com.cheng.zk.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author cheng
 *         2018/9/29 12:55
 */
public class StationBeijing extends AbstractDangerCenter {

    public StationBeijing(CountDownLatch countDown) {
        super(countDown, "北京调度站");
    }

    @Override
    public void check() {
        System.out.println("正在检查 [" + this.getStation() + "]...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("检查 [" + this.getStation() + "] 完毕，可以发车");
    }
}
