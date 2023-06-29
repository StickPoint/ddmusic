package com.stickpoint.ddmusic.common.thread;
import com.stickpoint.ddmusic.common.notion.Info;
import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.*;

/**
 * @author fntp
 */
@Info("线程池工具类")
public class ThreadUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = -2672990615083303115L;

    private static final ExecutorService POOL;

    static {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 10;
        TimeUnit keepAliveTimeUnit = TimeUnit.MINUTES;
        int queSize = 100_000;
        POOL = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, keepAliveTimeUnit, new ArrayBlockingQueue<>(queSize),
                Executors.defaultThreadFactory());
    }

    /**
     * 获取线程池
     * @return 线程池
     */
    public static ExecutorService getPool() {
        return POOL;
    }

}
