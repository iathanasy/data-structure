package top.icss.tree.bst;

import top.icss.tree.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 二叉搜索树
 *
 * @author: Mr.Wang
 * @create: 2020-11-30 下午 21:26
 * @description:
 **/
public class BinarySearchTree<E> implements BinaryTreeInfo {
    // 元素数量
    private int size;
    // 根节点
    private Node<E> root;

    // 比较器，根据传入的比较器实现 compareTo() 方法
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 元素的数量
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 清空所有元素
     */
    public void clear(){

    }

    /**
     * 添加元素
     * @param element
     */
    public void add (E element){
        checkElementNotNull(element);

        // 传入第一个节点, 若根节点为null, 则该节点为根节点
        if(root == null){
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 添加的不是第一个节点, 找到父节点
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        while (node != null) {
            // 父节点
            parent = node;
            // 比较【传入节点的元素值】与【父节点的元素值】
            cmp = compareTo(element, node.element);

            if(cmp < 0){
                // 传入节点比父节点要小, 往左
                node = node.left;
            }else if(cmp > 0){
                // 传入节点比父节点要大, 往右
                node = node.right;
            }else{
                // 相等, 最好是覆盖掉, 也可以采取其他操作, 看业务需求
                node.element = element;
                return;
            }
        }

        // 上面只是找到了要添加位置的父节点, 下面要将元素添加进去
        Node<E> newNode = new Node<>(element, parent);
        if(cmp < 0){
            parent.left = newNode;
        }else{
            parent.right = newNode;
        }
        size++;
    }

    /**
     * 删除元素
     * @param element
     */
    public void remove (E element){

    }

    /**
     * 是否包含某元素
     * @param element
     * @return
     */
    public boolean contains (E element){
        return false;
    }

    // 节点元素比较
    private int compareTo(E e1, E e2) {
        if (comparator != null) { // 传入比较器则通过比较器比较
            return comparator.compare(e1, e2);
        }
        // 没传比较器，元素内部必须自行实现了 Comparable 接口
        return ((Comparable<E>)e1).compareTo(e2);
    }

    /**
     * 检测传入的节点是否为空
     */
    private void checkElementNotNull(E element){
        if(element == null){
            // 不能传入空节点
            throw new IllegalArgumentException("element must not null");
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>)node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }

    /**
     * 节点
     * @param <E>
     */
    private class Node<E>{
        // 元素
        E element;
        // 左节点
        Node<E> left;
        // 右节点
        Node<E> right;
        // 父节点
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

}
