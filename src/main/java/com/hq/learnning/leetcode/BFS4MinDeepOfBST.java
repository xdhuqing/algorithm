package com.hq.learnning.leetcode;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 使用BSF查找二叉树的最短高度
 */
public class BFS4MinDeepOfBST {
	public static void main(String[] args) {
		int [] tree = new int[]{1,2,3,-1, -1,5,6};
		int minTreeDeep = minTreeDeep(tree);
		System.out.println(String.format("min tree deep is ={%d}", minTreeDeep));
	}

	private static int minTreeDeep(int[] tree) {

		int start = 0;
		Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
//		Set<Integer> visited = new HashSet<>();

		queue.offer(start);
//		visited.add(start);
		int deepth = 1;
		while (!queue.isEmpty()){

			int size = queue.size();
			for (int index = 0; index < size; index++){
				int cur = queue.poll();
				if (isChildrenBothNull(tree, cur)){
					return deepth;
				}
				List<Integer> neiberghs = getNeiberghsIndex(tree, cur);
				for (int neiber : neiberghs){
					queue.offer(neiber);
//					visited.add(neiber);
				}
			}
			deepth++;
		}


		return deepth;
	}

	private static List<Integer> getNeiberghsIndex(int[] tree, int cur) {
		List<Integer> nebersIndex = new ArrayList<Integer>();
		nebersIndex.add(cur*2 +1);
		nebersIndex.add(cur*2 +2);

		return nebersIndex;
	}

	public static boolean isChildrenBothNull(int[] tree, int i){
		if (2*i + 1 >= tree.length){
			return true;
		}
		if (2*i + 2 >= tree.length && tree[2*i + 1] == -1){
			return true;
		}
		if (2*i + 2 < tree.length && tree[2*i + 1] == -1 && tree[2*i + 2] == -1){
			return true;
		}
		return false;
	}

}
