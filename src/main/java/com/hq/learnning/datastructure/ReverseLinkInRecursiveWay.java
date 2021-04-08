package com.hq.learnning.datastructure;

import com.hq.learnning.datastructure.entity.MyLink;
import com.hq.learnning.datastructure.entity.Node;

/**
 * 使用递归翻转链表
 * 1 翻转链表一般使用双指针，使用递归会复杂些
 * 2 递归策略
 *    翻转整个链表、翻转部分链表
 *
 */
public class ReverseLinkInRecursiveWay {

    public static Node next;

    public static void main(String[] args) {

        MyLink myLink = new MyLink();
        for (int i = 10; i > 0; i--){
            Node tmp = new Node(i, i);
            myLink.addInHead(tmp);
        }
        System.out.println("init:");
        myLink.printNode();

        reverseWholeLink(myLink);

        System.out.println("reverseWholeLink:");
        myLink.printNode();

        reversePartyLink(myLink, 5);

        System.out.println("reversePartyLink n=5:");
        myLink.printNode();

        reverseLinkBetween(myLink, 2,3);

        System.out.println("reverseLinkBetween [2,3]:");
        myLink.printNode();



    }

    /**
     * 翻转链表
     * @param myLink
     */
    private static void reverseWholeLink(MyLink myLink) {
        myLink.setHead(doReverseWholeLink(myLink.getHead()));
    }


    /**
     * 翻转链表前N个节点
     *
     * @param myLink
     */
    private static void reversePartyLink(MyLink myLink, int n) {
        if (myLink == null || myLink.getSize() < n){
            return;
        }
        myLink.setHead(doReversePartyLink(myLink.getHead(), n));
    }

    /**
     * 翻转链表指定区间[m, n]  下标从0开始
     * 1->2->3->4->5->6->7->8->9->10->null
     * [2,3]
     * out:
     * 1->3->2->4->5->6->7->8->9->10->null
     * @param myLink
     */
    private static void reverseLinkBetween(MyLink myLink, int m, int n) {
        if (myLink == null || myLink.getSize() < n || m >= n){
            return;
        }
        if (m ==  0){
            reversePartyLink(myLink, n);
            return;
        }
        int index = 0;
        Node begin = myLink.getHead();
        Node pre = null;
        while (index < m){
            pre = begin;
            begin = begin.getNext();
            index++;
        }

        pre.setNext(doReversePartyLink(begin, n-m+1));//注意加1处理
    }




    /**
     *
     * @param head
     * @param n
     * @return
     */
    private static Node doReversePartyLink(Node head, int n) {
        if (head.getNext() == null || 1 == n){
            next = head.getNext();
            return head;
        }
        Node last = doReversePartyLink(head.getNext(), --n);//注意此处"--"。若改为n--，则传递的值永远是n
        head.getNext().setNext(head);
        head.setNext(next);
        return last;
    }


    /**
     * 翻转整个链表：递归实现
     * @param head
     * @return
     */
    private static Node doReverseWholeLink(Node head) {
        if (head == null || head.getNext() == null){
            return head;
        }
        Node last = doReverseWholeLink(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return last;
    }


}
