package interview.lecode;

import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return null;

        // 先进后出
        Stack<ListNode> stack = new Stack<ListNode>();

        ListNode itor = head;
        while (itor != null) {
            itor.next = null;
            stack.push(itor);
            itor = itor.next;
        }

        ListNode vHeadNode = new ListNode();
        ListNode pre = vHeadNode;

        while (!stack.empty()) {
            pre.next = stack.pop();
            pre = pre.next;
        }
        return vHeadNode.next;
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        ListNode b = new ListNode(2, null);
        ListNode a = new ListNode(1, b);

        ListNode listNode = solution.reverseList(a);
        System.out.println(listNode);
    }
}