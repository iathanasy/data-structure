package top.icss.leetcode.链表;

/**
 * 141. 环形链表
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * @author cd.wang
 * @create 2020-11-24 9:25
 * @since 1.0.0
 */
public class _141_环形链表 {
    /**
     * 使用快慢指针思路
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        // 快
        ListNode fast = head;
        // 慢
        ListNode slow = head;

        while(fast != null && fast.next != null){
            if(fast == slow){
                return true;
            }
            // 快 走两步
            fast = fast.next.next;
            // 慢 走一步 有环形就会相遇
            slow = slow.next;
        }
        return false;
    }
}
