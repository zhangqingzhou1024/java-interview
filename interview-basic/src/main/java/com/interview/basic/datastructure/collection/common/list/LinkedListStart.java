package com.interview.basic.datastructure.collection.common.list;

/**
 * LinkedList 学习
 * http://note.youdao.com/ynoteshare1/index.html?id=7db664708f88a71957c0fbc8b6f4bde9&type=note
 *
 * @author zqz
 * @version 1.0
 * @date 2020-01-04 21:23
 */
public class LinkedListStart {

    public static void main(String[] args) {
        // 1. linkedList 基于链表实现，通过属性 prev、next进行串联
       /* private static class Node<E> {
            E item;
            java.util.LinkedList.Node<E> next;
            java.util.LinkedList.Node<E> prev;

            Node(java.util.LinkedList.Node<E> prev, E element, java.util.LinkedList.Node<E> next) {
                this.item = element;
                this.next = next;
                this.prev = prev;
            }
        }*/

        java.util.LinkedList linkedList = new java.util.LinkedList();


        // 2. list中数组扩容操作
        /**
         * Links e as last element.
         */
       /* void linkLast(E e) {
            final java.util.LinkedList.Node<E> l = last;
            final java.util.LinkedList.Node<E> newNode = new java.util.LinkedList.Node<>(l, e, null);
            last = newNode;
            if (l == null)
                first = newNode;
            else
                l.next = newNode;
            size++;
            modCount++;
        }*/

        linkedList.add("test");
        linkedList.add("test02");

        linkedList.add(1, 3);

        // 3。当前节点，左右节点进行链接
      /*  E unlink(Node<E> x) {
            // assert x != null;
            final E element = x.item;
            final java.util.LinkedList.Node<E> next = x.next;
            final java.util.LinkedList.Node<E> prev = x.prev;

            if (prev == null) {
                first = next;
            } else {
                prev.next = next;
                x.prev = null;
            }

            if (next == null) {
                last = prev;
            } else {
                next.prev = prev;
                x.next = null;
            }

            x.item = null;
            size--;
            modCount++;
            return element;
        }*/

        linkedList.remove(0);
        linkedList.remove("test");

        for (Object o : linkedList) {
            System.out.println(o);
        }

        // 4. 如果指定索引位置进行查询，效率很慢-二分查找
       /* java.util.LinkedList.Node<E> node(int index) {
            // assert isElementIndex(index);

            if (index < (size >> 1)) {
                java.util.LinkedList.Node<E> x = first;
                for (int i = 0; i < index; i++)
                    x = x.next;
                return x;
            } else {
                java.util.LinkedList.Node<E> x = last;
                for (int i = size - 1; i > index; i--)
                    x = x.prev;
                return x;
            }
        }*/
        Object o = linkedList.get(0);
        System.out.println(o);
    }
}
