package com.hq.learnning.leetcode.backtrack;


/**
 * 回溯算法解决“生成合法括号”问题
 * result = []
 * def backtrack(路径, 选择列表):
 *  if 满⾜结束条件:
 *      result.add(路径)
 *      return
 *  for 选择 in 选择列表:
 *      做选择
 *      backtrack(路径, 选择列表)
 *      撤销选择
 */
public class BackTrackGenerateParenthesis {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        int n = 3;
        generateParenthesis(n);
        System.out.println("cost time " + (System.nanoTime() - startTime) + "ns");
    }

    private static void generateParenthesis(int n) {
        char [] track = new char[2*n];
        char [] choice = new char[]{'(', ')'};
        backTrack(n, 0, track, choice);
    }

    private static void backTrack(int n, int i, char[] track, char[] choice) {
        //终止条件
        if (i == 2*n){
            System.out.println(track);
            return;
        }
        //循环遍历
        for (char value : choice ){
            if (!isIllegal(track, i, value)){
                continue;
            }
            //做选择
            track[i] = value;
            //递归
            backTrack(n, i + 1, track, choice);
            //撤销
            track[i] = '.';
        }
    }

    private static boolean isIllegal(char[] track, int i, char value) {
        int left = 0;
        int right = 0;
        track[i] = value;
        for(char item : track){
            if (item == '('){
                left++;
            }
            if (item ==')') {
                right++;
            }
        }
        track[i] = '.';

        if (i == track.length-1){
            return left==right? true: false;
        }
        return left>=right? true : false;
    }


}
