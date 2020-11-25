package top.icss.list.array;

import top.icss.list.IAbstractList;

import java.util.Arrays;

/**
 * 泛型动态数组
 * 缺点：可能会造成内存空间的大量浪费。
 *
 * @author cd.wang
 * @create 2020-11-20 12:52
 * @since 1.0.0
 */
public class IArrayList<E> extends IAbstractList<E> {
    private Object[] elements;
    /**
     * 初始数组大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    public IArrayList(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("非法容量(Illegal Capacity): "+ capacity);
        }
        this.elements = new Object[capacity];
    }

    public IArrayList(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * 下标位置 添加元素
     * @param index
     * @param element
     */
    public void add(int index, E element){
        rangeCheckForAdd(index);

        expandCapacity(index + 1);
        // 下标 0 1 2 3 4 5 6 7 8 9
        // 数据 1 2 3 4 5 6 7
        // index 为 2 element 为 9 移动后
        // 下标 0 1 2 3 4 5 6 7 8 9
        // 数据 1 2 9 3 4 5 6 7
        // 数据从后往前开始移动， 最后一位开始移动
        for(int i = size - 1; i >= index; i--){
//            System.out.println(elements[i+1] +"("+(i+1)+")" +" :"+ elements[i] +"("+i+")");
            elements[i+1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 添加元素到尾部
     * @param element
     */
    public void add(E element){
        add(size, element);
    }

    /**
     * 下标获取元素
     * @param index
     * @return
     */
    public E get(int index){
        rangeCheck(index);
        E element = (E) elements[index];
        return element;
    }

    /**
     * 根据下标 设置
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element){
        rangeCheck(index);

        E oldElement = (E) elements[index];
        elements[index] = element;
        return oldElement;
    }

    /**
     * 根据元素内容 获取下标位置
     * @param element
     * @return
     */
    public int indexOf(E element){
        if(element == null){
            for(int i = 0; i < size; i++){
                if(elements[i] == null) return i;
            }
        }else{
            for(int i = 0; i < size; i++){
                if(elements[i].equals(element)) return i;
            }
        }
        return ELEMENTS_NOT_FOUNT;
    }

    /**
     * 按下标删除元素
     * @param index
     * @return
     */
    public E remove(int index){
        rangeCheck(index);

        E oldElement = (E) elements[index];
        // 下标 0 1 2 3 4 5 6 7 8 9
        // 数据 1 2 3 4 5 6 7
        // index 为 0
        // 下标 0 1 2 3 4 5 6 7 8 9
        // 数据 2 9 3 4 5 6 7
        // 数据从前往后移动一位  下标位置开始移动
        // (size-1)：元素少了一位
        for(int i = index; i < size - 1; i++){
            elements[i] = elements[i+1];
        }
        //内存回收
        elements[size--] = null;
        return oldElement;
    }

    /**
     * 清除元素
     */
    public void clear(){
        for(int i = 0; i < size; i++){
            elements[i] = null;
        }
        size = 0;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * 动态扩容
     * @param capacity
     */
    public void expandCapacity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity >= capacity) return;
        // 新的容量 是原容量的 1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] newElelemts = new Object[newCapacity];
        for(int i = 0; i < oldCapacity; i++){
            newElelemts[i] = elements[i];
        }
        // 复制
        elements = newElelemts;
        System.out.println("扩容操作-->Size: " + oldCapacity + ", 扩容到："+ newCapacity);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IArrayList{");
        sb.append("size=" + size +",[");
        for (int i = 0; i < size; i++){
            if(i != 0) sb.append(",");
            sb.append(elements[i]);
        }
        sb.append("]}");

        return sb.toString();
    }


    public static void main(String[] args) {
        IArrayList<Integer> list = new IArrayList<Integer>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        System.out.println(list.toString());

        list.add(2,9);

        System.out.println(list.toString());

        list.remove(0);

        System.out.println(list.toString());

        System.out.println("----------------------------------------");

        // 练习 插入到数组
        int[] arr = new int[10];
        int size = 0;
        for(int i = 0; i < 7; i++){
            arr[i] = i+1;
            size++;
        }

        size = size - 1;
        int index = 2;
        int element = 9;
        // 数据从后往前开始移动， 最后一位开始移动
        for(int i = size; i >= index; i--){
            arr[i+1] = arr[i];
        }
        arr[index] = element;
        size++;
        System.out.println(Arrays.toString(arr));

        // 练习 下标删除  数据从前往后移动一位  下标位置开始移动
        for(int i = index; i < size-1; i++){
            arr[i] = arr[i+1];
        }
        size--;
        System.out.println(Arrays.toString(arr));
    }
}
