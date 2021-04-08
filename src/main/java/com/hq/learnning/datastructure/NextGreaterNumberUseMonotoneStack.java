package com.hq.learnning.datastructure;

import java.util.Stack;

/**
 * 用单调栈解决下一较大值问题
 * 模板
 * vector<int> nextGreaterElement(vector<int>& nums) {
 *     vector<int> ans(nums.size()); // 存放答案的数组
 *     stack<int> s;
 *     for (int i = nums.size() - 1; i >= 0; i--) { // 倒着往栈⾥放
 *         while (!s.empty() && s.top() <= nums[i]) { // 判定个⼦⾼矮
 *             s.pop(); // 矮个起开，反正也被挡着了。。。
 *         }
 *         ans[i] = s.empty() ? -1 : s.top(); // 这个元素⾝后的第⼀个⾼个
 *         s.push(nums[i]); // 进队，接受之后的⾝⾼判定吧！
 *     }
 *     return ans;
 * }
 */
public class NextGreaterNumberUseMonotoneStack {


	public static void main(String[] args) {

		int[] nums = new int[]{2,1,2,4,3};

		int[] answer = nextGreaterNumber(nums);
		for (int index= 0; index<answer.length; index++){
			System.out.print(answer[index]+",");
		}
		System.out.println();


		int[] answerForCircle = nextGreaterNumberForCircle(nums);
		for (int index= 0; index<answerForCircle.length; index++){
			System.out.print(answerForCircle[index]+",");
		}
		System.out.println();

		int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
		int[] dayToWait = nextWarmerDayToWait(temperatures);
		for (int index= 0; index<dayToWait.length; index++){
			System.out.print(dayToWait[index]+",");
		}

		System.out.println();

	}


	/**
	 * 获取下一较大值列表
	 * @param nums
	 * @return
	 */
	public static int [] nextGreaterNumber(int[] nums){
		if (nums == null || nums.length == 0){
			return new int[0];
		}
		int[] answer = new int[nums.length];

		Stack<Integer> stack = new Stack<Integer>();
//		stack.push(nums[nums.length - 1]);
		for (int index = nums.length-1; index>=0; index--){

			while (!stack.isEmpty() && stack.peek() <= nums[index]){
				stack.pop();

			}
			//被优化
//			if (stack.isEmpty()){
//				answer[index] = -1;
//			}else {
//				answer[index] = stack.peek();
//			}
			//优化
			answer[index] = stack.isEmpty() ? -1 : stack.peek();

			stack.push(nums[index]);

		}

		return answer;
	}


	/**
	 * 变形题
	 *
	 * 给你⼀个数组 T = [73, 74, 75, 71, 69, 72, 76, 73]，这个数组存放的是近⼏天
	 * 的天⽓⽓温（这⽓温是铁板烧？不是的，这⾥⽤的华⽒度）。你返回⼀个数
	 * 组，计算：对于每⼀天，你还要⾄少等多少天才能等到⼀个更暖和的⽓温；
	 * 如果等不到那⼀天，填 0 。
	 * 举例：给你 T = [73, 74, 75, 71, 69, 72, 76, 73]，你返回 [1, 1, 4, 2, 1, 1, 0, 0]。
	 * 解释：第⼀天 73 华⽒度，第⼆天 74 华⽒度，⽐ 73 ⼤，所以对于第⼀天，
	 * 只要等⼀天就能等到⼀个更暖和的⽓温。后⾯的同理。
	 *
	 */
	public static int[] nextWarmerDayToWait(int[] temperatures){
		if (temperatures == null || temperatures.length == 0){
			return new int[0];
		}
		int[] answer = new int[temperatures.length];

		Stack<Integer> stack = new Stack<>();
//		stack.push(nums[nums.length - 1]);
		for (int index = temperatures.length-1; index>=0; index--){

			while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[index]){
				stack.pop();

			}
			//被优化
//			if (stack.isEmpty()){
//				answer[index] = 0;
//			}else {
//				answer[index] = stack.peek() - index;
//			}
            //优化
			answer[index] = stack.isEmpty() ? 0 : stack.peek() - index;


			stack.push(index);

		}

		return answer;
	}


	/**
	 * 变形题
	 * 循环数组：找到下一较大值
	 * 给你⼀个数组 [2,1,2,4,3]，你返回数组 [4,2,4,-1,4]。拥有了环形属性，最后
	 * ⼀个元素 3 绕了⼀圈后找到了⽐⾃⼰⼤的元素 4
	 *
	 */
	public static int[] nextGreaterNumberForCircle(int[] nums){
		if (nums == null || nums.length == 0){
			return new int[0];
		}
		int[] answer = new int[nums.length];
		Stack<Integer> stack = new Stack<>();

		for (int index = nums.length-1; index>=0; index--){
			stack.push(nums[index]);
		}

		for (int index = nums.length-1; index>=0; index--){
			while (!stack.isEmpty() && stack.peek() <= nums[index]){
				stack.pop();
			}
			//优化
			answer[index] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(nums[index]);
		}

		return answer;
	}

}
