package com.hq.learnning.leetcode;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 开密码锁问题
 *
 */
public class BSF4OpenLock {
	public static void main(String[] args) {
		String target = "0202";
		Set<String> deadends = new HashSet<>();
		deadends.add("0201");
		deadends.add("0101");
		deadends.add("0102");
		deadends.add("1212");
		deadends.add("2002");
		int minTreeDeep = openLock(deadends, target);
		System.out.println(String.format("min tree deep is ={%d}", minTreeDeep));
	}

	private static int openLock(Set<String> deads, String target) {
		int step = 0;

		String start = "0000";
		Queue<String> queue = new ConcurrentLinkedQueue<>();
		Set<String> visited = new HashSet<>();
		visited.addAll(deads);

		queue.offer(start);
		visited.add(start);
		//遍历队列
		while (!queue.isEmpty()){

			//遍历当前队列
			int size = queue.size();
			for (int i = 0; i < size; i++){
				String cur = queue.poll();

				//终止条件
				if (cur.equals(target)){
					return step;
				}
				//邻居入队列
				for (int index = 0; index < 4; index ++){
					String up = moveUp(cur, index);
					//已访问标志
					if (!visited.contains(up)){
						queue.offer(up);
						visited.add(up);
					}

					String down = moveDown(cur, index);
					if (!visited.contains(down)){
						queue.offer(down);
						visited.add(down);
					}
				}
			}
			step ++;
		}

		//无匹配
		return -1;
	}

	private static String moveUp(String cur, int index) {
		char[] array = cur.toCharArray();
		if (array[index] == '9'){
			array[index] = '0';
		}else {
			array[index] += 1;
		}

		return String.valueOf(array);
	}

	private static String moveDown(String cur, int index) {
		char[] array = cur.toCharArray();
		if (array[index] == '0'){
			array[index] = '9';
		}else {
			array[index] -= 1;
		}
		return String.valueOf(array);
	}


}
