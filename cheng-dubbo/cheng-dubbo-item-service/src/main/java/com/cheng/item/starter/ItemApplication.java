package com.cheng.item.starter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 使用 main 主线程启动 dubbo 服务
 *
 * @author cheng
 *         2018/10/6 13:46
 */
public class ItemApplication {
    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:spring/spring-context.xml"});
        context.start();

        System.in.read();
    }
}
