package com.stickpoint.ddmusic.common.thread;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.utils
 * @Author: fntp
 * @CreateTime: 2022-12-28  09:33
 * @Description: TODO
 * @Version: 1.0
 */
@SuppressWarnings("unused")
public class DdThreadCenter implements ThreadFactory {

    /**
     * 线程名称
     */
    private final String threadName;

    /**
     * 顶点音乐线程日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(DdThreadCenter.class);

    /**
     * 记录顶点音乐线程创建个数
     */
    private final AtomicInteger mThreadNum = new AtomicInteger(1);
    /**
     * 独立业务固定线程个数的线程池
     * 线程池分配如下：
     * 下载线程池
     */
    private final ExecutorService downloadExecutorService;
    /**
     * 搜索线程池
     */
    private final ExecutorService searchExecutorService;
    /**
     * 多任务线程池
     */
    private final ExecutorService otherTaskExecutorService;

    /**
     * 创建顶点线程工厂的时候实现传参
     * @param threadName 传递一个线程名称
     */
    public DdThreadCenter(String threadName) {
        this.threadName = threadName;
        // （1）下载最多五个线程
        int downloadExecutorPollSize = 5;
        this.downloadExecutorService = newFixedThreadPool(downloadExecutorPollSize,this);
        // （2）搜索一个线程
        int searchExecutorPollSize = 1;
        this.searchExecutorService = newFixedThreadPool(searchExecutorPollSize,this);
        // （3）处理多任务4个线程
        int otherTaskExecutorPollSize = 4;
        this.otherTaskExecutorService = newFixedThreadPool(otherTaskExecutorPollSize,this);
    }

    /**
     * 获取下载线程池
     * @return 返回一个下载线程池
     */
    private ExecutorService getDownloadExecutorService(){
        return this.downloadExecutorService;
    }

    /**
     * 获取其他任务线程池
     * @return 返回执行其他任务的线程池
     */
    private ExecutorService getOtherTaskExecutorService(){
        return this.otherTaskExecutorService;
    }

    /**
     * 获取搜索任务线程池
     * @return 返回一个搜索任务的线程池
     */
    private ExecutorService getSearchExecutorService(){
        return this.searchExecutorService;
    }


    /**
     * 执行下载任务
     * @param downloadTask 传入一个下载动作流程，将这个流程包装在函数中，然后整体扔到单独的线程中去执行
     * @return 返回一个执行结果
     * @param <T> 泛型 不指定返回结果类型
     */
    public <T> T doDdMusicDownloadTask(Callable<T> downloadTask){
        // 首先获取线程池
        try {
            ExecutorService executorService = getDownloadExecutorService();
            Task<T> finalTask = new Task<>() {
                @Override
                protected T call() throws Exception {
                    return downloadTask.call();
                }
            };
            // 提交任务到线程池
            executorService.execute(finalTask);
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
     * @param doSearchTask 传入一个搜索动作流程，将这个流程包装在函数中，然后整体扔到单独的线程中去执行
     * @return 返回一个执行结果
     * @param <T> 泛型 不指定返回结果类型
     */
    public <T> T doDdMusicSearchTask(Callable<T> doSearchTask){
        // 首先获取线程池
        try {
            ExecutorService executorService = getDownloadExecutorService();
            Task<T> finalTask = new Task<>() {
                @Override
                protected T call() throws Exception {
                    return doSearchTask.call();
                }
            };
            // 提交任务到线程池
            executorService.execute(finalTask);
            // 获取线程池返回的结果
            // 监听Task的完成事件，并返回结果
            finalTask.setOnSucceeded(event -> log.info("搜索Task请求执行成功！"));
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
     * 执行其他任务
     * @param doOtherTask 传入一个其他动作流程，将这个流程包装在函数中，然后整体扔到单独的线程中去执行
     * @return 返回一个执行结果
     * @param <T> 泛型 不指定返回结果类型
     */
    public <T> T doDdMusicOtherTask(Callable<T> doOtherTask){
        // 首先获取线程池
        try {
            ExecutorService executorService = getDownloadExecutorService();
            Task<T> finalTask = new Task<>() {
                @Override
                protected T call() throws Exception {
                    return doOtherTask.call();
                }
            };
            // 提交任务到线程池
            executorService.execute(finalTask);
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
        Thread thread = new Thread(r, "ddmusic-thread-"+threadName+"-No" + mThreadNum.getAndIncrement());
        log.info("ddmusic {} thread has been created",thread.getName());
        return thread;
    }


}
