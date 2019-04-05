package 线性表.双向链表;

/**
 * @program: DataStructure
 * @description:
 * @author: Huabuxiu
 * @create: 2019-04-05 17:35
 **/
public class DoubleLink<E> {

    int size;
    private DNode head;  //头结点
    private DNode tail;  //尾节点

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0 ? true : false;
    }

    public void add(E value){
        if (head == null) {
            head = tail = new DNode(value,null,null);
        }else {
            tail.next = new DNode(value);//加入节点
            tail.next.prev = tail;  //新节点前驱节点
            tail = tail.next;   //移动表尾指针
            tail.next = null;   //
        }
        size++;
    }

    public E get(int index) {
        //返回第index位置的节点的值
        if (index > size || index < 0 || size==0)
            throw new IndexOutOfBoundsException("非法索引");
        if (index < size /2) {  //正向查找
            DNode tempiont = head;
            for (int i = 0; i < index; i++) {
                tempiont = tempiont.next;
            }
            return (E) tempiont.value;
        }else {
            DNode tempiont = tail;
            for (int j=0; j< size-index-1;j++){
                tempiont = tempiont.prev;
            }
            return (E) tempiont.value;
        }
    }

    public void inest(int index,E value){
        /** 
        * @Description: 在index后面插入一个节点
        * @Param: [index, value] 
        * @return: void 
        * @Author: Huabuxiu 
        * @Date: 2019/4/6 
        */
        DNode node = getNode(index);
        if (node == tail){//在表尾插入
            add(value);
        }else {
            DNode newnode = new DNode(value);
            newnode.prev = node;
            newnode.next = node.next;
            node.next = newnode;
            newnode.next.prev = newnode;
        }
    }

    public DNode getNode(int index) {
        //返回第index位置的节点的值
        if (index > size || index < 0 || size == 0)
            throw new IndexOutOfBoundsException("非法索引");
        if (index < size / 2) {  //正向查找
            DNode tempiont = head;
            for (int i = 0; i < index; i++) {
                tempiont = tempiont.next;
            }
            return tempiont;
        } else {
            DNode tempiont = tail;
            for (int j = 0; j < size - index - 1; j++) {
                tempiont = tempiont;
            }
            return tempiont;
        }
    }


    public void delect(int index){
        /**
        * @Description:删除index 位置处的节点
        * @Param: [index]
        * @return: void
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */
        if (index >= size || index < 0 || size==0)
            throw new IndexOutOfBoundsException("非法索引");
        DNode node = getNode(index);
        if (node == head){  //删头
            head = head.next;
            head.prev = null;
            size--;
            node = null;
            return;
        }else if(node == tail){ //删尾
            tail = tail.prev;
            tail.next = null;
            size--;
            node = null;
            return;
        }else {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node = null;
        size--;
        }
    }




}
