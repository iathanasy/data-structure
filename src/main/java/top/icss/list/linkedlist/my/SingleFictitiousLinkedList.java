package top.icss.list.linkedlist.my;

import top.icss.list.IAbstractList;
import top.icss.list.IList;

/**
 * 虚拟头结点实现 List
 * @author cd.wang
 * @create 2020-11-25 9:26
 * @since 1.0.0
 */
public class SingleFictitiousLinkedList<E> extends IAbstractList<E> {

    /**
     * 第一个节点
     */
    private Node<E> first;

    /**
     * 初始化一个虚拟头结点
     */
    public SingleFictitiousLinkedList(){
        first = new Node<E>(null, null);
    }

    /**
     * 根据索引找到节点对象
     * @param index
     * @return
     */
    private Node<E> node(int index){
        rangeCheck(index);

        Node<E> node = first.next;
        for(int i = 0; i < index; i++){
            node = node.next;
        }
        return node;
    }

    public void clear() {
        first = null;
        size = 0;
    }


    public E get(int index) {
        return node(index).element;
    }

    public E set(int index, E element) {
        E old = node(index).element;
        node(index).element = element;
        return (E) old;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);

//        if(index == 0){
//            first = new Node<E>(element, first);
//        }else {
//            // 上一个节点
//            Node<E> prev = index == 0 ? first : node(index - 1);
//            prev.next = new Node<E>(element, prev.next);
//        }

        // 虚拟头结点 实现
        Node<E> prev = index == 0 ? first : node(index - 1);
        prev.next = new Node<E>(element, prev.next);

        size++;

    }

    public E remove(int index) {
        rangeCheck(index);
//        Node<E> node = first;
//        //第一个节点
//        if(index == 0){
//            // 删除第一个元素是特殊情况
//            node = first.next;
//        }else {
//            //上一个节点
//            Node<E> prev = node(index - 1);
//            // 待删除节点
//            node = prev.next;
//            //把节点的指针指向删除的下一个节点
//            prev.next = node.next;
//        }

        //上一个节点
        Node<E> prev = index == 0 ? first : node(index - 1);
        // 待删除节点
        Node<E> node = prev.next;
        //把节点的指针指向删除的下一个节点
        prev.next = node.next;

        size--;
        return node.element;
    }

    public int indexOf(E element) {
        //有个注意点, 如果传入元素为null, 则不能调用equals方法, 否则会空指针
        if(element == null){
            // todo 改动点
            Node<E> node = first.next;
            for(int i = 0; i < size; i++){
                if(node.element == null) return i;
                node = node.next;
            }
        }else{
            // todo 改动点
            Node<E> node = first.next;
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
        // todo 改动点
        Node<E> node = first.next;
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

    /**
     * 节点
     * @param <E>
     */
    private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        // 创建一个虚拟单链表
        IList<String> singleLinkedList = new SingleFictitiousLinkedList();

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
