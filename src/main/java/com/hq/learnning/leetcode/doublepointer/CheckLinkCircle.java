package com.hq.learnning.leetcode.doublepointer;

import com.hq.learnning.datastructure.entity.Node;

/**
 * 快慢指针解决链表是否有环问题
 */
public class CheckLinkCircle {


    public static void main(String[] args) {

    }

    /**
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(Node head) {
        Node fast, slow;
        fast = slow = head;
        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow) return true;
        }
        return false;
    }

}
