package com.hq.learnning.datastructure.entity;

public class MyLink {

    private Node head;

    private int size;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void addInHead(Node node){
//        Assert.isNull()
        node.setNext(this.head);
        this.head = node;
        this.size++;
    }

    public int removeHead(){
        if (this.head == null){
            this.size = 0;
            return this.size;
        }

        this.head = this.head.getNext();
        return --this.size;
    }

    public void printNode(){
        if (this.head != null){
            Node tmp = this.head;
            while (tmp != null){
                System.out.print(tmp.getValue()+"->");
                tmp = tmp.getNext();
            }
            System.out.println("null");
        }
    }

}
