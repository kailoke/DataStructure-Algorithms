package a11_Algorithms;

import java.util.Arrays;

/*
    KMP:三位设计者首名，
    > 当子串出现不匹配字符时，不对源字符串进行指针回溯+1
    > 源字符串指向值和 子串部分匹配表的上一个相同匹配值的 子串索引值 进行比较，若相等则此索引前的值均相等
        > 判断源字符串的 匹配后缀 == 子字符串的匹配前缀
    > 子串的部分匹配值，没有部分匹配则又从头开始匹配；使源字符串不用进行回溯
 */
public class A4_KMP {
    public static void main(String[] args) {
        String src1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String pat1 = "尚硅谷你尚硅你";

        // 顺序匹配
        int index1 = sequentialMatch(src1, pat1);
        System.out.println("sequential found:" + index1);

        String src2 = "BBC ABCDAB ABCDABCDABDE";
        String pat2 = "ABCDABD";
//        String pat2 = "BBC";
        int[] next = partialMatch(pat2);
        System.out.println("next : " + Arrays.toString(next));

        int kmp = KMP(src2, pat2);
        System.out.println("kmp found:" + kmp);
    }
    // 暴力匹配
    public static int sequentialMatch(String src,String pattern){
        char[] dest = src.toCharArray();
        char[] pat = pattern.toCharArray();

        int i = 0;  // i 指向dest
        int j = 0;  // j 指向pat
        while (i < dest.length && j < pat.length){
            // 匹配成功则继续匹配
            if ( dest[i] == pat[j]){
                i++;
                j++;    // pat超出索引后匹配结束，或pat && src 同时超出
            // 匹配失败则i+1继续后移，j置0
            }else {
                i = i - j + 1;
                j = 0;
            }
        }
//        return j == 0 ? -1 : i-j;
        return j == pat.length ? i-j : -1;
    }

    // 1. 得到pattern的部分匹配值
    public static int[] partialMatch(String pattern){
        int[] next = new int[pattern.length()];
        next[0] = 0;    // 长度1的字符串，没有前后缀，部分匹配值 == 0;

        // i指向pat
        for (int i = 1,j = 0; i < pattern.length(); i++) {
            // 部分匹配值的核心，此处 j = next[j-1] 不懂
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j-1];
            }
            // 后缀(从头后开始逐渐减少长度至尾巴)的结束 == 前缀(从头开始逐渐增加长度至尾巴前)的起始 则为部分匹配
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    /**
     * kmp搜索算法
     * @param src   源字符串
     * @param pat   子串
     * @return  -1 无匹配，否则是第一个匹配的位置
     */
    public static int KMP(String src,String pat){
        // 获得子串的部分匹配值
        int[] next = partialMatch(pat);

        // i指向src, j指向pat
        for (int i = 0,j = 0; i < src.length(); i++) {
            // KMP算法核心：字符不等时，重新指向pat指针j为上一个部分匹配值的索引，对比是否和子串索引值一致
            // j == 0 ,没有任何相等值时不需要进行匹配值查找
            if (j > 0 && src.charAt(i) != pat.charAt(j)){
                j = next[j-1];
            }
            if (src.charAt(i) == pat.charAt(j)){
                j++;
            }
            if (j == pat.length()) {
                // return时，i没有执行i++，手动+1
                return i + 1 - j;
            }
        }
        return -1;
    }
}
