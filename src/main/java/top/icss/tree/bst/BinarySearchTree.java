package top.icss.tree.bst;

import top.icss.tree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
        root = null;
        size = 0;
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

    /**
     * 检测传入的节点是否为空
     */
    private void checkElementNotNull(E element){
        if(element == null){
            // 不能传入空节点
            throw new IllegalArgumentException("element must not null");
        }
    }

    /**
     * 前序遍历(递归)
     * 树状结构展示（注意左右子树的顺序）
     * @param visitor
     */
    public void preorder(Visitor<E> visitor) {
        if (visitor == null) return;
        preorder(root, visitor);
    }
    private void preorder(Node<E> node, Visitor<E> visitor){
        if(node == null || visitor.stop ) return;
        // root -> left -> right
        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /**
     * 前序遍历（迭代）
     * @param visitor
     */
    public void preorderit(Visitor<E> visitor){
        if (root == null || visitor == null) return;

        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);

        while(!stack.empty()){
            Node<E> node = stack.pop();

            if (visitor.stop) return;
            visitor.stop = visitor.visit(node.element);

            if(node.right != null){
                stack.push(node.right);
            }

            if(node.left != null){
                stack.push(node.left);
            }
        }


    }


    /**
     * 中序遍历(递归)
     * 二叉搜索树的中序遍历按升序或者降序处理节点
     * @param visitor
     */
    public void inorder(Visitor<E> visitor) {
        if (visitor == null) return;
        inorder(root, visitor);
    }
    private void inorder(Node<E> node, Visitor<E> visitor){
        if(node == null || visitor.stop ) return;

        inorder(node.left, visitor);
        // left -> root -> right
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    /**
     * 中序遍历（迭代）
     * @param visitor
     */
    public void inorderit(Visitor<E> visitor){
        if (root == null || visitor == null) return;

        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        // TODO 有BUG 需要在调整
        while(!stack.empty()){
            Node<E> node = stack.pop();
            System.out.println(node.element);
            if(node.left != null){
                stack.push(node.left);
            }else{
                if (visitor.stop) return;
                visitor.stop = visitor.visit(node.element);

                stack.push(node.right);
            }
        }

    }


    /**
     * 后序遍历(递归)
     * 适用于一些先子后父的操作
     * @param visitor
     */
    public void postorder(Visitor<E> visitor) {
        if (visitor == null) return;
        postorder(root, visitor);
    }
    private void postorder(Node<E> node, Visitor<E> visitor){
        if(node == null || visitor.stop ) return;

        postorder(node.left, visitor);
        postorder(node.right, visitor);
        // left -> right -> root
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层序遍历(迭代)
     * 从上到下、从左到右依次访问每一个节点
     * @param visitor
     */
    public void levelorder(Visitor<E> visitor){
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            Node<E> node = queue.poll();
            // 如果找到 这个节点就 停止遍历
            if(visitor.visit(node.element)) return;

            if(node.left != null){
                queue.offer(node.left);
            }

            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }


    /**
     * 前驱节点: 中序遍历时的前一个节点
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node){
        if(node == null) return node;

        // 前驱节点在左子树中(left.right.right.right....)
        Node<E> p = node.left;
        if(p != null){
            // 左子树不为空,则找到它的最右节点
            while (p.right != null){
                p = p.right;
            }
            return p;
        }

        // 能来到这里说明左子树为空, 则从父节点、祖父节点中寻找前驱节点
        // 当父节点不为空, 且某节点为父节点的左子节点
        // 则顺着父节点找, 直到找到【某结点为父节点或祖父节点的右子树中】时
        while(node.parent != null && node.parent.left == node){
            node = node.parent;
        }

        // 来到这里有以下两种情况:
        // node.parent == null 无前驱, 说明是根结点
        // node == node.parent.right 找到【某结点为父节点或祖父节点的右子树中】
        // 那么父节点就是某节点的前驱节点
        return node.parent;

    }

    /**
     * 后继节点: 中序遍历时的后一个节点
     * @return
     */
    private Node<E> successor(Node<E> node){
        if (node == null) return node;

        // 后继节点在右子树中（node.right.left.left...）
        if (node.right != null) {
            Node<E> p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 来到这里说明没有右节点, 则从父节点、祖父节点中寻找后继节点
        // 当父节点不为空, 且某节点为父节点的右子节点
        // 则顺着父节点找, 直到找到【某结点在父节点或祖父节点的左子树中】时
        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }

        // 来到这里有以下两种情况:
        // node.parent == null 无前驱，说明是根结点
        // node.parent.left == node 找到【某结点在父节点或祖父节点的左子树中】
        // 那么父节点就是某节点的后继节点
        return node.parent;
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

        /**
         * 是否叶子节点
         * @return
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 是否有两个子节点
         * @return
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

    }

    public static abstract class Visitor<E> {
        boolean stop;
        /**
         * @return 如果返回true，就代表停止遍历
         */
        public abstract boolean visit(E element);
    }

}
