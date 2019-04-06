package 线性表.队列;


import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class QueueTest {


    @Test
    public void isEmpyt() throws Exception {
        Queue<Integer> queue = new Queue<>();
        for (int i =0; i<10 ;i++){
            queue.Enqueue(i);
        }

        System.out.println("Size:"+queue.getSize());


        while (!queue.IsEmpyt()){
            System.out.println(queue.OutEnqueue());
        }

        queue.makeEmpty();
        if (queue.IsEmpyt()){
            System.out.println("队列空");
        }

    }
}
