package top.icss.leetcode.链表;

/**
 * 206. 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author cd.wang
 * @create 2020-11-23 15:06
 * @since 1.0.0
 */
public class _206_反转链表 {

    /**
     * 反转一个单链表。
     * 交换法 迭代方法
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode newNode = null;
        //每次循环，都将当前节点指向它前面的节点，然后当前节点和前节点后移
        while(head != null){
            // 2 -> 3
            //临时节点，暂存当前节点的下一节点，用于后移
            ListNode temp = head.next;
            // 2 = null -> 3 = 1
            //将当前节点指向它前面的节点
            head.next = newNode;
            // null = 1 -> 1 = 2
            //前指针后移
            newNode = head;
            // 1 = 2 -> 2 = 3
            //当前指针后移
            head = temp;
        }
        return newNode;
    }

    /**
     * 递归
     *
     * 不妨假设链表为1，2，3，4，5。按照递归，
     * 当执行reverseList（5）的时候返回了5这个节点，
     * reverseList(4)中的newNode就是5这个节点，
     * 我们看看reverseList（4）接下来执行完之后，
     * 5->next = 4, 4->next = null。这时候返回了newNode这个节点，
     * 也就是链表5->4->null，接下来执行reverseList（3），
     * 代码解析为4->next = 3,3->next = null，
     * 这个时候newNode就变成了，5->4->3->null, reverseList(2), reverseList(1)依次类推，
     * newNode就是:5->4->3->2->1->null
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head){
        if(head == null || head.next == null) return head;

        System.out.println(head.val);
        ListNode newNode = reverseList2(head.next);
        System.out.println("--------");
        System.out.println(head.val);
        //
        head.next.next = head;
        head.next = null;
        return newNode;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode listNode = reverseList2(head);
        System.out.println(listNode);

    }
}
