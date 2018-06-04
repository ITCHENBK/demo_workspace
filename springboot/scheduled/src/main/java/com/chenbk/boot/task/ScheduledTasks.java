package com.chenbk.boot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kang on 2018/6/4.
 */
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 上一次开始执行时间点之后5秒再执行
     */
    @Scheduled(fixedRate = 5000)
    public void task1() {
        System.out.println("task1 现在时间：" + dateFormat.format(new Date()));
    }

    /**
     * 上一次执行完毕时间点之后5秒再执行
     */
    @Scheduled(fixedDelay = 5000)
    public void task2() {
        System.out.println("task2 现在时间：" + dateFormat.format(new Date()));
    }

    /**
     * 第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
     */
    @Scheduled(initialDelay=1000, fixedRate=5000)
    public void task3() {
        System.out.println("task3 现在时间：" + dateFormat.format(new Date()));
    }

    /**
     * 通过cron表达式定义规则
     */
    @Scheduled(cron="*/5 * * * * *")
    public void task4() {
        System.out.println("task4 现在时间：" + dateFormat.format(new Date()));
    }
}
