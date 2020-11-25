package top.icss.queue;

/**
 * 使用数组实现环形队列
 * 在 ArrayQueue 基础中稍作修改 ，当成环处理（数据首尾相连，可以通过 % 进行取模运算实现）
 * 一般采用 牺牲一个 数组空间 作为判断当前队列是否已满的条件。
 * @author cd.wang
 * @create 2020-11-18 10:43
 * @since 1.0.0
 */
public class ArrayCircleQueue<E> {
    // 最大容量
    private int capacity;
    // 队首元素
    private int head;
    // 队尾元素
    private int last;
    // 存储队列
    private Object[] queue;

    public ArrayCircleQueue(int capacity){
        this.capacity = capacity;
        this.queue = new Object[capacity];
        this.head = 0;
        this.last = 0;
    }

    /**
     * 添加数据进入队列
     * @param e
     */
    public void addQueue(E e){
        if(isFull()){
            System.out.println("队列已满!");
            return;
        }
        // 添加数据 last 向后移动一位
        queue[last] = e;
        last = (last + 1) % capacity;
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
        // 取出数据 head头部元素向后移动 一位
        E result = (E) queue[head];
        head = (head + 1) % capacity;
        return result;
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
        //
        for (int i = head; i < head + getSize(); i++){
            if(i != capacity){
                System.out.print(queue[i] + "[" + i + "]" + " ");
            }

        }
        System.out.println();
    }

    /**
     * 获取当前队列实际大小
     * @return 队列实际存储数据数量
     */
    public int getSize() {
        return (last - head + capacity) % capacity;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    private boolean isEmpty() {
        return head == last;
    }

    /**
     * 判断队列是否已满
     * @return
     */
    public boolean isFull(){
        return (last + 1) % capacity == head;
    }

    public static void main(String[] args) {
        //创建队列
        ArrayCircleQueue<Integer> arrayQueue = new ArrayCircleQueue<Integer>(4);
        arrayQueue.addQueue(1);
        arrayQueue.addQueue(2);
        arrayQueue.addQueue(3);

        arrayQueue.showQueue();

        //取出元素
        System.out.println(arrayQueue.getQueue());
        System.out.println(arrayQueue.getQueue());

        // TODO 有bug 待查
        arrayQueue.addQueue(4);
        arrayQueue.addQueue(5);

        arrayQueue.showQueue();
    }
}
