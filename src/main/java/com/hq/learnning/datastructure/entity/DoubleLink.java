package com.hq.learnning.datastructure.entity;

public class DoubleLink {

	Node head, tail;
	private int size;


	// 在链表头部添加节点 x，时间 O(1)
	public void addFirst(Node x){

		if (head == null){
			head = x;
			tail = x;
			this.size++;
			return;

		}
		Node tmp = head;
		head = x;
		head.setNext(tmp);
		tmp.setPre(head);

		this.size++;
	}

	// 删除链表中的 x 节点（x ⼀定存在）
    // 由于是双链表且给的是⽬标 Node 节点，时间 O(1)
	public void remove(Node x){

		if (head == x){
			if (head.getNext() == null){
				head = null;
				tail = null;
			}else {
				head = head.getNext();
				head.setPre(null);
			}
			this.size--;

		}
		//先假设x一定存在
		Node next = x.getNext();
		Node pre = x.getPre();
		pre.setNext(next);
		if (next != null){
			next.setPre(pre);
		}
		this.size--;
	}

	// 删除链表中最后⼀个节点，并返回该节点，时间 O(1)
	public Node removeLast(){

		if (size == 0){
			return null;
		}
		Node tmp = tail;
		if (size == 1){
			head = null;
			tail = null;
			this.size--;
			return tmp;
		}
		Node pre = tail.getPre();
		pre.setNext(null);
		tail = pre;

		this.size--;
		return tmp;

	}
	// 返回链表⻓度，时间 O(1)
	public int size(){
		return this.size;
	}

	public void printOut(){
		if (head == null){
			System.out.println("empty link!");
			return;
		}
		Node temp = head;
		while (temp != null){
			System.out.print(temp.getValue()+"->");
			temp = temp.getNext();
		}
		System.out.println();

	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
