package com.stickpoint.ddmusic.common.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.utils
 * @Author: fntp
 * @CreateTime: 2022-12-28  09:31
 * @Description: TODO
 * @Version: 1.0
 */
public class MyThreadUtil {
    /**
     * 核心线程池大小
     */
    int corePoolSize = 2;
    /**
     * 最大线程池大小
     */
    int maximumPoolSize = 4;
    /**
     * 线程最大空闲时间
     */
    long keepAliveTime = 10;
    /**
     * 时间单位
     */
    TimeUnit unit = TimeUnit.SECONDS;
    /**
     * 线程等待队列
     */
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
    /**
     * 线程创建工厂
     */
    ThreadFactory threadFactory = new DdThreadFactory("serviceThread");
    /**
     * 拒绝策略
     */
    RejectedExecutionHandler handler = new MyIgnorePolicy();

    /**
     * 构造函数：初始化线程池
     * @param corePoolSize 线程池大小
     * @param maximumPoolSize 最大线程池大小
     * @param keepAliveTime 线程最大空闲时间
     */
    public MyThreadUtil(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }

    /**
     * 构造函数
     */
    public MyThreadUtil() {

    }

    /**
     * 默认线程池
     * @return 线程池
     */
    public ExecutorService createNewThreadPool(){
        return new ThreadPoolExecutor(this.corePoolSize, this.maximumPoolSize, this.keepAliveTime, this.unit,
                this.workQueue, this.threadFactory, this.handler);
    }

    /**
     * newFixedThreadPool：数量固定大小，该线程池重用在共享的无边界队列上运行的固定数量的线程。
     * 在任何时候，最多 corePoolSize（构造函数的参数，核心线程数与最大线程数相等）个线程都是活动的处理任务。
     * 如果在所有线程都处于活动状态时提交了其他任务，则它们将在队列中等待，直到某个线程可用为止。
     * 如果在关闭之前执行过程中由于执行失败导致任何线程终止，则在执行后续任务时将使用新线程代替。
     * 池中的线程将一直存在，直到明确将其关闭。
     * @param corePoolSize 核心线程数
     * @return 线程池
     */
    public ExecutorService newFixedThreadPool(int corePoolSize){
        return new ThreadPoolExecutor(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), this.threadFactory, this.handler);
    }

    /**
     * newCachedThreadPool：数量无上限，该线程池会根据需要创建，但是优先使用之前构造的线程。
     * 这些池通常将提高执行许多短期异步任务的程序的性能，如果没有可用的现有线程，则将创建一个新线程并将其添加到池中。
     * 当60S内未使用的线程将被终止并从缓存中删除。 因此，保持空闲时间足够长的池不会消耗任何资源。
     * @return 线程池
     */
    public ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), this.threadFactory, this.handler);
    }

    /**
     * newScheduledThreadPool：该线程池可以安排命令在给定的延迟后运行或定期执行。
     * @param corePoolSize 线程池核心线程大小
     * @return 线程池
     */
    public ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize, this.threadFactory, this.handler);
    }
}
