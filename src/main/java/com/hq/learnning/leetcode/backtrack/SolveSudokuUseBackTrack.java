package com.hq.learnning.leetcode.backtrack;

/**
 * 回溯算法求解数独
 * void solveSudoku(char[][] board) {
 *     backtrack(board, 0, 0);
 * }
 *
 * void backtrack(char[][] board, int r, int c) {
 *     int m = 9, n = 9;
 *     // 就是对棋盘的每个位置进行穷举
 *     for (int i = r; i < m; i++) {
 *         for (int j = c; j < n; j++) {
 *             // 做选择
 *             backtrack(board, i, j);
 *             // 撤销选择
 *         }
 *     }
 * }
 */
public class SolveSudokuUseBackTrack {

    public static void main(String[] args) {

        char[][] board = new char[9][9];

        initBoard(board);

        System.out.println("init:");
        printBoard(board);

        long startTime = System.nanoTime();
        solveSudoku(board);
        System.out.println("solveSudoku cost time:"+(System.nanoTime()-startTime)+"ns");
        printBoard(board);


        initBoard(board);
        System.out.println("init:");
        printBoard(board);
        startTime = System.nanoTime();
        solveSudokuDoubleFor(board);
        System.out.println("solveSudokuDoubleFor cost time:"+(System.nanoTime()-startTime)+"ns");
        printBoard(board);


    }

    private static boolean solveSudokuDoubleFor(char[][] board) {
        return doResolveUseBackTrackDoubleFor(board, 0, 0);
    }

    /**
     * 双循环回溯
     * 递归实现回溯，双循环实现每个格子的计算
     *
     * @param board
     * @param r
     * @param c
     */
    private static boolean doResolveUseBackTrackDoubleFor(char[][] board, int r, int c) {
        int m = 9, n = 9;
        if (c == n) {
            // 穷举到最后一列的话就换到下一行重新开始。
            return doResolveUseBackTrackDoubleFor(board, r + 1, 0);
        }
        if (r == m) {
            // 找到一个可行解，触发 base case
            return true;
        }
        // 就是对每个位置进行穷举
        for (int i = r; i < m; i++) {
            for (int j = c; j < n; j++) {

                if (board[i][j] != '.') {
                    // 如果有预设数字，不用我们穷举
                    return doResolveUseBackTrackDoubleFor(board, i, j + 1);
                }

                for (char ch = '1'; ch <= '9'; ch++) {
                    // 如果遇到不合法的数字，就跳过
                    if (!isValid(board, i, j, ch))
                        continue;

                    board[i][j] = ch;
                    // 如果找到一个可行解，立即结束
                    if (doResolveUseBackTrackDoubleFor(board, i, j + 1)) {
                        return true;
                    }
                    board[i][j] = '.';
                }
                // 穷举完 1~9，依然没有找到可行解，此路不通
                return false;
            }
        }
        return false;
    }




    /**
     *
     * @param board
     * @return true-resolved  false-no answer
     */
    private static boolean solveSudoku(char[][] board) {
        return doResolveUseBackTrack(board, 0, 0);
    }

    /**
     * 思路：
     * 套用公式，特殊处理
     *
     * 公式可以解决一行的搜索，所以当列为最大值后，让行加一，进入下一行
     * @param board
     * @param row
     * @param col
     * @return
     */
    private static boolean doResolveUseBackTrack(char[][] board, int row, int col) {

        //终止条件
        if (row == board.length){
            return true;
        }
        //特殊处理：遍历下一行
        if (col == board[0].length){
            return doResolveUseBackTrack(board, row+1, 0);
        }
        //特殊处理：跳过预设数字
        if (board[row][col] != '.'){
            return doResolveUseBackTrack(board, row, col+1);
        }

        for (char ch = '1'; ch <= '9'; ch++) {
            //跳过不合法
            if (!isValid(board, row, col, ch)){
                continue;
            }
            // 做选择
            board[row][col] = ch;
            // 继续穷举下⼀个.如果找到⼀个可⾏解，⽴即结束
            if (doResolveUseBackTrack(board, row, col + 1)){
                return true;
            }
            // 撤销选择
            board[row][col] = '.';
        }

        return false;

    }

    private static boolean isValid(char[][] board, int row, int col, char n) {
        for (int i = 0; i < 9; i++) {
            // 判断⾏是否存在重复
            if (board[row][i] == n) return false;
            // 判断列是否存在重复
            if (board[i][col] == n) return false;
            // 判断 3 x 3 ⽅框是否存在重复
            if (board[(row/3)*3 + i/3][(col/3)*3 + i%3] == n)
                return false;
        }
        return true;
    }

    private static void printBoard(char[][] board) {
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }

    }

    private static void initBoard(char[][] board) {
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                board[i][j] = '.';
            }
        }

    }


}
