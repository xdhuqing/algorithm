package com.hq.learnning.datastructure.entity;

import java.util.ArrayDeque;
import java.util.Deque;

public class MonotoneQueue {

	private Deque<Integer> deque = new ArrayDeque<>();

	// 在队尾添加元素 n
	public void push(int n){
		while (!deque.isEmpty() && deque.getLast() < n){
			deque.removeLast();
		}
		deque.offerLast(n);
	}

	// 返回当前队列中的最⼤值
	public int max(){
		return deque.getFirst();
	}

	// 队头元素如果是 n，删除它
	public void pop(int n){
		if (!deque.isEmpty() && deque.getFirst() == n){
			deque.removeFirst();
		}
	}
}
