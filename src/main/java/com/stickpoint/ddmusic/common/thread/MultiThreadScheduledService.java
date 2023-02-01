package com.stickpoint.ddmusic.common.thread;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.thread
 * @Author: fntp
 * @CreateTime: 2022-12-28  13:48
 * @Description: TODO
 * @Version: 1.0
 */
public class MultiThreadScheduledService extends ScheduledService<Object> {
    /**
     * 服务名称
     */
    private String name;

    /**
     * 计数器
     */
    private int counter = 0;

    /**
     * 构造函数
     * @param name 服务名称
     */
    public MultiThreadScheduledService(String name) {
        this.name = name;
    }

    @Override
    protected Task<Object> createTask() {
        return new Task<Object>() {
            @Override
            protected Integer call() throws Exception {
                counter++;
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("Now Time: " + sdf.format(date));
                System.out.println("ScheduledService Count: " + counter);
                return counter;
            }
        };
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        System.out.println(this.toString() + " is Done!");
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        System.out.println(this.toString() + " is Cancelled!");
    }

    @Override
    protected void failed() {
        super.failed();
        System.out.println(this.toString() + " is Failed!");
    }

    @Override
    protected void running(){
        super.running();
        System.out.println(this.toString() + " is Running!");
    }

    @Override
    protected void scheduled(){
        super.scheduled();
        System.out.println(this.toString() + " is Scheduled!");
    }

    @Override
    public void reset(){
        super.reset();
        System.out.println(this.toString() + " is Reset!");
    }

    /**
     * 获取服务名称
     *
     * @return 服务名称
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MyScheduledService [ name = " + name + " ]";
    }
}
