package top.icss.list.linkedlist.my;

import top.icss.list.IAbstractList;
import top.icss.list.IList;

/**
 * 双向链表 实现 List
 * @author cd.wang
 * @create 2020-11-26 9:51
 * @since 1.0.0
 */
public class DoubleLinkedList<E> extends IAbstractList<E> {
    /**
     * 头结点
     */
    private Node<E> first;
    /**
     * 尾节点
     */
    private Node<E> last;

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

        // index = 0
        // size = 0
        if(index == size){
            // 往最后面添加元素
            Node<E> oldNode = last;
            last = new Node<E>(oldNode, element, null);
            // 添加的第一个元素
            if(oldNode == null){
                first = last;
            }else{
                oldNode.next = last;
            }
        }else{
            // 正常添加元素
            // 当前节点
            Node<E> next = node(index);
            // 上一个节点
            Node<E> prev = next.prev;

            Node<E> node = new Node<E>(prev, element, next);
            // 当前节点的上一个 等于 当前
            next.prev = node;
            // index == 0
            if(prev == null){
                first = node;
            }else{
                prev.next = node;
            }
        }
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);
        Node<E> oldNode = node(index);

        Node<E> prev = oldNode.prev;
        Node<E> next = oldNode.next;

        // index == 0
        if(prev == null){
            first = next;
        }else{
            prev.next = next;
        }

        // index == size - 1
        if(next == null){
            last = prev;
        }else{
            next.prev = prev;
        }
        size--;
        return oldNode.element;
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
        IList<String> doubleLinkedList = new DoubleLinkedList<String>();

        System.out.println("=======================================");

        doubleLinkedList.add("Java");
        doubleLinkedList.add("Python");
        doubleLinkedList.add(1, "C");
        doubleLinkedList.add(3, "Scalc");
        System.out.println(doubleLinkedList.toString());;
        System.out.println("=======================================");

        doubleLinkedList.remove(2);
        System.out.println(doubleLinkedList.toString());;
        System.out.println("=======================================");
    }
}
