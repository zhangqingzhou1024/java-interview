package com.interview.basic.cache;

import lombok.Data;
import lombok.Getter;

/**
 * 对缓存value值的包装类
 *
 * @author zqz
 * @date 2019/12/10 17:39
 */
public class CachedValue<V> {
    private V value;

    private long initTime;

    private int timeout;

    public CachedValue(V value) {
        this.value = value;
    }

    CachedValue(V value, int timeout, long initTime) {
        this.value = value;
        this.timeout = timeout;
        this.initTime = initTime;
    }

    public V getValue() {
        return value;
    }

    void setValue(V value) {
        this.value = value;
    }

    long getInitTime() {
        return initTime;
    }

    int getTimeout() {
        return timeout;
    }
}
