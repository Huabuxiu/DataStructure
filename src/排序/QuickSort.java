package 排序;

/**
 * @program: DataStructure
 * @description:    递归快排
 * @author: Huabuxiu
 * @create: 2019-04-07 10:37
 **/
public class QuickSort {
    private static void quicksort(int[] a,int l, int r){

        if ( l < r){
            int i,j,x;

            i =l;
            j = r;
            x = a[i];

            while (i < j ){
                //移动区间
                while (i < j && a[j] > x)   //找到后面区间比x小的
                    j--;
                if (i<j){
                    a[i++] =a[j];   //移动到前面区间，
                }
                while (i<j && a[i]< x)
                    i++;
                if (i<j){
                    a[j--] = a[i];
                }
            }
            a[i] = x;
            quicksort(a,l,i-1); //前半部分
            quicksort(a,i+1,r); //后半部分
        }
    }

    public static void quickSort(int[] a,int n){
        //统一接口
        quicksort(a,0,n-1);
    }
}
