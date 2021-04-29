package com.zys.taimeiknowledge.Test.Sort;

/**
 * @author yisong.zhang
 * @since 2021/4/16 17:10
 * 1.选择排序
 * https://www.cnblogs.com/captainad/p/10946033.html
 */
public class ChoiceSort {

    public static void main(String[] args) {

        int[] arr = {2,1,5,4};
        int len = arr.length;

        // i< len-1 是因为比较时只需要比较到倒数第二位即可
        for (int i = 0; i < len - 1; i++) {
            int minIdx = i;
            // j<len 是因为比较时需要比较到最后一位
            for (int j = i + 1; j < len; j++) {
                // 找到最小的数
                if (arr[minIdx] > arr[j]) {
                    // 保存最小数的索引
                    minIdx = j;
                }
            }
            // 如果一轮比较下来都没有变更最小值的索引则无需调换顺序
            if (minIdx != i) {
                int tmp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = tmp;
            }
        }


    }



}


