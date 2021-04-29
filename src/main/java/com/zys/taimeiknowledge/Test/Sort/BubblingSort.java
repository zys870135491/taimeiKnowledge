package com.zys.taimeiknowledge.Test.Sort;

/**
 * @author yisong.zhang
 * @since 2021/4/26 17:06
 * 冒泡排序
 * https://blog.csdn.net/qq_41679818/article/details/90296399
 */
public class BubblingSort {

    public static void main(String[] args) {
        int[] arr = {2,1,5,4};
        int len = arr.length;

        for(int i = 0 ;i<len;i++){
            //每次都少比较一位(因为第一轮比到最后j+1)
            for(int j = 0; j<len-1-i; j++ ){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }

        }

    }

}
