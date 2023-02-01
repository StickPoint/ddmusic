package com.stickpoint.ddmusic.common.thread;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.thread
 * @Author: fntp
 * @CreateTime: 2022-12-28  13:41
 * @Description:
 * Service会封装Task，并负责后台执行它封装的Task。
 * Service是可重复执行的。
 * Service可以被注入Executor依赖，并通过注入的Executor执行实际的Task。否则Service只会构造一个简单的Thread执行。
 * Service通过Service的start()方法执行任务。
 * 可以通过WorkerStateEvent监听变化。
 * @Version: 1.0
 */
public class MultiThreadService extends Service<Object> {
    /**
     * 顶点音乐日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(MultiThreadService.class);
    
    /**
     * 服务名称
     */
    private final String name;

    /**
     * 构造函数
     * @param name 服务名称
     */
    public MultiThreadService(String name) {
        this.name = name;
    }

    @Override
    protected Task<Object> createTask(){
        return new Task<>() {
            @Override
            protected Integer call() throws Exception {
                int iterations;
                for (iterations = 0; iterations < 10; iterations++) {
                    if (isCancelled()) {
                        break;
                    }
                   log.info("Service Task Iteration: " + iterations);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interrupted) {
                    if (isCancelled()) {
                        updateMessage("Cancelled");
                    }
                }
                return iterations;
            }
        };
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        log.info(this.toString() + " is Done!");
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        log.info(this.toString() + " is Cancelled!");
    }

    @Override
    protected void failed() {
        super.failed();
        log.info(this.toString() + " is Failed!");
    }

    @Override
    protected void running(){
        super.running();
        log.info(this.toString() + " is Running!");
    }

    @Override
    protected void scheduled(){
        super.scheduled();
        log.info(this.toString() + " is Scheduled!");
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
        return "MyService [ name = " + name + " ]";
    }
}
