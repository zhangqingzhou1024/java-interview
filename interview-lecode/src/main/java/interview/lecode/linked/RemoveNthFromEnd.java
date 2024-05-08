package interview.lecode.linked;

import interview.lecode.ListNode;

/**
 * @author zqz
 * @date 2024-04-18 0:20
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {
        //ListNode c = new ListNode(3, null);
        ListNode b = new ListNode(2, null);
        ListNode a = new ListNode(1, b);

        System.out.println(a);
        ListNode listNode = removeNthFromEnd(a, 2);

        System.out.println(listNode);

    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }

        // 处理正常值
        /**
         * 这是链表，问题关键是怎么确定是倒数第n个元素
         * 如果是正着的则非常好处理
         * 即：删除 正数第几个元素
         * 所以能否先反转链表呢？反转后进行删除，删除后再次进行反转 即得到想要的结果
         */

        // 1、反转链表
        ListNode reverseListNode = reverseListNode(head);

        // 2、删除相对于节点
        if (n == 1) {
            reverseListNode = reverseListNode.next;
        } else {
            // 双指针
            ListNode pre = reverseListNode;
            ListNode cur = reverseListNode.next;
            int indexCount = 2;
            // 执行完之后 pre 为要删除的前一个节点，cur为要删除的节点
            while (cur != null && indexCount < n) {
                pre = cur;
                cur = cur.next;
                indexCount++;
            }
            // 节点移除
            if (cur != null) {
                pre.next = cur.next;
            }
        }

        // 再次反转
        return reverseListNode(reverseListNode);
    }

    private static ListNode reverseListNode(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode temp = cur.next;
            // 反转链表指向
            cur.next = pre;

            // 下一轮赋值
            pre = cur;
            cur = temp;
        }

        return pre;
    }
}
