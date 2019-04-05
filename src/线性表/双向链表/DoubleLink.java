package 线性表.双向链表;

/**
 * @program: DataStructure
 * @description:
 * @author: Huabuxiu
 * @create: 2019-04-05 17:35
 **/
public class DoubleLink<E> {

    int size;
    private Node head;  //头结点
    private Node tail;  //尾节点

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0 ? true : false;
    }

    public void add(E value){
        if (head == null) head = new Node(value,null,null);
        tail.next = new Node(value);//加入节点
        tail.next.prev = tail;  //新节点前驱节点
        tail = tail.next;   //移动表尾指针
        tail.next = null;   //
        size++;
    }

    public E get(int index) {
        //返回第index位置的节点
        if (index > size || index < 0 || size==0)
            throw new IndexOutOfBoundsException("非法索引");
        if (index < size /2) {  //正向查找
            Node tempiont = head;
            for (int i = 0; i < index; i++) {
                tempiont = tempiont.next;
            }
            return tempiont.value;
        }else {
            Node tempiont = tail;
            for (int i=size; i> size-index;i--){
                tempiont = tempiont.prev;
            }
            return tempiont.value;
        }
    }

    public void delect(){

    }



    private class Node{

        public E value;
        public Node prev;
        public Node next;

        public Node(E value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public Node(E value) {
            this.value = value;
        }
    }

}
