package com.stickpoint.ddmusic.common.thread;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.utils
 * @Author: fntp
 * @CreateTime: 2022-12-28  09:33
 * @Description: TODO
 * @Version: 1.0
 */
@SuppressWarnings("unused")
public class DdThreadPollCenter implements ThreadFactory {

    /**
     * 线程名称
     */
    private static final String THREAD_NAME;

    /**
     * 顶点音乐线程日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(DdThreadPollCenter.class);

    /**
     * 记录顶点音乐线程创建个数
     */
    private final AtomicInteger mThreadNum = new AtomicInteger(1);
    /**
     * 独立业务固定线程个数的线程池
     * 线程池分配如下：
     * 下载线程池
     */
    private static final ExecutorService DOWNLOAD_EXECUTOR_SERVICE;
    /**
     * 搜索线程池
     */
    private static final ExecutorService SEARCH_EXECUTOR_SERVICE;
    /**
     * 多任务线程池
     */
    private static final ExecutorService OTHER_TASK_EXECUTOR_SERVICE;

    static {
        long keepAliveTime = 10;
        TimeUnit keepAliveTimeUnit = TimeUnit.MINUTES;
        int queSize = 100_000;
        // 下载线程池最多三个线程
        DOWNLOAD_EXECUTOR_SERVICE = new ThreadPoolExecutor(3, 3,
                keepAliveTime, keepAliveTimeUnit, new ArrayBlockingQueue<>(queSize),
                Executors.defaultThreadFactory());
        // 多任务线程池最多4个线程
        OTHER_TASK_EXECUTOR_SERVICE = new ThreadPoolExecutor(4,4,
                keepAliveTime, keepAliveTimeUnit, new ArrayBlockingQueue<>(queSize),
                Executors.defaultThreadFactory());
        // 搜索最多一个线程
        SEARCH_EXECUTOR_SERVICE = new ThreadPoolExecutor(1,1,
                keepAliveTime, keepAliveTimeUnit, new ArrayBlockingQueue<>(queSize),
                Executors.defaultThreadFactory());
        // 线程名称
        THREAD_NAME = "顶点音乐";
    }

    /**
     * 执行下载任务
     * @param downloadTask 传入一个下载动作流程，将这个流程包装在函数中，然后整体扔到单独的线程中去执行
     * @return 返回一个执行结果
     * @param <T> 泛型 不指定返回结果类型
     */
    public static <T> T doDdMusicDownloadTask(Callable<T> downloadTask){
        // 首先获取线程池
        try {
            Task<T> finalTask = new Task<>() {
                @Override
                protected T call() throws Exception {
                    return downloadTask.call();
                }
            };
            // 提交任务到线程池
            DOWNLOAD_EXECUTOR_SERVICE.execute(finalTask);
            // 获取线程池返回的结果
            // 监听Task的完成事件，并返回结果
            finalTask.setOnSucceeded(event -> log.info("下载Task请求执行成功！"));
            finalTask.setOnFailed(event -> {
                // 处理异常
                Throwable exception = finalTask.getException();
                log.error(exception.getMessage());
            });
            // 暂时返回null，真正的结果将在Task的回调函数中处理
            return finalTask.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(e.getMessage());
        } catch (ExecutionException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 执行搜索任务
     *
     * @param doSearchTask 传入一个搜索动作流程，将这个流程包装在函数中，然后整体扔到单独的线程中去执行
     * @param <T>          泛型 不指定返回结果类型
     */
    public static <T> void doDdMusicSearchTask(Callable<T> doSearchTask, Consumer<T> userConsumer){
        // 首先获取线程池
        try {
            Task<T> finalTask = new Task<>() {
                @Override
                protected T call() throws Exception {
                    return doSearchTask.call();
                }
            };
            // 提交任务到线程池
            SEARCH_EXECUTOR_SERVICE.execute(finalTask);
            // 获取线程池返回的结果
            // 监听Task的完成事件，并返回结果
            finalTask.setOnSucceeded(event -> {
                log.info("搜索Task请求执行成功！");
                // 执行更新UI操作
                Platform.runLater(()-> userConsumer.accept(finalTask.getValue()));
            });
            finalTask.setOnFailed(event -> {
                // 处理异常
                Throwable exception = finalTask.getException();
                log.error(exception.getMessage());
            });
            // 暂时返回null，真正的结果将在Task的回调函数中处理
            finalTask.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(e.getMessage());
        } catch (ExecutionException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 执行其他任务
     * @param doOtherTask 传入一个其他动作流程，将这个流程包装在函数中，然后整体扔到单独的线程中去执行
     * @return 返回一个执行结果
     * @param <T> 泛型 不指定返回结果类型
     */
    public static <T> T doDdMusicOtherTask(Callable<T> doOtherTask){
        // 首先获取线程池
        try {
            Task<T> finalTask = new Task<>() {
                @Override
                protected T call() throws Exception {
                    return doOtherTask.call();
                }
            };
            // 提交任务到线程池
            OTHER_TASK_EXECUTOR_SERVICE.execute(finalTask);
            // 获取线程池返回的结果
            // 监听Task的完成事件，并返回结果
            finalTask.setOnSucceeded(event -> log.info("其他Task请求执行成功！"));
            finalTask.setOnFailed(event -> {
                // 处理异常
                Throwable exception = finalTask.getException();
                log.error(exception.getMessage());
            });
            // 暂时返回null，真正的结果将在Task的回调函数中处理
            return finalTask.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(e.getMessage());
        } catch (ExecutionException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     * 创建线程 重新命名
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable r) {
        // 当新的线程创建的时候，记录日志
        Thread thread = new Thread(r, "ddmusic-thread-"+ THREAD_NAME +"-No" + mThreadNum.getAndIncrement());
        log.info("ddmusic {} thread has been created",thread.getName());
        return thread;
    }


}
