package a11_Algorithms;

import java.util.Arrays;

/*
    KMP:三位该算法的设计者首名
    > 当 S 和 T 出现不匹配字符时，利用已经部分匹配这个有效信息，保持 i 指针不回溯
      通过修改 j 指针至 T 部分匹配前缀(和Si部分后缀相同)的索引+1处，让模式串尽量地移动到有效的位置继续比较
  二、部分匹配值
    > 当前字符串"之前字符串"中，最大长度的相同后缀前缀
    > 后缀：sub(非头，尾)  j 扫描后缀
    > 前缀：sub(头，非尾)  k 扫描前缀
 */
public class A4_KMP {
    public static void main(String[] args) {
        // Brute Match
        String src1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String pat1 = "尚硅谷你尚硅你";
        int index1 = sequentialMatch(src1, pat1);
        System.out.println("sequential1 found:" + index1);

        String src2 = "BBC ABCDAB ABCDABCDABDE";
        String pat2 = "ABCDABD";
        int[] next2 = partialMatch(pat2);
        System.out.println("next2 : " + Arrays.toString(next2));
        int kmp2 = KMP(src2, pat2);
        System.out.println("kmp2 found:" + kmp2);

        String src3 = "abacbababeca";
        String pat3 = "abab";
//        String pat3 = "abaabe";
        int[] next3 = partialMatch(pat3);
        System.out.println("next3 : " + Arrays.toString(next3));
        int kmp3 = KMP(src3, pat3);
        System.out.println("kmp3 found:" + kmp3);
    }

    // Brute Match
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

    // next[] 当前字符"之前字符串"中，最大长度的前缀后缀
    private static int[] partialMatch(String pattern){
        int[] next = new int[pattern.length()];
        next[0] = -1;    // 没有匹配值标记
                         // index=0字符之前没有字符，直接不匹配，在index=1处直接填入0

        int j = 0;  // 扫描后缀
        int k = -1; // 当前字符的"之前字符串"最大前缀后缀，扫描前缀
        // j < arr.length-1 : 之前字符串的最大index = arr.length - 1
        // j将所有之前字符串尝试匹配后，跳出循环
        while ( j < pattern.length() - 1) {
            // (连续)失配后，pat[j] != pat[0] --> k == -1 --> j++,k重置,下一个字符索引处=0 (没有匹配值)
            // k == -1 : 没有匹配值，更新值后继续扫描
            // pat[j] == pat[k] ，后缀起始和前缀起始相同，更新值后继续扫描
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                ++j;            // 当前字符串的匹配长度 填在 下一个字符索引处
                ++k;            // 重置k 或 增加k匹配长度
                next[j] = k;    // 在下一个字符索引处填入当前的最大前缀后缀
            }else {
                k = next[k];    // 失配时，k移动到之前已匹配的最大前缀索引+1处，尝试找到跟多匹配值
            }
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

        int i = 0;  // 指向src
        int j = 0;  // 指向pat
        // 匹配成功则j跳出循环，匹配失败则i跳出循环
        while ( i < src.length() && j < pat.length()) {
            // (连续)失配后， src[i] != pat[0] --> k == -1 --> i++,j重置为0
            // j == -1 : src[i] != pat[0] 后移i，重置j
            if (j == -1 || src.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            }else {
                j = next[j];    // 失配时 pat相对于src向右移动了 j - next[j] 位
            }
        }
        if ( j == pat.length()) {
            return i - j;
        }
        return -1;
    }
}
