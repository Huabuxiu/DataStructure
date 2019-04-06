package 树.二叉查找树;

/**
 * @program: DataStructure
 * @description: 二叉搜索树，左小右大
 * @author: Huabuxiu
 * @create: 2019-04-06 16:05
 **/
public class BSTree<E extends Comparable<E>> {      //泛型比较接口

    private BSTreeNode<E> root;

    public BSTreeNode<E> getRoot() {
        return root;
    }


   BSTreeNode<E> delete(E value){
        /**
        * @Description:  删除value节点
         * 1.叶子节点，直接删除
         * 2.有一个儿子的节点，调整父节点的指针指向它的儿子
         * 3. 有两个儿子，找到该节点的右子树的最小节点来替换它，然后删除右子树的最小节点
        * @Param:
        * @return: void
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */

        BSTreeNode<E> temp = find(value);
        if (temp == null)   //节点不存在
            return null;

        if (temp.left == null && temp.right == null){  //1.叶子节点
            if (temp.parent.left.equals(temp)){
                //是父节点的左儿子
                temp.parent.left =null;
                temp.parent = null;
                return temp;
            }
            if (temp.parent.right.equals(temp)){
                //是父节点的右儿子
                temp.parent.right =null;
                temp.parent = null;
                return temp;
            }
        }
        if (temp.right != null && temp.left != null){   //3.有两个儿子


            BSTreeNode<E> minrightnode = findmin(temp.right);
            BSTreeNode<E> replacenode = new BSTreeNode<E>(minrightnode.getValue());

            if (temp == root){  //删除根节点的情况
                replacenode.left = temp.left;
                replacenode.right = temp.right;
                root = replacenode;
            }else {
                if (temp.parent.right.equals(temp)){
                    //是父节点的右儿子
                    replacenode.parent = temp.parent;
                    temp.parent.right = replacenode;
                    replacenode.left = temp.left;
                    replacenode.right = temp.right;
                }
                if (temp.parent.left.equals(temp)){
                    //是父亲的左儿子
                    replacenode.parent = temp.parent;
                    temp.parent.left = replacenode;
                    replacenode.left = temp.left;
                    replacenode.right = temp.right;
                }
            }

            //去删除右子树最小的字符
            if (minrightnode.right == null)
            {
                minrightnode.parent.left =null;
                minrightnode.parent = null;
            }else {
                minrightnode.parent.left = minrightnode.right;
                minrightnode.right.parent = minrightnode.parent;
                minrightnode.parent = null;
                minrightnode.right = null;
            }
            return temp;
        }
        //一个节点
        if (temp.left != null){
            if (temp.parent.left.equals(temp)){
                temp.parent.left = temp.left;
                temp.left.parent = temp.parent;
                temp.left = null;
                temp.parent = null;
            }if (temp.parent.right.equals(temp)){
                temp.parent.right = temp.left;
                temp.left.parent = temp.parent;
                temp.left = null;
                temp.parent = null;
            }
            return temp;
        }else if (temp.right !=null ){
            if (temp.parent.left.equals(temp)){
                temp.parent.left = temp.right;
                temp.right.parent = temp.parent;
                temp.right = null;
                temp.parent = null;
            }if (temp.parent.right.equals(temp)) {
                temp.parent.right = temp.right;
                temp.right.parent = temp.parent;
                temp.right = null;
                temp.parent = null;
            }
            return temp;
        }
        return temp;
    }


    BSTreeNode<E> find(E value){
        
        /** 
        * @Description: 查找二叉树中值为value 的节点 
        * @Param: [value] 
        * @return: 树.二叉查找树.BSTree<E>.BSTreeNode<E> 
        * @Author: Huabuxiu 
        * @Date: 2019/4/6 
        */ 
        BSTreeNode<E> temp = root;
        if (temp == null){
            return null;
        }
        while (temp != null){
            int cmp = value.compareTo(temp.value);
            if (cmp < 0){
                temp = temp.left;
            }else if ( cmp > 0 ){
                temp = temp.right;
            }else
            {
                if (temp.value.compareTo(value) != 0){
                    return null;    //不存在
                }else {
                    return temp;
                }
            }
        }
        if (temp.value.compareTo(value) != 0){
            return null;
        }else {
            return temp;
        }
    }

    private BSTreeNode<E> findmin(BSTreeNode<E> node){
        /**
        * @Description: 返回最小的节点
        * @Param: []
        * @return: 树.二叉查找树.BSTree<E>.BSTreeNode<E>
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */

        BSTreeNode<E> temp = node;
        if (temp == null)
            return null;
        while (temp.left !=null){
            temp = temp.left;
        }
        return temp;
    }

    private BSTreeNode<E> findmax(BSTreeNode<E> node){

        /**
        * @Description: 返回最大的节点
        * @Param: []
        * @return: 树.二叉查找树.BSTree<E>.BSTreeNode<E>
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */
        BSTreeNode<E> temp = node;
        if (temp == null)
            return null;
        while (temp.right !=null){
            temp = temp.right;
        }
        return temp;
    }


    /**
     * @Description: 前序遍历 递归实现
     * @Param:
     * @return:
     * @Author: Huabuxiu
     * @Date: 2019/4/6
     */

    public void preprint(BSTreeNode root){
        if (root != null){
            System.out.print(root.getValue()+" ");
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
    public void midprint(BSTreeNode root){
        if (root != null){
            preprint(root.left);
            System.out.print(root.getValue()+" ");
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
    public void lastprint(BSTreeNode root){
        if (root != null){
            preprint(root.left);
            preprint(root.right);
            System.out.print(root.getValue()+" ");
        }
    }



    public void insert(E values){
        BSTreeNode<E> insertNode = new BSTreeNode<>(values);
        if (insertNode!=null){
            insert(insertNode);
        }
    }

    public void insert(BSTreeNode insertNode){

        /**
        * @Description: 插入一个节点
        * @Param: [insertNode]
        * @return: void
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */
        //插入头结点的情况
        if (root == null){
            root = insertNode;
            return;
        }


        int cmp;
        BSTreeNode<E> parent = null;    //存前驱节点
        BSTreeNode<E> temp = root;

        while (temp != null){
            parent = temp;
            cmp = insertNode.value.compareTo(temp.value);
            if (cmp < 0 ){
                temp = temp.left;
            }else if (cmp > 0 ){
                temp = temp.right;
            }
        }
        //设置parent值
        insertNode.parent = parent;

        cmp = insertNode.value.compareTo(parent.value);
        if (cmp < 0 && parent.left == null){
            parent.left = insertNode;
        }else if (cmp >0 && parent.right == null){
            parent.right = insertNode;
        }



    }



    public class BSTreeNode<E extends Comparable<E>> {
        private E value;
        private BSTreeNode<E> left;
        private BSTreeNode<E> right;
        private BSTreeNode<E> parent;   //父节点

        public BSTreeNode(E value) {
            this.value = value;
        }

        public BSTreeNode(E value, BSTreeNode<E> left, BSTreeNode<E> right, BSTreeNode<E> parent) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public E getValue() {
            return value;
        }

        public BSTreeNode<E> getLeft() {
            return left;
        }

        public BSTreeNode<E> getRight() {
            return right;
        }

        public BSTreeNode<E> getParent() {
            return parent;
        }

        @Override
        public String toString() {
            return "value=" + value ;
        }
    }
}
