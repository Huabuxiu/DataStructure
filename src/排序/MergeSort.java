package 排序;

/**
 * @program: DataStructure
 * @description:
 * @author: Huabuxiu
 * @create: 2019-04-07 15:40
 **/
public class MergeSort {

    /**
    * @Description: 归并排序
    * @Param:
    * @return:
    * @Author: Huabuxiu
    * @Date: 2019/4/7
    */

    private static void merge(int[] a,int start,int mid, int end){
        int[] tmp = new int[end-start+1];
        int i = start;
        int j = mid+1;

        int k =0;

        while (i <= mid && j<= end){
            if (a[i] <= a[j])
                tmp[k++] = a[i++];
            else
                tmp[k++] = a[j++];
        }
        while (i <= mid)
            tmp[k++] = a[i++];
        while (j <= mid)
            tmp[k++] = a[j++];

        for (i=0;i<k;i++){
            a[start+i] =tmp[i];
        }
        tmp =null;
    }

    private static void mergeSortUp2Down(int[] a,int start,int end){
        if (a == null || start >= end){
            return;
        }

        int mid = (end + start) / 2;
        mergeSortUp2Down(a,start,mid);
        mergeSortUp2Down(a,mid+1,end);

        merge(a,start,mid,end);

    }

    public static void mergeSort(int[] a,int n){
        /**
        * @Description: 接口
        * @Param: [a, n]
        * @return: void
        * @Author: Huabuxiu
        * @Date: 2019/4/7
        */

        mergeSortUp2Down(a,0,n-1);
    }

}
