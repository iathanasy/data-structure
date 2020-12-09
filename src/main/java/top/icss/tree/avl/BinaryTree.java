package top.icss.tree.avl;

import top.icss.tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树
 * @author: Mr.Wang
 * @create: 2020-12-04 下午 16:38
 * @description:
 **/
public class BinaryTree<E> implements BinaryTreeInfo {
	// 元素数量
	protected int size;
	// 根节点
	protected Node<E> root;

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
	 * 检测传入的节点是否为空
	 */
	protected void checkElementNotNull(E element){
		if(element == null){
			// 不能传入空节点
			throw new IllegalArgumentException("element must not null");
		}
	}

	/**
	 * 创建节点
	 * @param element
	 * @param parent
	 * @return
	 */
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
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
	protected Node<E> predecessor(Node<E> node){
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
	protected Node<E> successor(Node<E> node){
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


	/**
	 * 是否完全二叉树
	 * @return
	 */
	public boolean isComplete() {
		if (root == null) return false;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);

		boolean leaf = false;
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (leaf && !node.isLeaf()) return false;

			if (node.left != null) {
				queue.offer(node.left);
			} else if (node.right != null) {
				return false;
			}

			if (node.right != null) {
				queue.offer(node.right);
			} else { // 后面遍历的节点都必须是叶子节点
				leaf = true;
			}
		}

		return true;
	}

	public int height() {
		if (root == null) return 0;

		// 树的高度
		int height = 0;
		// 存储着每一层的元素数量
		int levelSize = 1;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--;

			if (node.left != null) {
				queue.offer(node.left);
			}

			if (node.right != null) {
				queue.offer(node.right);
			}

			if (levelSize == 0) { // 意味着即将要访问下一层
				levelSize = queue.size();
				height++;
			}
		}

		return height;
	}

	public int height2() {
		return height(root);
	}

	private int height(Node<E> node) {
		if (node == null) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
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
	 * 访问器接口 ——> 访问器抽象类
	 * @param <E>
	 */
	public static abstract class Visitor<E> {
		boolean stop;
		/**
		 * @return 如果返回true，就代表停止遍历
		 */
		public abstract boolean visit(E element);
	}

	/**
	 * 节点
	 * @param <E>
	 */
	protected class Node<E>{
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

		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}

		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}

		public Node<E> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}

			if (isRightChild()) {
				return parent.left;
			}

			return null;
		}



	}

}
