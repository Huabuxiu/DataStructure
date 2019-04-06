package 树.二叉树;

import 线性表.单向链表.LinkedList;

/**
 * @program: DataStructure
 * @description:
 * @author: Huabuxiu
 * @create: 2019-04-06 10:51
 **/
public class BinaryTree<E> {

        private  int size;
        private TreeNode<E> root;

        public TreeNode createtree(E[] elements){

            /**
            * @Description: 根据输入的数组创建二叉树，不存的在位置使用null来表示;
             *
             * 把树从根左右的顺序从0开始标号。那么第i号节点的左孩子的标号是2*i+1,右孩子是2*i+2。
            * @Param: [elements]
            * @return: 树.二叉树.BinaryTree<E>.TreeNode
            * @Author: Huabuxiu
            * @Date: 2019/4/6
            */
            if (elements.length==0){
                return null;
            }

            LinkedList<TreeNode> list = new LinkedList<>();
            for (int i =0;i<elements.length;i++){
                if (elements[i] != null) {
                    TreeNode node = new TreeNode(elements[i], null, null);
                    list.add(node); //存入链表
                }else {
                    list.add(null);
                }
            }

            size = list.getSize();
            root= list.get(0);

            if (list.getSize() > 0){
                for (int i =0; i<elements.length/2;i++){
                    if (elements[2*i+1]!=null){
                        //左节点
                        list.get(i).left= list.get(2*i+1);

                    }
                    if (elements[2*i+2] != null ){
                        list.get(i).right = list.get(2*i+2);
                    }
                }
            }

            return list.get(0);
        }


        /**
        * @Description: 前序遍历
        * @Param:
        * @return:
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */

        public void preprint(TreeNode root){
            if (root != null){
                System.out.println(root.getValue() +" ");
                preprint(root.left);
                preprint(root.right);
            }
        }


        /** 
        * @Description: 中序遍历 
        * @Param:  
        * @return:  
        * @Author: Huabuxiu 
        * @Date: 2019/4/6 
        */ 
    public void midprint(TreeNode root){
        if (root != null){
            preprint(root.left);
            System.out.println(root.getValue() +" ");
            preprint(root.right);
        }
    }

    /** 
    * @Description: 后序遍历 
    * @Param:  
    * @return:  
    * @Author: Huabuxiu 
    * @Date: 2019/4/6 
    */ 
    public void lastprint(TreeNode root){
        if (root != null){
            preprint(root.left);
            preprint(root.right);
            System.out.println(root.getValue() +" ");
        }
    }




    public class TreeNode<E>{
        E value;
        TreeNode left;
        TreeNode right;


        public TreeNode(E value) {
            this.value = value;
        }

        public TreeNode(E value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public E getValue() {
            return value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }
    }

}
