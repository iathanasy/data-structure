package top.icss.tree.bst2;

import java.util.Comparator;

/**
 * 二叉搜索树
 *
 * @author: Mr.Wang
 * @create: 2020-11-30 下午 21:26
 * @description:
 **/
public class BinarySearchTree<E> extends BinaryTree<E>{

    // 比较器，根据传入的比较器实现 compareTo() 方法
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }


    /**
     * 添加元素
     * @param element
     */
    public void add (E element){
        checkElementNotNull(element);

        // 传入第一个节点, 若根节点为null, 则该节点为根节点
        if(root == null){
            root = new Node<E>(element, null);
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
        Node<E> newNode = new Node<E>(element, parent);
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
        remove(node(element));
    }

    /**
     * 是否包含某元素
     * @param element
     * @return
     */
    public boolean contains (E element){
        return node(element) != null;
    }

    /**
     * 根据节点删除元素
     * @param node
     */
    private void remove(Node<E> node){
        if(node == null) return;

        size--;

        // 是否有两个子节点 度为 2
        if(node.hasTwoChildren()){
            // 找到后继节点
            Node<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;
        // node是度为1的节点
        if(replacement != null){
            // 更改parent 节点
            replacement.parent = node.parent;
            // 更改 parent的 left、right的指向
            if(node.parent == null){
                // node是度为1的节点并且是根节点
                root =replacement;
            }else if(node == node.parent.left){
                node.parent.left = replacement;
            }else{
                //node == node.parent.right;
                node.parent.right = replacement;
            }
        }else if(node.parent == null){
            // node是叶子节点并且是根节点
            root = null;
        }else{
            // node是叶子节点 但不是根节点
            if(node == node.parent.left){
                node.parent.left = null;
            }else{
                // node == node.parent.right
                node.parent.right = null;
            }
        }
    }

    /**
     * 根据元素值获取元素
     * @param element
     */
    private Node<E> node(E element){
        checkElementNotNull(element);

        Node<E> node = root;
        while(node != null){
            int cmp = compareTo(element, node.element);

            if(cmp < 0){
                node = node.left;
            }else if(cmp > 0){
                node = node.right;
            }else{
                //相等
                return node;
            }
        }
        return null;
    }

    /**
     * 节点元素比较
     * @param e1
     * @param e2
     * @return
     * 传入节点比父节点要小, 往左
     * 传入节点比父节点要大, 往右
     * 相等 == 0
     */
    private int compareTo(E e1, E e2) {
        if (comparator != null) { // 传入比较器则通过比较器比较
            return comparator.compare(e1, e2);
        }
        // 没传比较器，元素内部必须自行实现了 Comparable 接口
        return ((Comparable<E>)e1).compareTo(e2);
    }

    public static abstract class Visitor<E> {
        boolean stop;
        /**
         * @return 如果返回true，就代表停止遍历
         */
        public abstract boolean visit(E element);
    }

}
