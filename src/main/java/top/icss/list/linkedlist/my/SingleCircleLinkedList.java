package top.icss.list.linkedlist.my;

import top.icss.list.IAbstractList;
import top.icss.list.IList;

/**
 * 单向循环链表 实现List
 * @author cd.wang
 * @create 2020-11-26 11:28
 * @since 1.0.0
 */
public class SingleCircleLinkedList<E> extends IAbstractList<E> {

    private Node<E> first;


    public void clear() {
        size = 0;
        first = null;
    }

    /**
     * 根据索引找到节点
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
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

        if(index == 0){
            Node<E> newNode = new Node<E>(element, first);
            // 拿到最后一个节点, 上面先不要直接改first, 否则下面找节点会出现问题
            Node<E> last = (size == 0) ? newNode : node(size - 1);
            last.next = newNode;
            first = newNode;
        }else{
            Node<E> prev = node(index - 1);
            prev.next = new Node<E>(element, prev.next);
        }
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);

        Node<E> node = first;
        if(index == 0){
            if(size == 1){
                first = null;
            }else{
                // 找到最后一个 节点
                Node<E> last = node(size - 1);
                // 因为要删除第一个  第一个节点的下一个 就等于第一个
                first = node.next;
                // 单向循环 最后一个的下一个 指针 指向 第一个
                last.next = first;
            }
        }else{
            // 当前要删除节点的上一个
            Node<E> prev = node(index - 1);
            // 要删除的节点
            node = prev.next;
            prev.next = node.next;
        }
        size--;

        return node.element;
    }

    public int indexOf(E element) {
        //有个注意点, 如果传入元素为null, 则不能调用equals方法, 否则会空指针
        if(element == null){
            Node<E> node = first;
            for(int i = 0; i < size; i++){
                if(node.element == null) return i;
                node = node.next;
            }
        }else{
            Node<E> node = first;
            for(int i = 0; i < size; i++){
                if(node.element.equals(element)) return i;
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
            string.append(node.element);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }

    private class Node<E>{
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(element).append("_").append(next.element);
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        // 创建一个单向循环链表
        IList<String> singleLinkedList = new SingleCircleLinkedList<String>();

        System.out.println("=======================================");

        singleLinkedList.add("Java");
        singleLinkedList.add("Python");
        singleLinkedList.add(1, "C");
        singleLinkedList.add(3, "Scalc");
        System.out.println(singleLinkedList.toString());;
        System.out.println("=======================================");

        singleLinkedList.remove(2);
        System.out.println(singleLinkedList.toString());;
        System.out.println("=======================================");
    }
}
