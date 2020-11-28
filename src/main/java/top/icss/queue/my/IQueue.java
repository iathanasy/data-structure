package top.icss.queue.my;

import top.icss.list.IList;
import top.icss.list.linkedlist.my.DoubleLinkedList;

/**
 * 队列
 * 优先使用双向链表，因为队列主要是往头尾操作元素；
 * 先进先出的原则，First In First Out，FIFO
 * @author: Mr.Wang
 * @create: 2020-11-28 下午 14:00
 * @description:
 **/
public class IQueue<E> {
    // 双向链表实现
    private IList<E> list = new DoubleLinkedList<>();

    /**
     * 元素的数量
     */
    public int size(){
        return list.size();
    }
    /**
     * 清空
     */
    public void clear(){
        list.clear();
    }
    /**
     * 是否为空
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 入队
     */
    public void offer(E element){
        list.add(element);
    }
    /**
     * 出队
     */
    public E poll(){
        return list.remove(0);
    }

    /**
     * 队头元素
     */
    public E peek(){
        return list.get(0);
    }

    public static void main(String[] args) {
        IQueue<Integer> queue = new IQueue<>();
        queue.offer(11);
        queue.offer(22);
        queue.offer(33);
        queue.offer(44);
        queue.offer(55);

        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}
