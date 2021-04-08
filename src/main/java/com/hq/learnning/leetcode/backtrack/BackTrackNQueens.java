package com.hq.learnning.leetcode.backtrack;

import org.springframework.util.StringUtils;

import java.util.*;

public class BackTrackNQueens {
	/**
	 * Q...
	 * .Q..
	 * ..Q.
	 * ...Q
	 */
//	private static List<char[][]> all_board = new LinkedList<>();  有引用问题
	private static List<List<String>> result = new LinkedList<>();

	public static void main(String[] args) {
		int num = 3;
		long startTime = System.nanoTime();
		nQueens(num);
		long endTime = System.nanoTime();

		System.out.println(String.format("board num={%d}, cost time = {%d} nano",
				result.size(), endTime-startTime));

//		for (char[][] board : all_board){
//			for (int i = 0 ; i < num; i++){
//				System.out.println(board[i]);
//			}
//		}

		for (List<String> board : result){
			for (String row : board){
				System.out.println(row);
			}
			System.out.println(String.format("%10s", "-").replace(" ", "-"));
		}
	}

	private static void nQueens(int num) {

		//棋盘
		char[][] board = new char[num][num];
		//init board
		for (int i = 0 ; i < num; i++){
			for (int j = 0 ; j < num; j++){
				board[i][j] = '.';
			}
			System.out.println(board[i]);
		}

		System.out.println("finished init!");

		backTrace(board, 0);

	}

	private static void backTrace(char[][] board, int row) {
		if (row == board[0].length){
//			all_board.add(board.clone());
			LinkedList<String> temp = new LinkedList<>();
			for (int i= 0;i< row; i++){
				temp.add(String.valueOf(board[i]));
			}
			result.add(temp);

			return;
		}

		int colNum = board[0].length;
		for (int col = 0; col < colNum; col++){
			if (!isValid(board, row, col)){
				continue;
			}
			board[row][col] = 'Q';
			backTrace(board, row + 1);
			board[row][col] = '.';

		}
	}

	private static boolean isValid(char[][] board, int row, int col) {
		int n = board.length;
        // 检查列是否有皇后互相冲突
		for (int i = 0; i < n; i++) {
			if (board[i][col] == 'Q')
				return false;
		}
//        // 检查右上⽅是否有皇后互相冲突
//		for (int i = row - 1, j = col + 1;
//			 i >= 0 && j < n; i--, j++) {
//			if (board.get(i)[j] == 'Q')
//				return false;
//		}
//        // 检查左上⽅是否有皇后互相冲突
//		for (int i = row - 1, j = col - 1;
//			 i >= 0 && j >= 0; i--, j--) {
//			if (board.get(i)[j] == 'Q')
//				return false;
//		}
		return true;
	}
}
