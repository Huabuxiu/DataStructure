package 线性表.双向链表;

/**
 * @program: DataStructure
 * @description:
 * @author: Huabuxiu
 * @create: 2019-04-06 01:11
 **/

public class DNode<E>{

    public E value;
    public DNode prev;
    public DNode next;

    public DNode(E value, DNode prev, DNode next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public DNode(E value) {
        this.value = value;
    }
}
