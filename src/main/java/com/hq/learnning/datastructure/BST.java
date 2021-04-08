package com.hq.learnning.datastructure;

import com.hq.learnning.datastructure.entity.TreeNode;

/**
 * 二叉搜索树（BST）的结构是左子树的值小于根值，右子树的值大于根值。
 * （是整棵子树哦，不仅是子节点）
 */
public class BST {
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	TreeNode root;


	/**
	 * 删除操作
	 */
	TreeNode delete(TreeNode root, int key) {
		if (root == null) return null;
		if (root.getValue() == key) {
            // 这两个 if 把情况 1 和 2 都正确处理了
			if (root.getLeft() == null) return root.getRight();
			if (root.getRight() == null) return root.getLeft();
            // 处理情况 3：获取右边最小值或者左边最大值
			TreeNode minNode = getMin(root.getRight());
			root.setValue(minNode.getValue());
			//待优化
			root.setRight(delete(root.getRight(), minNode.getValue()));
		} else if (root.getValue() > key) {
			root.setLeft(delete(root.getLeft(), key));
		} else if (root.getValue() < key) {
			root.setRight(delete(root.getRight(), key));
		}
		return root;
	}

	/**
	 *
	 * @param node
	 * @return
	 */
	TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最⼩的
		while (node.getLeft() != null) node = node.getLeft();
		return node;
	}


	/**
	 * 插入非重复值
	 * @param root
	 * @param val
	 * @return
	 */
	public TreeNode insert(TreeNode root, int val){
		// 找到空位置插⼊新节点
		if (root == null){
			TreeNode node = new TreeNode();
			node.setValue(val);
			return node;
		}
        // if (root.val == val)
        // BST 中⼀般不会插⼊已存在元素
		if (root.getValue() < val)
			root.setRight(insert(root.getRight(), val));
		if (root.getValue() > val)
			root.setLeft(insert(root.getLeft(), val));
		return root;
	}


	/**
	 * 查找目标
	 * @param target
	 * @return
	 */
	public boolean hasTarget(int target){

        return doHashTarget(root, target);
	}

	/**
	 * void BST(TreeNode root, int target) {
	 * if (root.val == target)
	 * // 找到⽬标，做点什么
	 * if (root.val < target)
	 * BST(root.right, target);
	 * if (root.val > target)
	 * BST(root.left, target);
	 * }
	 * @param node
	 * @param target
	 * @return
	 */
	private boolean doHashTarget(TreeNode node, int target) {
		if (node == null){
			return false;
		}
		if (node.getValue() == target){
			return true;
		}
		if (root.getValue() < target){
			return doHashTarget(root.getRight(), target);
		}

		return doHashTarget(root.getLeft(), target);
	}


	/**
	 * 判断BST是否合法
	 * @param root
	 * @return
	 */
	public boolean isValid(TreeNode root){
		return isValid(root, null, null);
	}

	private boolean isValid(TreeNode root, TreeNode min, TreeNode max) {
		if (root == null){
			return true;
		}
		if (min != null && root.getValue() <= min.getValue()){
			return false;
		}
		if (max != null && root.getValue() >= max.getValue()){
			return false;
		}
		return isValid(root.getLeft(), min, root)
				&& isValid(root.getRight(), root, max);
	}

















}
