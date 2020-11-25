package top.icss.queue;

/**
 * 数组实现队列
 * 缺点：取出的空间将不会被再次使用，即此时的队列是一次性的。
 * @author cd.wang
 * @create 2020-11-18 10:04
 * @since 1.0.0
 */
public class ArrayQueue<E> {
    // 最大容量
    private int capacity;
    // 队首元素
    private int head;
    // 队尾元素
    private int last;
    // 存储队列
    private Object[] queue;

    public ArrayQueue(int capacity){
        this.capacity = capacity;
        this.queue = new Object[capacity];
        this.head = -1;
        this.last = -1;
    }

    /**
     * 添加队列
     * @param e
     */
    public void addQueue(E e){
        if(ifFull()){
            System.out.println("队列已满！");
            return;
        }
        // 添加数据 last 向后移动一位
        queue[++last] = e;
    }

    /**
     * 从队列中取出数据
     * @return
     */
    public E getQueue(){
        if(isEmpty()){
            System.out.println("队列为空");
            return null;
        }
        // 取出数据 头部元素向后移动 一位
        return (E) queue[++head];
    }

    /**
     * 输出当前队列所有元素
     */
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }
        System.out.print("当前队列存储元素总个数为：" + getSize() + "  当前队列为：");
        // 未取出是 head = -1 所以要加 1 使之等于 0
        for (int i = head + 1; i <= last; i++){
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }

    /**
     * 获取当前队列元素 size
     * @return
     */
    public int getSize(){
        return last - head;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return head == last;
    }

    /**
     * 判断队列是否已满
     * @return
     */
    public boolean ifFull(){
        return last == capacity - 1;
    }

    public static void main(String[] args) {
        //创建队列
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>(6);
        arrayQueue.addQueue(1);
        arrayQueue.addQueue(2);
        arrayQueue.addQueue(3);
        arrayQueue.addQueue(4);
        arrayQueue.showQueue();

        //取出元素
        System.out.println(arrayQueue.getQueue());
        System.out.println(arrayQueue.getQueue());

        arrayQueue.showQueue();
    }
}
