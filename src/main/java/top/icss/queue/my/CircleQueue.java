package top.icss.queue.my;

import java.util.Objects;

/**
 * 循环队列
 * 动态数组实现循环队列
 * @author: Mr.Wang
 * @create: 2020-11-28 下午 22:12
 * @description:
 **/
public class CircleQueue<E> {
    // 队头指针
    private int first;
    // 元素数量
    private int size;
    // 利用动态扩容数组实现的循环队列
    private E[] elements;
    // 初始容量
    public static final int DEFAULT_CAPACITY = 10;

    public CircleQueue(){
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * 元素的数量
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            // elements[index(i)] = null;
            elements[index(i)] = null;
        }
        size = 0;
        first = 0;
    }

    /**
     * 队头出队
     * @return
     */
    public E pollFirst(){
        E oldElement = elements[first];
        elements[first] = null;
        // 头元素 下标指向
        first = index(1);
        size--;
        return oldElement;
    }

    /**
     * 队尾入队
     * @param element
     */
    public void offerLast(E element){
        expandCapacity(size + 1);

        elements[index(size)] = element;
        size++;
    }

    /**
     * 获取队头元素
     * @return
     */
    public E peekFirst(){
        return elements[first];
    }


    /**
     * 将first真实索引转换为循环队列上的索引
     *
     * 循环队列 – %运算符优化
     * 尽量避免使用 乘*、除/、模%、浮点数运算，效率低下；
     * @param index
     * @return
     */
    private int index(int index){
//        return (first + index) % elements.length; // 效率较低
        index += first;
        return index - ((index >= elements.length) ? elements.length : 0);
    }

    /**
     * 扩容
     * @param capacity
     */
    private void expandCapacity(int capacity){
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的 1.5 倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) { // 旧数组中元素移到新数组
            newElements[i] = elements[index(i)];
        }
        System.out.println("从" + oldCapacity + "扩容到" + newCapacity);
        elements = newElements;
        first = 0; // 重置front
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capacity=").append(elements.length).append(" size=").append(size).append(" front=")
                .append(first).append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

    public static void main(String[] args) {
        CircleQueue<Integer> queue = new CircleQueue<Integer>();
        // 0 1 2 3 4 5 6 7 8 9
        for (int i = 0; i < 10; i++) {
            queue.offerLast(i);
        }
        System.out.println(queue);
        // null null null null null 5 6 7 8 9
        for (int i = 0; i < 5; i++) {
            queue.pollFirst();
        }
        System.out.println(queue);
        // 15 16 17 18 19 f[5] 6 7 8 9
        for (int i = 15; i < 30; i++) {
            queue.offerLast(i);
        }

//		while (!queue.isEmpty()) {
//			System.out.println(queue.pollFirst());
//		}
//		queue.clear();
        System.out.println(queue);
    }
}
