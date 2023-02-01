package com.stickpoint.ddmusic.common.thread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.utils
 * @Author: fntp
 * @CreateTime: 2022-12-28  09:47
 * @Description: TODO
 * @Version: 1.0
 */
public class MyIgnorePolicy implements RejectedExecutionHandler {

    /**
     * 顶点音乐日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(MyIgnorePolicy.class);

    /**
     * Method that may be invoked by a {@link ThreadPoolExecutor} when
     * {@link ThreadPoolExecutor#execute execute} cannot accept a
     * task.  This may occur when no more threads or queue slots are
     * available because their bounds would be exceeded, or upon
     * shutdown of the Executor.
     *
     * <p>In the absence of other alternatives, the method may throw
     * an unchecked {@link RejectedExecutionException}, which will be
     * propagated to the caller of {@code execute}.
     *
     * @param r        the runnable task requested to be executed
     * @param executor the executor attempting to execute this task
     * @throws RejectedExecutionException if there is no remedy
     */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.error(r.toString().concat("rejected"));
    }

}
