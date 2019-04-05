package 线性表.双向链表;


import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class DoubleLinkTest {


    @Test
    public void add() {
        DoubleLink<Integer> doubleLink = new DoubleLink<>();

        if (doubleLink.isEmpty())
        {
            System.out.println("空");
        }else {
            System.out.println("不空");
        }

        for (int i =0; i<10;i++){
            doubleLink.add(i);
        }

        for (int i =0; i<10;i++){
            System.out.print(doubleLink.get(i)+" ");
        }
        doubleLink.delect(0);
        doubleLink.delect(doubleLink.size-1);


        System.out.println("");
        for (int i =0; i<doubleLink.size;i++){
            System.out.print(doubleLink.get(i)+" ");
        }
    }
}
