package com.interview.basic.thread.thread.productAndConsumer;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-07 22:59
 */
public class TaskMsg {

    private long taskId;

    private Object data;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
