package 线性表.栈;

import 线性表.双向链表.DNode;
import 线性表.双向链表.DoubleLink;

/**
 * @program: DataStructure
 * @description:使用双向链表实现的栈
 * @author: Huabuxiu
 * @create: 2019-04-06 09:58
 **/
public class Stack<E> {
    private int size;
    private DNode top;
    private DoubleLink stackMemory;

    public Stack() {
        stackMemory = new DoubleLink<E>();
        top = stackMemory.getHead();
        size = stackMemory.getSize();
    }

    boolean IsEmpty(){
        return stackMemory.isEmpty();
    }

    public void push(E value){
        stackMemory.add(value);
        top = stackMemory.getTail();
        size++;
    }


    public E pop(){
        DNode tempnode =  top;
        stackMemory.delect(stackMemory.getSize()-1);
        top = stackMemory.getTail();
        size--;
        return (E) tempnode.value;
    }

    public int getSize() {
        return size;
    }

    void MakeEmpty(){
        stackMemory = new DoubleLink<E>();
        top = stackMemory.getHead();
        size = stackMemory.getSize();
    }

}
