package com.hq.learnning.datastructure;

import org.springframework.util.Assert;

import java.io.UncheckedIOException;
import java.util.PriorityQueue;

/**
 * 优先级队列
 */
public class MyPriorityQueue<Key extends Comparable<Key>> {

	public static void main(String[] args) {

		MyPriorityQueue<Integer> mPq = new MyPriorityQueue<>(10);
		mPq.insert(1);
		mPq.printOutAll();
		mPq.insert(9);
		mPq.printOutAll();
		mPq.insert(5);
		mPq.printOutAll();
		mPq.insert(3);
		mPq.printOutAll();
		mPq.insert(8);
		mPq.printOutAll();
		mPq.insert(2);
		mPq.printOutAll();
		System.out.println("-------------");
		mPq.delHead();
		mPq.printOutAll();
		mPq.delHead();
		mPq.printOutAll();
		mPq.delHead();
		mPq.printOutAll();
		mPq.delHead();
		mPq.printOutAll();
		mPq.delHead();
		mPq.printOutAll();

	}


	private boolean isMaxPriorityQueue;

	private Key [] pq;

	private int num = 0;


	public MyPriorityQueue(int cap) {
		this(true, cap);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public MyPriorityQueue(boolean isMaxPriorityQueue, int cap) {
		Assert.isTrue(cap>0, String.format("size must greater than 0! now is %d", cap));
        // 索引 0 不⽤，所以多分配⼀个空间
		pq = (Key[]) new Comparable[cap + 1];
		this.isMaxPriorityQueue = isMaxPriorityQueue;
	}
	/* 返回当前队列中最⼤元素 */
	public Key head() {

		return pq[1];
	}

	/* 插⼊元素 e */
	public void insert(Key e) {
		Assert.isTrue(num < pq.length-1, "queue is full now!");
		num++;
        // 先把新元素加到最后
		pq[num] = e;
        // 然后让它上浮到正确的位置
		swim(num);
	}


	/* 删除并返回当前队列中最⼤元素 */
	public Key delHead() {
        // 最⼤堆的堆顶就是最⼤元素;最小堆的堆顶就是最小元素
		Key head = pq[1];
		// 把这个最⼤元素换到最后，删除之
		exch(1, num);
		pq[num] = null;
		num--;
		// 让 pq[1] 下沉到正确位置
		sink(1);
		return head;
	}

	private void exch(int i, int num) {
		Key temp = pq[i];
		pq[i] = pq[num];
		pq[num] = temp;
	}

	/* 上浮第 k 个元素，以维护最⼤堆性质 */
	private void swim(int k) {
		while (k/2 > 0 && isNeedSwim(k)){
			exch(k, k/2);
			k = k/2;
		}
	}

	private boolean isNeedSwim(int k) {
		if (isMaxPriorityQueue){
			if (pq[k].compareTo(pq[k/2]) > 0){
				return true;
			}
		}else {
			if (pq[k].compareTo(pq[k/2]) < 0){
				return true;
			}
		}
		return false;
	}

	/* 下沉第 k 个元素，以维护最⼤堆性质 */
	private void sink(int k) {

		while (2*k <= num && isNeedSink(k)){
			//处理奇数特殊情况
			if (2*k+1 > num){
				exch(k, 2*k);
				continue;
			}
			int maxChildIndex = pq[2*k].compareTo(pq[2*k +1]) > 0 ? 2*k : (2*k + 1);
			exch(k, maxChildIndex);
		}
	}

	private boolean isNeedSink(int k) {
		if (isMaxPriorityQueue){
			if ((pq[2*k] != null && pq[k].compareTo(pq[2*k]) < 0) || (pq[2*k + 1] != null && pq[k].compareTo(pq[2*k + 1]) < 0)){
				return true;
			}
		} else {
			if ((pq[2*k] != null && pq[k].compareTo(pq[2*k]) > 0) || (pq[2*k + 1] != null && pq[k].compareTo(pq[2*k + 1]) > 0)){
				return true;
			}
		}
		return false;
	}

	public void printOutAll(){
		for (Key key : pq){
			System.out.print(key+",");
		}
		System.out.println("");
	}
}
