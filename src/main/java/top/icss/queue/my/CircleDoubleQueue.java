package top.icss.queue.my;

/**
 * 循环双端队列
 * @author: Mr.Wang
 * @create: 2020-11-28 下午 22:12
 * @description:
 **/
public class CircleDoubleQueue<E> {
    // 队头指针
    private int first;
    // 元素数量
    private int size;
    // 利用动态扩容数组实现的循环队列
    private E[] elements;
    // 初始容量
    public static final int DEFAULT_CAPACITY = 10;

    public CircleDoubleQueue(){
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
     * 队尾出队
     * @return
     */
    public E pollLast(){
        int index = index(size - 1);
        E oldElement = elements[index];
        elements[index] = null;
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
     * 队头入队
     * @param element
     */
    public void offerFirst(E element){
        expandCapacity(size + 1);
        // 头指针 获取
        first = index(-1);
        elements[first] = element;
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
     * 获取队尾元素
     * @return
     */
    public E peekLast(){

        return elements[index(size - 1)];
    }

    /**
     * 将first真实索引转换为循环队列上的索引
     *
     * 原理：已知 n >= 0，m > 0：
     * n % m 等价于 n – (m > n ? 0 : m) ；
     * 前提条件：n < 2m
     * @param index
     * @return
     */
    private int index(int index){
        index += first;
        if(index < 0){
            return index + elements.length;
        }
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
        CircleDoubleQueue<Integer> queue = new CircleDoubleQueue<Integer>();
        // 头5 4 3 2 1  100 101 102 103 104 105 106 8 7 6 尾

        // 头 8 7 6  5 4 3 2 1  100 101 102 103 104 105 106 107 108 109 null null 10 9 尾
        for (int i = 0; i < 10; i++) {
            queue.offerFirst(i + 1);
            queue.offerLast(i + 100);
        }

        System.out.println(queue);

        // 头 null 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null null 尾
        for (int i = 0; i < 3; i++) {
            queue.pollFirst();
            queue.pollLast();
        }
        System.out.println(queue);

        // 头 11 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null 12 尾
        queue.offerFirst(11);
        queue.offerFirst(12);

        System.out.println(queue);
    }
}
