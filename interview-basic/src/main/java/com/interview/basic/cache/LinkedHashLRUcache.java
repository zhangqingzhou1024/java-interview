package com.interview.basic.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-04-01 21:12
 */
public class LinkedHashLRUcache<k, v> {

    /**
     * LinkedHashMap（自身实现了ＬＲＵ算法）
     * 1.有序
     * 2.每次访问一个元素，都会提到最后面去
     */
    private static class InternalLRUcache<k, v> extends LinkedHashMap<k, v> {
        private final int limit;

        private InternalLRUcache(int limit) {
            super(16, 0.75f, true);
            this.limit = limit;
        }

        //是否删除最老的数据
        @Override
        protected boolean removeEldestEntry(Map.Entry<k, v> eldest) {
            return size() > limit;
        }
    }

    private final int limit;
    private final InternalLRUcache<k, v> internalLRUcache;


    public LinkedHashLRUcache(int limit) {
        // Assert.state(limit > 0, "limit必须大于0");
        this.limit = limit;
        this.internalLRUcache = new InternalLRUcache(limit);
    }


    public void put(k key, v value) {
        this.internalLRUcache.put(key, value);
    }

    public v get(k key) {
        return this.internalLRUcache.get(key);
    }

    public void remove(k key) {
        this.internalLRUcache.remove(key);
    }

    public int size() {
        return this.internalLRUcache.size();
    }

    public void clear() {
        this.internalLRUcache.clear();
    }

    @Override
    public String toString() {
        return internalLRUcache.toString();
    }

}
