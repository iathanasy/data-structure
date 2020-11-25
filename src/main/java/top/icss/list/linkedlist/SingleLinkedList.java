//package top.icss.list.linkedlist;
//
///**
// * 单链表 实现
// * @author cd.wang
// * @create 2020-11-18 11:27
// * @since 1.0.0
// */
//public class SingleLinkedList<E> {
//    // 保存链表实际长度
//    private int size;
//    // 保存链表头节点，仅作用于起点， 不存储数据
//    private Node<E> head;
//
//    public SingleLinkedList(Node<E> head){
//        this.head = head;
//    }
//
//    /**
//     * 在链表末尾添加节点数据
//     * @param data 数据
//     */
//    public void addLastData(E data){
//        Node<E> newNode = new Node<E>(data);
//        // 临时变量保存 头节点，
//        Node<E> temp = head;
//        // 遍历链表  第一次进入为null
//        while(temp.next != null){
//            temp = temp.next;
//        }
//        // 在链表末尾添加节点，链表长度 加 1
//        temp.next = newNode;
//        size++;
//    }
//
//    /**
//     * 链表末尾添加Node节点
//     * @param newNode
//     */
//    public void addLastNode(Node<E> newNode){
//        Node<E> temp = head;
//        while(temp.next != null){
//            temp = temp.next;
//        }
//        temp.next = newNode;
//        size++;
//    }
//
//    /**
//     * 在链表指定位置 插入节点
//     * @param node 待插入节点
//     * @param index 指定位置(1 ~ n, 1 表示第一个节点的位置)
//     */
//    public void insert(Node<E> node, int index){
//        Node<E> temp = head;
//        // 节点越界
//        if(index < 1 || index > size){
//            throw new IndexOutOfBoundsException("Index: "+ index + ", Size: "+ size);
//        }
//        // 节点为末尾
//        if(index == size){
//            addLastNode(node);
//            return;
//        }
//        // 节点不是链表末尾，遍历找到插入位置
//        while(index != 1){
//            temp = temp.next;
//            index--;
//        }
//        // A -> B 变为 A -> C -> B， 即 A.next = B 变为 C.next = A.next, A.next = C，即 A 指向 C，C 指向 B。
//        // index = 1,  此处 node 为数据 "C"， node.next 为 null ;
//        // temp为头节点、temp.next 数据为 "JAVA";
//        // 把待添加的节点给temp.next（头部的下一个节点）
//        node.next = temp.next;
//        temp.next = node;
//        size++;
//    }
//
//    public int size(){
//        return size;
//    }
//    /**
//     * 输出链表
//     */
//    public void showList(){
//        // 使用临时变量保存第一个节点，
//        Node<E> temp = head.next;
//        if(size == 0){
//            System.out.println("链表为空！");
//            return;
//        }
//        System.out.println("当前链表长度为： "+ size + " ,当前链表为：");
//        while(temp != null){
//            System.out.print(temp +"----> ");
//            temp = temp.next;
//        }
//        System.out.println();
//    }
//
//    /**
//     * 删除最后一个节点
//     */
//    public void deleteLastNode(){
//        Node<E> temp = head;
//        if(size == 0){
//            System.out.println("链表为空！");
//            return;
//        }
//        // 找到最后一个节点
//        while(temp.next.next != null){
//            temp = temp.next;
//        }
//        // 赋值为null
//        temp.next = null;
//        size--;
//    }
//
//    /**
//     * 删除指定位置元素
//     * @param index
//     */
//    public void delete(int index){
//        Node<E> temp = head;
//        // 节点越界
//        if(index < 1 || index > size){
//            throw new IndexOutOfBoundsException("Index: "+ index + ", Size: "+ size);
//        }
//        //节点为末尾 ，调用末尾删除
//        if(index == size){
//            deleteLastNode();
//            return;
//        }
//        // 遍历链表，找到删除位置
//        while(index != 1){
//            index--;
//            temp = temp.next;
//        }
//        // 赋值 下下一个 比如 abc 把b删除了 a.next.next
//        temp.next = temp.next.next;
//        size--;
//    }
//
//
//    /**
//     * 数据节点
//     * @param <E>
//     */
//    static class Node<E>{
//        // 数据域，存储节点数据
//        E data;
//        // 指针域， 指向下一个节点
//        Node<E> next;
//
//        public Node(E data){
//            this.data = data;
//        }
//
//        public Node(E data, Node<E> next){
//            this.data = data;
//            this.next = next;
//        }
//
//        @Override
//        public String toString() {
//            return "Node{" +
//                    "data=" + data +
//                    '}';
//        }
//    }
//
//    public static void main(String[] args) {
//        // 创建一个单链表
//        SingleLinkedList<String> singleLinkedList = new SingleLinkedList(new Node("Header"));
//        singleLinkedList.showList();
//        System.out.println("=======================================");
//
//        singleLinkedList.addLastData("Java");
//        singleLinkedList.addLastNode(new Node<String>("Python"));
//        singleLinkedList.insert(new Node<String>("C"), 1);
//        singleLinkedList.insert(new Node<String>("Scalc"), 3);
//        singleLinkedList.showList();
//        System.out.println("=======================================");
//
//        singleLinkedList.deleteLastNode();
//        singleLinkedList.delete(2);
//
//        singleLinkedList.showList();
//        System.out.println("=======================================");
//    }
//
//}
//
//
