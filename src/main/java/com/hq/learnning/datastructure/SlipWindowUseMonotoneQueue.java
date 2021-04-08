package com.hq.learnning.datastructure;

import com.hq.learnning.datastructure.entity.MonotoneQueue;

import java.util.PriorityQueue;
import java.util.Queue;

public class SlipWindowUseMonotoneQueue {

	public static void main(String[] args) {
      int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
      int windowSize = 3;
      int[] answer = getSlipWindowMaxValueList(nums, windowSize);


//      for (int i : answer){//这种遍历不保证顺序
//		  System.out.print(answer[i]+",");
//	  }
		for(int index=0; index< answer.length;index++){
			System.out.print(answer[index]+",");
		}

	}

	public static int[] getSlipWindowMaxValueList(int[] nums, int windowSize){

		if (nums == null || nums.length < 2){
			return nums;
		}
		int[] answer = new int[nums.length-windowSize+1];

		MonotoneQueue queue = new MonotoneQueue();
		for (int index = 0; index < nums.length; index++){
			if (index < windowSize-1){
				queue.push(nums[index]);
				continue;
			}
			queue.push(nums[index]);
			answer[index - windowSize + 1] = queue.max();
			queue.pop(nums[index - windowSize + 1]);
		}

		return answer;
	}

}
