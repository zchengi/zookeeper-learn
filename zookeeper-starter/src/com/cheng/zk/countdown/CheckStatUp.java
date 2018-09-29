package com.cheng.zk.countdown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 6-7 CountDownLatch 的使用
 *
 * @author cheng
 *         2018/9/29 12:59
 */
public class CheckStatUp {

    private static List<AbstractDangerCenter> stationList;

    private static CountDownLatch countDown;

    public CheckStatUp() {
    }

    public static boolean checkAllStations() throws Exception {

        // 初始化3个调度站
        countDown = new CountDownLatch(3);

        stationList = new ArrayList<>();
        stationList.add(new StationBeijing(countDown));
        stationList.add(new StationHangzhou(countDown));
        stationList.add(new StationShandong(countDown));


        // 使用线程池
        Executor executor = Executors.newFixedThreadPool(stationList.size());

        for (AbstractDangerCenter center : stationList) {
            executor.execute(center);
        }

        // 等待线程执行完毕
        countDown.await();

        for (AbstractDangerCenter center : stationList) {
            if (!center.isOk()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {

        boolean result = CheckStatUp.checkAllStations();
        System.out.println("监控中心针对所有危化品调度站点的检查结果为: " + result);
    }
}
