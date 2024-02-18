package interview.lecode.linked;

import java.util.LinkedList;

/**
 * 单链表反转
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-02 11:42
 */
public class ReversalLink {

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");
        linkedList.add("E");
        linkedList.add("F");
        linkedList.add("G");

        int splitSize = 3;
        int linkedListSize = linkedList.size();
        linkedList.getFirst();
        linkedList.removeFirst();
        linkedList.getFirst();
        linkedList.removeFirst();
        System.out.println(linkedList.getFirst());
    }
}
