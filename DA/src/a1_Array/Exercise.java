package a1_Array;

import org.junit.Test;
import java.util.HashSet;

public class Exercise {
    @Test
    /*
        从123456789中选出数字使其满足 □□□ X □□ == □□□□
        方框中的数字只能选1位且不能重复，找出满足条件的解
     */
    public void test1(){
        int[] list = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (j==1){
                    continue;
                }
                for (int k = 0; k < list.length; k++) {
                    if (k==i || k==j){
                        continue;
                    }
                    for (int l = 0; l < list.length; l++) {
                        if (l==i || l==j || l==k){
                            continue;
                        }
                        for (int m = 0; m < list.length; m++) {
                            if (m==i || m==j || m==k || m==l){
                                continue;
                            }
                            int a = list[i]*100 + list[j]*10 + list[k];
                            int b = list[l]*10 + list[m];
                            int result = a * b;
                            // 不含0且位数<=4的结果则继续，否则跳出循环
                            String right = String.valueOf(result);
                            if (right.length() != 4 || right.contains("0")) {
                                continue;
                            }

                            // 转字符串加入集合
                            int src = a * 100 + b;
                            HashSet<Character> set = new HashSet<>();
                            String left = String.valueOf(src);
                            for (int n = 0; n < left.length(); n++) {
                                set.add(left.charAt(n));
                            }
                            for (int n = 0; n < right.length(); n++) {
                                set.add(right.charAt(n));
                            }
                            if (set.size() == 9){
                                System.out.println( a + " * " + b + " = " + result);
                            }
                        }
                    }
                }
            }
        }
    }
}
