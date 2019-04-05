package 线性表.单向链表;


public class LinkedListTest {


    @org.junit.Test
    public void get() throws Exception {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i =0;i<10;i++){
            list.add(i);
        }
        for (int i =0;i<10;i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println("结束");

//        list.get(20);
        list.delete(5);
    }
}
