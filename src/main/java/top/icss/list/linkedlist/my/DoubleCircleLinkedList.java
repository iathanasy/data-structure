package top.icss.list.linkedlist.my;

import top.icss.list.IAbstractList;
import top.icss.list.IList;

/**
 * 双向循环链表 实现 List
 *
 * 如何发挥循环链表的最大威力：
 *  可以考虑增设1个成员变量、3个方法：
 * current ：用于指向某个节点
 *
 * void reset() ：让 current 指向头结点 first
 * E next() ：让 current 往后走一步，也就是 current = current.next
 * E remove() ：删除 current 指向的节点，删除成功后让 current 指向下一个节点
 *
 * @author cd.wang
 * @create 2020-11-26 9:51
 * @since 1.0.0
 */
public class DoubleCircleLinkedList<E> extends IAbstractList<E> {
    /**
     * 头结点
     */
    private Node<E> first;
    /**
     * 尾节点
     */
    private Node<E> last;

    /**
     * 指针访问当前节点
     */
    private Node<E> current;

    /**
     * 根据索引找到节点
     * @param index
     * @return
     */
    private Node<E> node(int index){
        rangeCheck(index);

        // 索引小于一半从前往后找
        if((size >> 1) > index){
            Node<E> node = first;
            for (int i = 0; i < index; i++){
                node = node.next;
            }
            return node;
        }else{
            // 索引大于一半从后往前找
            Node<E> node = last;
            for(int i = (size - 1); i > index; i--){
                node = node.prev;
            }
            return node;
        }
    }

    /**
     * 让 current 指向头结点 first
     */
    public void reset(){
        current = first;
    }

    /**
     * 让 current 往后走一步，也就是 current = current.next
     * @return
     */
    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.element;
    }

    /**
     * 删除 current 节点
     */
    public E remove() {
        if (current == null) return null;
        Node<E> next = current.next;
        E element = remove(current);
        if(size == 0){
            current = null;
        }else{
            current = next;
        }
        return element;
    }


    ////////////////////////////////////////////////////////////////////

    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    public E get(int index) {
        return node(index).element;
    }

    public E set(int index, E element) {
        Node<E> oldNode = node(index);
        oldNode.element = element;
        return oldNode.element;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);

        //最后面添加 节点
        if(index == size){
            Node<E> oldLast = last;
            last = new Node<E>(oldLast, element, first);
            // 如果是链表第一个元素
            if(oldLast == null){
                // 头节点 指向 当前节点
                first = last;
                // 头的上下节点指向 当前
                first.prev = first;
                first.next = first;
            }else{
                // 最后一个节点的 下一个 指向当前
                oldLast.next = last;
                // 头节点的上一个 指向 最后一个
                first.prev = last;
            }
        }else{
            // 正常添加
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<E>(prev, element, next);

            next.prev = node;
            prev.next = node;

            // index = 0
            if(next == first){
                first = node;
            }
        }

        size++;
    }

    public E remove(int index) {
        rangeCheck(index);

        return remove(node(index));
    }

    /**
     * 根据节点删除
     * @param node
     * @return
     */
    public E remove(Node<E> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;

            if (node == first) { // index == 0
                first = next;
            }

            if (node == last) { // index == size - 1
                last = prev;
            }
        }
        size--;
        return node.element;
    }

    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == element) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element.equals(element)) return i;
                node = node.next;
            }
        }
        return ELEMENTS_NOT_FOUNT;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("[size=").append(size).append(", ");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(node);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }


    /**
     * 节点
     * @param <E>
     */
    private class Node<E>{
        E element;
        /**
         * 上一个节点
         */
        Node<E> prev;
        /**
         * 下一个节点
         */
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            if(prev != null){
                sb.append(prev.element);
            }else{
                sb.append("null");
            }
            sb.append("_").append(element).append("_");
            if(next != null){
                sb.append(next.element);
            }else{
                sb.append("null");
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        // 创建一个双向链表
        IList<String> doubleLinkedList = new DoubleCircleLinkedList<String>();

        System.out.println("=======================================");

        doubleLinkedList.add("Java");
        doubleLinkedList.add("Python");
        doubleLinkedList.add(1, "C");
        doubleLinkedList.add(3, "Scalc");
        System.out.println(doubleLinkedList.toString());;
        System.out.println("=======================================");

        doubleLinkedList.remove(2);
        doubleLinkedList.remove(0);
        doubleLinkedList.remove(0);
        doubleLinkedList.remove(0);
        System.out.println(doubleLinkedList.toString());;
        System.out.println("=======================================");

        josephus();
    }

    /**
     * 双向循环链表解决约瑟夫环问题
     */
    public static void josephus(){
        DoubleCircleLinkedList<Integer> list = new DoubleCircleLinkedList<Integer>();
        for(int i = 1; i <= 8; i++){
            list.add(i);
        }
        list.reset(); // current->1
        while(!list.isEmpty()){
            list.next();
            list.next();
            System.out.println(list.remove());
        }
    }
}
