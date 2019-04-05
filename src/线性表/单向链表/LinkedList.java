package 线性表.单向链表;

/**
 * @program: DataStructure
 * @description:
 * @author: Huabuxiu
 * @create: 2019-04-05 17:10
 **/
public class LinkedList<E> {

    int size;
    private Node head;  //头结点
    private Node tail;  //尾节点

    public void add(E element){
        //加入节点
        if(head == null){
            head = tail = new Node(element);
            head.next = null;
        }else {
            tail.next = new Node(element);
            tail = tail.next;
        }
        size++;
    }

    public E get(int index) throws Exception {
        /*
        * @Description: 返回index位置的值
        * @Param: [index]
        * @return: E
        * @Author: Huabuxiu
        * @Date: 2019/4/5
        */
        if(index>= size || size == 0 || index < 0)
            throw new Exception("非法索引");

        Node node = head;
        for (int i =0 ; i<index;i++){
            node = node.next;
        }
        return node.elem;
    }


    public void delete(int index) throws Exception {
        /**
        * @Description: 删除index位置的节点
        * @Param: [index]
        * @return: boolean
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */
        if(index>= size || size == 0 || index <0)
            throw new Exception("非法索引");

        Node prenode = new Node(null,head);
        for (int i =0 ; i<index-1;i++){
            prenode = prenode.next;
        }
        if (prenode.next == head){//删除头节点
            head= head.next;    //删除链表头节点
        }
        if (prenode.next == tail){  //删除链表的尾节点
            tail = prenode;
        }
        prenode.next = prenode.next.next;
        size--;

    }

    //删除element元素的节点
    public boolean delete(E element){

        Node temp = new Node(null,head);    //删除链表头结点
        Node dump = temp;

        while (temp.next!=null){
            if (temp.next.elem.equals(element)){
                if (temp.next == tail) tail = temp; //删除尾节点
                temp.next = temp.next.next;
                if (temp.next == head)  head= dump.next;    //删除链表头节点
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }







    private class Node{

        /**
        * @Description: 单链表节点
        * @Param:
        * @return:
        * @Author: Huabuxiu
        * @Date: 2019/4/5
        */
        E elem; //值
        Node next;

        public Node(E elem) {
            this.elem = elem;
        }

        public Node(E elem, Node next) {
            this.elem = elem;
            this.next = next;
        }
    }


}
