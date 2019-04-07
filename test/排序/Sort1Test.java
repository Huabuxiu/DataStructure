package 排序;


import org.junit.Test;

public class Sort1Test {


    @Test
    public void bubbleSort() {
        int[] a = new int[]{0,32,4,23,7,43,83,655,31,9};
//        Sort1.bubbleSort(a,a.length);
//        Sort1.selectSort(a,a.length);
//        Sort1.selectSort(a,a.length);

//        QuickSort.quickSort(a,a.length);
        MergeSort.mergeSort(a,a.length);
        for (int x :
                a) {
            System.out.print(x+" ");
        }
    }
}
