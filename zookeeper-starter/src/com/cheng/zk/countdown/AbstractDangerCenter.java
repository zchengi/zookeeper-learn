package com.cheng.zk.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * 抽象类，用于演示危化品化工车监控中心 统一检查
 *
 * @author cheng
 *         2018/9/29 12:49
 */
public abstract class AbstractDangerCenter implements Runnable {

    /**
     * 计数器
     */
    private CountDownLatch countDown;

    /**
     * 调度站
     */
    private String station;

    /**
     * 调度站针对当前自己的站点进行检查，是否检查 ok 的标志
     */
    private boolean ok;

    public AbstractDangerCenter(CountDownLatch countDown, String station) {
        this.countDown = countDown;
        this.station = station;
        this.ok = false;
    }

    @Override
    public void run() {
        try {
            check();
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (countDown != null) {
                countDown.countDown();
            }
        }
    }

    /**
     * 检查危化品车
     * 蒸罐，汽油轮胎，gps···
     */
    public abstract void check();

    public CountDownLatch getCountDown() {
        return countDown;
    }

    public void setCountDown(CountDownLatch countDown) {
        this.countDown = countDown;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
