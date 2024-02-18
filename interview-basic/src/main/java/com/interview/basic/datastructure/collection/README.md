java 常用面试-集合系列
主要分为
1. 常规（非并发）集合
2. 并发集合


集合
集合分为两大块：java.util 包下的非线程安全集合和 java.util.concurrent 下的线程安全集合。
List
ArrayList 与 LinkedList 的实现和区别

Map
HashMap：了解其数据结构、hash 冲突如何解决（链表和红黑树）、扩容时机、扩容时避免 rehash 的优化
LinkedHashMap：了解基本原理、哪两种有序、如何用它实现 LRU
TreeMap：了解数据结构、了解其 key 对象为什么必须要实现 Compare 接口、如何用它实现一致性哈希

Set
Set 基本上都是由对应的 map 实现，简单看看就好

常见问题
hashmap 如何解决 hash 冲突，为什么 hashmap 中的链表需要转成红黑树？
hashmap 什么时候会触发扩容？
jdk1.8 之前并发操作 hashmap 时为什么会有死循环的问题？
hashmap 扩容时每个 entry 需要再计算一次 hash 吗？
hashmap 的数组长度为什么要保证是 2 的幂？
如何用 LinkedHashMap 实现 LRU ？
如何用 TreeMap 实现一致性 hash ？

线程安全的集合
Collections.synchronized
了解其实现原理
CopyOnWriteArrayList
了解写时复制机制、了解其适用场景、思考为什么没有 ConcurrentArrayList
ConcurrentHashMap
了解实现原理、扩容时做的优化、与 HashTable 对比。
BlockingQueue
了解 LinkedBlockingQueue、ArrayBlockingQueue、DelayQueue、SynchronousQueue

常见问题
ConcurrentHashMap 是如何在保证并发安全的同时提高性能？
ConcurrentHashMap 是如何让多线程同时参与扩容？
LinkedBlockingQueue、DelayQueue 是如何实现的？
CopyOnWriteArrayList 是如何保证线程安全的？