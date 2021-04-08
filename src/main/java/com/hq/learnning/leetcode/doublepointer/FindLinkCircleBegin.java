package com.hq.learnning.leetcode.doublepointer;

import com.hq.learnning.datastructure.entity.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * 快慢指针实现查找链表环的起始点
 * 前提：已知链表有环
 *
 *
 *
 * 快慢指针延伸：
 *    寻找链表的中点
 *    寻找链表倒数第k个数（让快指针先走k步，然后两者相同速度走）
 */
public class FindLinkCircleBegin {


    public static void main(String[] args) {
        int n = 20;
        int circleIndex = 17;
        Node head = initLink(n, circleIndex);

        printLink(head);

        Node circleBegin = detectCycle(head);
        System.out.println(circleBegin.getValue());


    }

    private static void printLink(Node head) {
        Node tmp = head;
        Set<Integer> visited = new HashSet<>();

        while (tmp != null){
            if (visited.contains(tmp.getValue())){
                System.out.println(tmp.getValue());
                break;
            }

            System.out.print(tmp.getValue()+"->");
            visited.add(tmp.getValue());
            tmp = tmp.getNext();
        }
        System.out.println();
    }

    private static Node initLink(int n, int circleIndex) {
        Node head = new Node(n,n);
        Node circle = null;
        Node tail = head;

        for (int index = n-1; index > 0; index--){
            Node item = new Node(index, index);
            item.setNext(head);
            head = item;

            if (index == circleIndex){
                circle = item;
            }
        }

        if (circle != null){
            tail.setNext(circle);
        }

        return head;
    }


    public static Node detectCycle(Node head) {
        Node fast, slow;
        fast = slow = head;
        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow) break;
        }
        // 上⾯的代码类似 hasCycle 函数

        slow = head;

        while (slow!=fast){
            slow = slow.getNext();
            fast = fast.getNext();
        }
        return slow;
    }
}
