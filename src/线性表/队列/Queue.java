package 线性表.队列;

import 线性表.单向链表.LinkedList;

/**
 * @program: DataStructure
 * @description:单向链表的队列
 * @author: Huabuxiu
 * @create: 2019-04-06 10:26
 **/
public class Queue<E> {
    private LinkedList.Node Front;
    private LinkedList.Node Rear;
    private int size;
    private LinkedList queueMemory;

    public Queue() {
        queueMemory = new LinkedList();
        size = queueMemory.getSize();
        Front= queueMemory.getHead();
        Rear = queueMemory.getTail();
    }


    public int getSize() {
        return size;
    }

    boolean IsEmpyt(){
        return queueMemory.isEmpty();
    }

    public void Enqueue(E value){
        /**
        * @Description: 在队列尾加入新元素
        * @Param: [value]
        * @return: void
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */
        queueMemory.add(value);
        Front = queueMemory.getHead();
        Rear = queueMemory.getTail();
        size++;
    }

    public E OutEnqueue() throws Exception {
        LinkedList.Node tempnode = Front;
        //删除表头
        queueMemory.delete(0);
        Front = queueMemory.getHead();
        Rear = queueMemory.getTail();
        size--;
        return (E) tempnode.getElem();
    }


    public void makeEmpty(){
        queueMemory = new LinkedList();
        size = queueMemory.getSize();
        Front= queueMemory.getHead();
        Rear = queueMemory.getTail();
    }
}
