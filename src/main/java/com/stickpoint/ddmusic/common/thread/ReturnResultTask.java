package com.stickpoint.ddmusic.common.thread;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.concurrent.Task;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.thread
 * @Author: fntp
 * @CreateTime: 2022-12-28  13:37
 * @Description:  携带返回值的Task
 * @Version: 1.0
 */
public class ReturnResultTask extends Task<Object> {
    /**
     * 任务名称
     */
    private String name;

    /**
     * 只读对象包装器
     */
    private ReadOnlyObjectWrapper<String> results =
            new ReadOnlyObjectWrapper<>(this, "Results", "");

    /**
     * 获取只读对象
     * @return 只读对象
     */
    public final String getResults() {
        return results.get();
    }

    /**
     * 获取只读对象属性
     * @return 对象属性
     */
    public final ReadOnlyObjectProperty<String> resultsProperty() {
        return results.getReadOnlyProperty();
    }

    /**
     * 构造函数
     * @param name 任务名称
     */
    public ReturnResultTask(String name) {
        this.name = name;
    }

    @Override
    protected String call() {
        results.set("Results Get");
        try {
            Thread.sleep(100);
        } catch (InterruptedException interrupted) {
            if (isCancelled()) {
                updateMessage("Cancelled");
            }
        }
        updateMessage("Done!");
        return results.get();
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        updateMessage("Done!");
        System.out.println(this.toString() + " is Done!");
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        updateMessage("Cancelled!");
        System.out.println(this.toString() + " is Cancelled!");
    }

    @Override
    protected void failed() {
        super.failed();
        updateMessage("Failed!");
        System.out.println(this.toString() + " is Failed!");
    }

    @Override
    protected void running(){
        super.running();
        updateMessage("Running!");
        System.out.println(this.toString() + " is Running!");
    }

    @Override
    protected void scheduled(){
        super.scheduled();
        updateMessage("Scheduled!");
        System.out.println(this.toString() + " is Scheduled!");
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
