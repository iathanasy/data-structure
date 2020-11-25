package top.icss.leetcode.链表;

/**
 *  237. 删除链表中的节点
 *  https://leetcode-cn.com/problems/delete-node-in-a-linked-list/submissions/
 * @author cd.wang
 * @create 2020-11-23 14:31
 * @since 1.0.0
 */
public class _237_删除链表中的节点 {

    /**
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
     * @param node
     */
    public void deleteNode(ListNode node) {
        // 思路  把当前节点指针 指向下一个节点
        // 注意顺序 node.val 必须要先赋值   然后在赋值node.next
        ListNode temp = node;
        node.val = temp.next.val;
        node.next = temp.next.next;
    }
}
