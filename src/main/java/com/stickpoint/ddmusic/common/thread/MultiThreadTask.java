package com.stickpoint.ddmusic.common.thread;

import javafx.concurrent.Task;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.thread
 * @Author: fntp
 * @CreateTime: 2022-12-28  13:39
 * @Description:
 * 再次注意！这里是基于Task构建的多任务Thread异步线程，不可以重复执行！
 * Task继承了java.util, concurrency.FutureTask类。
 *  * Task是不可以重复执行的Worker
 *  * 运行方式：1. new Thread(task).start();
 *  * 2.ExecutorService.submit(task);
 *  * 3.其他。
 *  * Task中有许多updateXXX方法用于改变相应的property。setOnXXX方法用于设置EventHandler.
 *  * 继承Task需要覆盖其call()方法，call()方法会在后台线程中被调用。
 *  * 由于java中并没有安全的线程中断机制，所以你必须在call()方法内部对isCancelled()进行判断
 * @Version: 1.0
 */
public class MultiThreadTask extends Task<Void> {
    /**
     * 线程名称
     */
    private String name;

    /**
     * 构造函数
     * @param name 线程名称
     */
    public MultiThreadTask(String name) {
        this.name = name;
    }

    @Override
    protected Void call() {
        System.out.println(this.toString() + " is running!");
        try {
            Thread.sleep(100);
        } catch (InterruptedException interrupted) {
            if (isCancelled()) {
                updateMessage("Cancelled");
            }
        }
        updateMessage("Done!");
        return null;
    }

    /**
     * 获取任务名称
     *
     * @return 任务名称
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MyTask [ name = " + name + " ]";
    }
}
