package com.hq.learnning.leetcode.slipwindow;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最小覆盖子串
 * 在S(source) 中找到包含T(target) 中全部字母的一个子串，
 * 且这个子串一定是所有可能子串中最短的
 */
public class MinWindowSubstring {

    public static void main(String[] args) {

        String s = "ADOBECODEBANCAA";
        String t = "AA";

        String result;
//        result = minWindowSubstring(s, t);
//        System.out.println(result);
//
//
//        result = minWindowSubstringWithRepeat(s, t);
//        System.out.println(result);
//
//        result = minWindow(s, t);
//        System.out.println(result);

//        s = "aaaaaaaa";
//        result = longestSubWithoutRepeat(s);
//        System.out.println(result);

        boolean isTrue;
        String src = "eidbabaooo";
        String sub = "aa";
        isTrue = permutationInString(sub, src);
        System.out.println(isTrue);

        System.out.println((int)'a');
        System.out.println((int)' ');
        System.out.println((int)'A');

    }

    /**
     * 字符串排列
     * Permutation in String
     */
    public static boolean permutationInString(String sub, String src){

        if (StringUtils.isEmpty(sub) || StringUtils.isEmpty(src)){
            return false;
        }

        return doPermutationInString(sub, src);
    }

    /**
     *
     * @param sub
     * @param src
     * @return
     */
    private static boolean doPermutationInString(String sub, String src) {

        int left = 0;
        int right = 0;
        char[] subArray = sub.toCharArray();
        char[] srcArray = src.toCharArray();

        Map<Character, Integer> need = new HashMap<>();
        for (char c : subArray){
            need.put(c, need.getOrDefault(c, 0) + 1);
        }


        while (right < src.length()){

            //优化：窗口要大于等于子串长度，才有可能匹配
            if (right < sub.length()-1){
                right++;
                continue;
            }
//            //优化
//            while (left < right && !need.containsKey(srcArray[left])){
//                left++;
//            }
//            right = left + sub.length() - 1;

            //特殊的收缩窗口：窗口必须保证和子串长度一致，才有可能是完全匹配的排列串
            left = right-(sub.length() - 1);
            //匹配判断
            if (isFullMatch(srcArray, left, right, need)){
                return true;
            }
            //滑动窗口
            right++;
        }


        return false;
    }

    private static boolean isFullMatch(char[] src, int left, int right, Map<Character, Integer> need) {
        Map<Character, Integer> window = new HashMap<>();

        for(int index = left; index <= right; index++){
            window.put(src[index], window.getOrDefault(src[index], 0) + 1);
        }
        for (char c : need.keySet()){
            if (!window.containsKey(c) || window.get(c) != need.get(c)){
                return false;
            }
        }
        return true;
    }



    /**
     * 最长无重复子串
     * Longest Substring Without Repeating Characters
     */
    public static String longestSubWithoutRepeat(String s){
        if (s == null || s.length() == 1){
            return s;
        }
        return doGetLongestSubWithoutRepeat(s);
    }

    /**
     *
     * @return
     */
    private static String doGetLongestSubWithoutRepeat(String s) {

        int left = 0;
        int right = 0;

        int start = 0;
        int end = 0;
        Set<Character> visited = new HashSet<>();
        char[] sArray = s.toCharArray();
        while (right < s.length()){
            char c = sArray[right];
            //遇到重复值
            if (visited.contains(c)){
                //记录无重复子串最优值
                if (right-left > end -start){
                    start = left;
                    end = right;
                }
                //收缩窗口
                while (sArray[left] != c){
                    if (visited.contains(sArray[left])){
                        visited.remove(sArray[left]);
                    }
                    left++;
                }
                left++;
            }

            visited.add(c);
            right++;

        }

        return end > 0 ? s.substring(start, end) : "";
    }


    /**
     * 滑动窗口
     *
     * 关键：找到一个匹配串时，继续找时，
     * 将left移动到下一个在t中存在的值位置，将right++
     *
     *
     * 缺陷：
     *  1 不支持t中全部重复如AAA--已特殊处理
     *  2 还有另一种理解，t若有重复字符，结果中也要有同样重复个数
     *     如
     *         String s = "ADOBECODEBANCA";
     *         String t = "AAC";
     *         result = "ANCA"
     *     这需要另一种计数解法@minWindowSubstringWithRepeat
     * @param s
     * @param t
     */
    public static String minWindowSubstring(String s, String t) {

        String tmp = getNoRepeatString(t);
        if (tmp.length() == 1){
            return s.contains(tmp)? tmp : "";
        }

        String result = s;
        char[] charArray = s.toCharArray();
        int left = 0, right = 0;
        //在s中找到第一个元素（在t中存在的的）
        while (!t.contains(charArray[left]+"")){
            left++;
        }

        right = left + t.length()-1;

        while (right < s.length()) {
            // 从候选中获取最短
            if (isCandidate(s.substring(left, right+1), t)){
                result = result.length() > (right - left +1)? s.substring(left, right+1) : result;
                //左移窗口
                left ++;

                //找到下一元素（在t中的）
                while (!t.contains(charArray[left]+"")&&left<s.length()-1){
                    left++;
                }
            }
            // 右移窗口
            right++;

        }

        return result == s ? "" : result;
    }

    /**
     * 计数法实现
     * @param s
     * @param t
     * @return
     */
    public static String minWindowSubstringWithRepeat(String s, String t){

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        char[] tArray = t.toCharArray();
        for (char c : tArray){
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        System.out.println(need.values());


        char[] sArray = s.toCharArray();
        int left = 0, right = 0;
        int valid = 0;

        // 记录最小覆盖子串的起始索引及长度
        int start = 0, end = s.length() + 1;
        while (right < sArray.length) {
            // 开始滑动
            char c = sArray[right];
            window.put(c, window.getOrDefault(c, 0) + 1);

            System.out.println(left +", "+ right);
            right++;
            //是否匹配
            if (isMatch(need, window)){
                //优化左端
                while (left < right){
                    if(!t.contains(sArray[left]+"")){
                        left++;
                    }else if (need.getOrDefault(sArray[left], 0) < window.getOrDefault(sArray[left], 0)){
                        window.put(sArray[left], window.get(sArray[left]) -1);
                        left++;
                    }else {
                        break;
                    }
                }

                //更新最优值
                if ((end - start) > (right - left)){
                    //更新记录
                    start = left;
                    end = right;//此时是++后的值，因为是[left, right)
                }
                //滑动窗口
                char d = sArray[left];
                window.put(d, window.get(d)-1);
                left++;

            }
        }
        return end == (s.length() + 1)? "":  s.substring(start, end);
    }

    private static boolean isMatch(Map<Character, Integer> need, Map<Character, Integer> window) {
        for (Character c : need.keySet()){
            if (need.get(c) > window.getOrDefault(c, 0)){
                return false;
            }
        }
        return true;
    }


    /**
     * 判断是否匹配
     * @param s
     * @param t
     * @return
     */
    private static boolean isCandidate(String s, String t) {
        for (char c : t.toCharArray()){
            if (!s.contains(""+c)){
                return false;
            }
        }
        return true;
    }


    public static boolean isOneValidValueString(String t){
        if (t != null && t.length() == 1){
            return true;
        }
        String first = t.substring(0,1);
        char[] array = t.substring(1, t.length()).toCharArray();
        for (int i = 0 ; i < array.length; i++){
            if (!first.equals(array[i]+"")){
                return false;
            }
        }
        return true;
    }

    /**
     * 提取非重复子集
     * @param t
     * @return
     */
    public static String getNoRepeatString(String t){
        Set<Character> visited = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();

        char[] array = t.toCharArray();
        for (int i = 0 ; i < array.length; i++){
            if (visited.contains(array[i])){
                continue;
            }
            stringBuilder.append(array[i]);
            visited.add(array[i]);
        }

        return stringBuilder.toString();


    }


    /**
     * labuladong算法
     *
     * 缺陷：
     *     String s = "ADOBECODEBANCAA";
     *     String t = "AA";
     *
     * 不能正常识别
     *
     *
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
//        unordered_map<char, int> need, window;
//        for (char c : t) need[c]++;
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        char[] tArray = t.toCharArray();
        for (char c : tArray){
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        System.out.println(need.values());

        char[] sArray = s.toCharArray();


        int left = 0, right = 0;
        int valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = sArray[right];
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.getOrDefault(c, 0) == need.getOrDefault(c, 0))
                    valid++;
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗口的字符
                char d = sArray[left];
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.getOrDefault(d, 0) == need.getOrDefault(d, 0))
                        valid--;
                    window.put(d, window.getOrDefault(d, 0) + 1);
                }
            }
        }
        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ?
                "" : s.substring(start, start + len);
    }


}
