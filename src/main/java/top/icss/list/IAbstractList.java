package top.icss.list;

/**
 * @author cd.wang
 * @create 2020-11-23 11:25
 * @since 1.0.0
 */
public abstract class IAbstractList<E> implements IList<E> {

    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return indexOf(element) > ELEMENTS_NOT_FOUNT;
    }

    public void add(E element) {
        /**
         * 添加到最后
         */
        add(size, element);
    }


    protected void outOfBoundsException(int index){
        throw new IndexOutOfBoundsException("数组越界-->Size: "+ size + ", Index: "+ index);
    }

    /**
     * 检查下标越界(不可访问或删除Size的位置)
     * @param index
     */
    protected void rangeCheck(int index){
        if(index < 0 || index >= size){
            outOfBoundsException(index);
        }
    }

    /**
     * 检查下标越界(可以在size位置添加)
     * @param index
     */
    protected void rangeCheckForAdd(int index){
        if(index < 0 || index > size){
            outOfBoundsException(index);
        }
    }

}
