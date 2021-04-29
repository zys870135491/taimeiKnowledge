package com.zys.taimeiknowledge.Test.Sort;

/**
 * @author yisong.zhang
 * @since 2021/4/28 10:52
 *https://blog.csdn.net/qq_28081081/article/details/80594386
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {3,1,5,2,8,6};
        int len = arr.length;

        for(int i=1; i<len; i++){
            int temp = arr[i];//保存每次需要插入的那个数
            int j;
            for(j=i; j>0&&arr[j-1]>temp; j--){//这个较上面有一定的优化
                arr[j] = arr[j-1];//吧大于需要插入的数往后移动。最后不大于temp的数就空出来j
            }
            arr[j] = temp;//将需要插入的数放入这个位置
        }


        for (int i : arr) {
            System.out.println(i);
        }

    }

}
