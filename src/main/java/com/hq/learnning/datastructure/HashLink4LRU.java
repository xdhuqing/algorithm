package com.hq.learnning.datastructure;

import com.hq.learnning.datastructure.entity.DoubleLink;
import com.hq.learnning.datastructure.entity.Node;

import java.util.HashMap;

public class HashLink4LRU {


	public static void main(String[] args) {

		HashLink4LRU hashLink4LRU = new HashLink4LRU(3);
		hashLink4LRU.cache.printOut();
		hashLink4LRU.put(1,1);
		hashLink4LRU.cache.printOut();
		hashLink4LRU.put(2,2);
		hashLink4LRU.cache.printOut();
		hashLink4LRU.put(3,3);
		hashLink4LRU.cache.printOut();
		hashLink4LRU.put(4,4);
		hashLink4LRU.cache.printOut();
		hashLink4LRU.put(5,5);
		hashLink4LRU.cache.printOut();
		hashLink4LRU.put(1,1);
		hashLink4LRU.cache.printOut();
		hashLink4LRU.put(2,2);
		hashLink4LRU.cache.printOut();
		hashLink4LRU.put(3,3);
		hashLink4LRU.cache.printOut();



	}

	// key -> Node(key, val)
	private HashMap<Integer, Node> map;
	// Node(k1, v1) <-> Node(k2, v2)...
	private DoubleLink cache;
	// 最⼤容量
	private int cap;
	public HashLink4LRU(int capacity) {
		this.cap = capacity;
		map = new HashMap<>();
		cache = new DoubleLink();
	}

	public int get(int key){

		if (!this.map.containsKey(key)){
			return -1;
		}

		Node node = this.map.get(key);
		this.cache.remove(node);
		this.cache.addFirst(node);

		return node.getValue();
	}

	public Node put(int key, int value){

		Node node = new Node(key, value);
		if (this.map.containsKey(key)){
			this.map.remove(key);
			this.cache.remove(node);

			this.map.put(key, node);
			this.cache.addFirst(node);
		}else {
			if (this.cap <= this.cache.size()){
				Node tail = this.cache.getTail();
				this.cache.removeLast();
				this.map.remove(tail.getKey());
			}
			this.map.put(key, node);
			this.cache.addFirst(node);
		}

		return node;
	}



}
