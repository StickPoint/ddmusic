package com.stickpoint.ddmusic.common.thread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.utils
 * @Author: fntp
 * @CreateTime: 2022-12-28  09:33
 * @Description: TODO
 * @Version: 1.0
 */
public class DdThreadFactory implements ThreadFactory {

    private final String threadName;

    /**
     * 顶点音乐日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(DdThreadFactory.class);
    /**
     * 记录线程创建个数
     */
    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    /**
     * 创建顶点线程工厂的时候实现传参
     * @param threadName 传递一个线程名称
     */
    public DdThreadFactory(String threadName) {
        this.threadName = threadName;
    }

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable r) {
        // 当新的线程创建的时候，记录日志
        Thread thread = new Thread(r, "ddmusic-thread"+threadName+"-No" + mThreadNum.getAndIncrement());
        log.info(thread.getName() + " has been created");
        return thread;
    }
}
