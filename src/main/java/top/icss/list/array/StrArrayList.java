package top.icss.list.array;

/**
 * 字符类型动态数组
 * @author cd.wang
 * @create 2020-11-20 10:28
 * @since 1.0.0
 */
public class StrArrayList {
    // 元素数量
    private int size;
    private String[] elements;

    // 初始容量
    private static final int DEFAULT_CAPACITY = 10;
    // 元素未找到
    private static final int ELEMENTS_NOT_FOUND = -1;

    public StrArrayList(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("Illegal Capacity: "+  capacity);
        }
        // 容量小于10 一律扩充为10
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = new String[capacity];
    }

    public StrArrayList(){
        this(DEFAULT_CAPACITY);
    }


    /**
     * 是否包含某个元素
     * @param element
     * @return
     */
    public boolean contains(String element){
        // 找到元素返回true
        return indexOf(element) != ELEMENTS_NOT_FOUND;
    }

    /**
     * 在index位置添加一个元素
     * @param index
     * @param element
     */
    public void add(int index, String element){
        rangCheckForAdd(index);

        // 确保容量足够
        ensureCapaCity(size + 1);
        // 0 1 2 3 4 5 6 7 8 9	(index)
        // 1 2 3 4 5 6 x x x x	(原数组)
        // 在index=2处，插入9，元素全部后移
        // 1 2 9 3 4 5 6 x x x	(add后数组)
        // 先从后往前开始, 将每个元素往后移一位, 然后再赋值
        for(int i = size - 1; i >= index; i--){
            System.out.println(elements[i+1]+ " : "+ elements[i]);
            elements[i+1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 添加元素 到最后
     * @param element
     */
    public void add(String element){
        add(size, element);
    }

    /**
     * 获取index的位置
     * @param index
     * @return
     */
    public String get(int index){
        rangCheck(index);
        return elements[index];
    }

    /**
     * 设置index位置元素
     * @param index
     * @param element
     * @return 原来的元素
     */
    public String set(int index, String element){
        rangCheck(index);
        String oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    /**
     * 删除 index 位置的元素
     * @param index
     * @return 删除的元素
     */
    public String remove(int index){
        rangCheck(index);

        String oldElement = elements[index];
        // 0 1 2 3 4 5 	(index)
        // 1 2 3 4 5 6 	(原数组)
        // 删除index为2的元素，元素前移
        // 1 2 4 5 6	(remove后的数组)
        // 从前往后开始移动， 用后面的元素覆盖前面的元素
        for (int i = index; i < size-1; i++){
            elements[i] = elements[i+1];
        }

        elements[size--] = null;
        return oldElement;
    }

    /**
     * 查看元素索引
     * @param element
     * @return
     */
    public int indexOf(String element){
        if(element != null){
            for(int i = 0; i < size; i++){
                if(elements[i] == element){
                    return i;
                }
            }
        }else{
            for(int i = 0; i < size; i++){
                //对 null进行处理
                if(elements[i] == null){
                    return i;
                }
            }
        }
        return ELEMENTS_NOT_FOUND;
    }

    /**
     * 清除所有元素
     */
    public void clear(){
        // 将元素置位null
        for(int i = 0; i < size; i++){
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 数组扩容操作
     * @param capacity
     */
    private void ensureCapaCity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity >= capacity) return;
        // 新容量为 旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1); // 右移 除以2的1次方 1.5
        String[] newElements = new String[newCapacity];
        for(int i = 0; i < size; i++){
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println("扩容操作-->Size: " + oldCapacity + ", 扩容到："+ newCapacity);
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 元素数量
     * @return
     */
    public int getSize(){
        return size;
    }


    //////////////////////////////封装的一些函数/////////////////////////////////////

    /**
     * 检查下标越界(不可访问或删除Size的位置)
     * @param index
     */
    private void rangCheck(int index){
        if(index < 0 || index >= size){
            outOfBounds(index);
        }
    }

    /**
     * 检查下标越界(可以在size位置添加)
     * @param index
     */
    private void rangCheckForAdd(int index){
        if(index < 0 || index > size){
            outOfBounds(index);
        }
    }

    /**
     * 下标越界抛出异常
     * @param index
     */
    private void outOfBounds(int index){
        throw new IndexOutOfBoundsException("下标越界-->Index: "+ index + ", Size: "+ size);
    }


    /****************封装好的功能函数***************************/
    @Override
    public String toString() {
        // 打印形式为: size=5, [99, 88, 77, 66, 55]
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if(0 != i) string.append(", ");
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }


    public static void main(String[] args) {
        StrArrayList list = new StrArrayList();
        list.add("Java");
        list.add("Python");
        list.add("C");
        list.add("Scala");

        list.add(0,"C++");
        list.add(0,"JavaScript");

        list.set(1, "Lua");

        String str = list.remove(0);
        System.out.println("删除的元素： "+ str);

        System.out.println(list.toString());
    }
}
