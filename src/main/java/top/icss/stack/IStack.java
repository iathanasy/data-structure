package top.icss.stack;

import top.icss.list.IList;
import top.icss.list.array.IArrayList;

/**
 * 栈是一种特殊的线性表，只能在一端进行操作
 * 后进先出的原则，Last In First Out，LIFO
 * 栈 可以基于动态数组实现
 * @author cd.wang
 * @create 2020-11-27 9:30
 * @since 1.0.0
 */
public class IStack<E> {

    private IList<E> list = new IArrayList();

    /**
     * 入栈
     * @param element
     * @return
     */
    public E push(E element){
        list.add(element);
        return element;
    }

    /**
     * 出栈
     * @return
     */
    public E pop(){
        return list.remove(size() - 1);
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public E peek(){
        return list.get(size() - 1);
    }

    /**
     * 清除所有元素
     */
    void clear(){
        list.clear();
    }
    /**
     * 元素的数量
     * @return
     */
    public int size(){
        return list.size();
    }
    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    public static void main(String[] args) {
        IStack<Integer> stack = new IStack<Integer>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
