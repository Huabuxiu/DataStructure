package 排序;

/**
 * @program: DataStructure
 * @description:
 * @author: Huabuxiu
 * @create: 2019-04-07 09:45
 **/
public class Sort1 {

    public static void insertSort(int[] a,int n){
        int i,j,k;
        for (i=1;i<n;i++){
            for (j=i-1;j>0;j++){
                if (a[j]<a[i])
                    break;
            }
            if (j !=i-1){
                int tmp = a[i];
                for (k = i-1;k>j;k--){
                    a[k+1] = a[k];
                }
                a[k+1] =tmp;
            }
        }
    }


    public static  void selectSort(int[] a,int n){
        /**
        * @Description: 选择排序，在未排序的序列中选择最小的放到排好序的列对的位置
        * @Param: [a, n]
        * @return: void
        * @Author: Huabuxiu
        * @Date: 2019/4/7
        */

        int i;  //有序的末尾
        int j;  //无序的起始
        int min;    //无序区最小的

        for (i =0;i<n;i++){
            min = i;

            for (j= i+1;j<n;j++){
                if (a[j]<a[min])
                    min=j;
            }

            // 若min!=i，则交换 a[i] 和 a[min]。
            // 交换之后，保证了a[0] ... a[i] 之间的元素是有序的。
            if (min!=i)
            {
                int tmp = a[i];
                a[i] = a[min];
                a[min] =tmp;
            }
        }

    }

    public static void bubbleSort(int[] a,int n){
        /**
        * @Description: 冒泡排序
        * @Param: [a, n]
        * @return: void
        * @Author: Huabuxiu
        * @Date: 2019/4/7
        */
        int i,j;

        for (i=0;i<n-1;i++){    //趟数
            for (j=0;j<n-1-i;j++){  //交换
                if (a[j]<a[j+1]){
                    int tmp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }
}
