package top.icss.leetcode.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226.翻转二叉树
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * @author: Mr.Wang
 * @create: 2020-12-02 下午 20:51
 * @description:
 **/
public class _226_翻转二叉树 {

    /**
     * 层序遍历翻转
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            // 节点交换
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if(node.left != null){
                queue.offer(node.left);
            }

            if(node.right != null){
                queue.offer(node.right);
            }
        }

        return root;
    }

    /**
     * 前序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return root;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree1(root.left);
        invertTree1(root.right);

        return root;
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;

        invertTree2(root.left);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 上面交接了 所以直接传 left
        invertTree2(root.left);
        return root;
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return root;

        invertTree3(root.left);
        invertTree3(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }
}
