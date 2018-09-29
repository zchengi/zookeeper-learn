package com.cheng.zk.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author cheng
 *         2018/9/29 12:59
 */
public class StationHangzhou extends AbstractDangerCenter {

    public StationHangzhou(CountDownLatch countDown) {
        super(countDown, "杭州调度站");
    }

    @Override
    public void check() {
        System.out.println("正在检查 [" + this.getStation() + "]...");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("检查 [" + this.getStation() + "] 完毕，可以发车");
    }
}
