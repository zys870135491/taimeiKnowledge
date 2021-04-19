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

        int[] ints = selectionSort(arr);
        System.out.println(ints);

    }


   public static int[] selectionSort(int[] array) {
       int len = array.length;
       // 如果数组长度为0或者1，都是不用排序直接返回
       if (len == 0 || len == 1) {
           return array;
       }
       for (int i = 0; i < len - 1; i++) {
           int minIdx = i;
           for (int j = i + 1; j < len; j++) {
               // 找到最小的数
               if (array[minIdx] > array[j]) {
                   // 保存最小数的索引
                   minIdx = j;
               }
           }
           // 如果一轮比较下来都没有变更最小值的索引则无需调换顺序
           if (minIdx != i) {
               int tmp = array[i];
               array[i] = array[minIdx];
               array[minIdx] = tmp;
           }
       }
       return array;
   }

}


