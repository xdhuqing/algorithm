package com.hq.learnning.leetcode;

import com.hq.learnning.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 使用BSF查找二叉树的最短高度
 */
public class BFS4MinDeepOfBSTwithNode {
	public static void main(String[] args) {
		TreeNode<Integer> root = new TreeNode<>();
		root.setValue(1);

		TreeNode<Integer> left = new TreeNode<>();
		TreeNode<Integer> right = new TreeNode<>();
		root.setLeft(left);
		root.setRight(right);

		left.setValue(2);
		right.setValue(3);

		TreeNode<Integer> rightChild1 = new TreeNode<>();
		TreeNode<Integer> rightChild2 = new TreeNode<>();
		right.setLeft(rightChild1);
		right.setRight(rightChild2);

		rightChild1.setValue(4);
		rightChild2.setValue(5);

		int minTreeDeep = minTreeDeep(root);
		System.out.println(String.format("min tree deep is ={%d}", minTreeDeep));
	}

	private static int minTreeDeep(TreeNode<Integer> root) {

		Queue<TreeNode<Integer>> queue = new ConcurrentLinkedQueue<>();

		//初始化
		queue.offer(root);
		int deepth = 1;
		//开始遍历
		while (!queue.isEmpty()){

			//遍历当前步长的点
			int size = queue.size();
			for (int index = 0; index < size; index++){
				TreeNode<Integer> cur = queue.poll();
				//判断终止条件
				if (isChildrenBothNull(cur)){
					return deepth;
				}
				//获取邻居点，放入队列（二叉树没有回路，不需要visited标识）
				List<TreeNode<Integer>> neighbors = getNeighbors(cur);
				for (TreeNode<Integer> neighbor : neighbors){
					queue.offer(neighbor);
				}
			}
			deepth++;
		}

		return deepth;
	}

	private static List<TreeNode<Integer>> getNeighbors(TreeNode<Integer> node) {
		List<TreeNode<Integer>> neighbors = new ArrayList<>();
		if (node.getLeft() != null){
			neighbors.add(node.getLeft());
		}
		if (node.getRight() != null){
			neighbors.add(node.getRight());
		}

		return neighbors;
	}

	public static boolean isChildrenBothNull(TreeNode<Integer> node){
		if (node.getLeft() == null && node.getRight() == null){
			return true;
		}

		return false;
	}

}
