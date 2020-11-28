package top.icss.queue.my;

import top.icss.list.IList;
import top.icss.list.linkedlist.my.DoubleLinkedList;

/**
 * 双端队列
 *  能在头尾两端添加、删除的队列
 * @author: Mr.Wang
 * @create: 2020-11-28 下午 21:54
 * @description:
 **/
public class IDeque<E> {
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
     * 从队头入队
     * @param element
     */
    public void offerFirst(E element){
        list.add(0, element);
    }

    /**
     * 从队尾入队
     */
    public void offerLast(E element){
        list.add(element);
    }

    /**
     * 从队头出队
     * @return
     */
    public E pollFirst(){
        return list.remove(0);
    }

    /**
     * 从队尾出队
     * @return
     */
    public E pollLast(){
        return list.remove(size() - 1);
    }

    /**
     * 获取队头元素
     * @return
     */
    public E peekFirst(){
        return list.get(0);
    }

    /**
     * 获取队尾元素
     * @return
     */
    public E peekLast(){
        return list.get(size() - 1);
    }

    public static void main(String[] args) {
        IDeque<Integer> queue = new IDeque<>();
        // 头 55 22 11 33 44 尾
        queue.offerFirst(11);
        queue.offerFirst(22);
        queue.offerLast(33);
        queue.offerLast(44);
        queue.offerFirst(55);

        while(!queue.isEmpty()){
            System.out.println(queue.pollFirst());
        }
    }
}
