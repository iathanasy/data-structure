package top.icss.list;

/**
 *
 * 动态数组：开辟、销毁内存空间的次数相对较少，但可能造成内存空间浪费（可以通过缩容解决）
 * 双向链表：开辟、销毁内存空间的次数相对较多，但不会造成内存空间的浪费
 *
 * 如果频繁在尾部进行添加、删除操作，动态数组、双向链表均可选择
 * 如果频繁在头部进行添加、删除操作，建议选择使用双向链表
 * 如果有频繁的 （在任意位置）添加、删除操作，建议选择使用双向链表
 * 如果有频繁的查询操作（随机访问操作），建议选择使用动态数组
 * @author cd.wang
 * @create 2020-11-23 11:24
 * @since 1.0.0
 */
public interface IList<E> {

    static final int ELEMENTS_NOT_FOUNT = -1;

    /**
     * 清除所有元素
     */
    void clear();

    /**
     * 元素的数量
     * @return
     */
    int size();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 是否包含某个元素
     * @param element
     * @return
     */
    boolean contains(E element);

    /**
     * 添加元素到尾部
     * @param element
     */
    void add(E element);

    /**
     * 获取index位置的元素
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 设置index位置的元素
     * @param index
     * @param element
     * @return 原来的元素ֵ
     */
    E set(int index, E element);

    /**
     * 在index位置插入一个元素
     * @param index
     * @param element
     */
    void add(int index, E element);

    /**
     * 删除index位置的元素
     * @param index
     * @return
     */
    E remove(int index);

    /**
     * 查看元素的索引
     * @param element
     * @return
     */
    int indexOf(E element);
}
