package 树.平衡二叉树;

/**
 * @program: DataStructure
 * @description:
 * @author: Huabuxiu
 * @create: 2019-04-06 20:52
 **/
public class AVLTree<E extends Comparable<E>> {

    private avlTreeNode<E> root;

    public AVLTree() {
        root = null;
    }

    public avlTreeNode<E> getRoot() {
        return root;
    }

    private int height(avlTreeNode<E> node){
        if (node!=null)
            return node.height;
        return 0;
    }

    private int max(int a,int b){
        return  a>b ? a : b;
    }


    private avlTreeNode<E> find(avlTreeNode<E> node,E value){
        /**
        * @Description: 查找value节点
        * @Param: [node, value]
        * @return: 树.平衡二叉树.AVLTree<E>.avlTreeNode<E>
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */



        while (node!= null){
            int cmp = value.compareTo(node.getValue());
            if (cmp<0){
                node = node.left;
            }else if (cmp>0){
                node = node.right;
            }else
                return node;
        }
        return node;
    }

    private avlTreeNode<E> min(avlTreeNode<E> tree){
        /**
        * @Description: 查找最小节点
        * @Param: [tree]
        * @return: 树.平衡二叉树.AVLTree<E>.avlTreeNode<E>
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */
        if (tree == null){
            return null;
        }

        while (tree!= null){
            tree =  tree.left;
        }
        return tree;
    }

    public E minvalue(avlTreeNode<E> tree){
        if (min(tree)!=null)
            return min(tree).getValue();

        return null;
    }

    public E maxvalue(avlTreeNode<E> tree){
        if (max(tree)!=null)
            return max(tree).getValue();

        return null;
    }

    private avlTreeNode<E> max(avlTreeNode<E> tree){
        /**
        * @Description: 查找最大节点
        * @Param: [tree]
        * @return: 树.平衡二叉树.AVLTree<E>.avlTreeNode<E>
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */
        if (tree == null){
            return null;
        }

        while (tree!= null){
            tree =  tree.right;
        }
        return tree;
    }






    private avlTreeNode<E> insert(avlTreeNode<E> rootNode, E value){
        if (rootNode == null){
            rootNode = new avlTreeNode<E>(value,null,null);
        }else {
            int cmp = value.compareTo(rootNode.value);
            if (cmp<0){ //应该把value插入到左子树
                rootNode.left = insert(rootNode.left,value);
                if (height(rootNode.left) - height(rootNode.right) ==2){
                    if (value.compareTo(rootNode.left.value)<0){
                        rootNode = leftLeftRotation(rootNode);
                    }else {
                        rootNode = leftRightRotation(rootNode);
                    }
                }
            }else if (cmp > 0){ //插右子树
                rootNode.right = insert(rootNode.right,value);
                if (height(rootNode.right) - height(rootNode.left) ==2){
                    if (value.compareTo(rootNode.right.value)>0){
                        rootNode = rightRightRotation(rootNode);
                    }else
                        rootNode = rightLiftRotation(rootNode);
                }
            }
        }
        rootNode.height = max(height(rootNode.left),height(rootNode.right))+1;
        return rootNode;
    }


    public void insert(E value){
        root = insert(root,value);
    }

    private avlTreeNode<E> delete(avlTreeNode<E> tree,avlTreeNode<E> z){
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (tree==null || z==null)
            return null;

        int cmp = z.value.compareTo(tree.value);
        if (cmp < 0) {        // 待删除的节点在"tree的左子树"中
            tree.left = delete(tree.left, z);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.right) - height(tree.left) == 2) {
                avlTreeNode<E> r =  tree.right;
                if (height(r.left) > height(r.right))
                    tree = rightLiftRotation(tree);
                else
                    tree = rightRightRotation(tree);
            }
        } else if (cmp > 0) {    // 待删除的节点在"tree的右子树"中
            tree.right = delete(tree.right, z);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.left) - height(tree.right) == 2) {
                avlTreeNode<E> l =  tree.left;
                if (height(l.right) > height(l.left))
                    tree = leftRightRotation(tree);
                else
                    tree = leftLeftRotation(tree);
            }
        } else {    // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if ((tree.left!=null) && (tree.right!=null)) {
                if (height(tree.left) > height(tree.right)) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                    avlTreeNode<E> max = max(tree.left);
                    tree.value = max.value;
                    tree.left = delete(tree.left, max);
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                    avlTreeNode<E> min = min(tree.right);
                    tree.value = min.value;
                    tree.right = delete(tree.right, min);
                }
            } else {
                avlTreeNode<E> tmp = tree;
                tree = (tree.left!=null) ? tree.left : tree.right;
                tmp = null;
            }
        }

        return tree;


    }


    public void delete(E value){
        avlTreeNode<E> z;
        if ((z = find(root,value)) != null){
            root = delete(root,z);
        }
    }




    public void preprint(avlTreeNode root){
        if (root != null){
            System.out.print(root.getValue()+" ");
            preprint(root.left);
            preprint(root.right);
        }
    }




    private avlTreeNode<E> leftLeftRotation(avlTreeNode<E> k2){
    /**
    * @Description: LL单选
    * @Param: [k2]
    * @return: 树.平衡二叉树.AVLTree<E>.avlTreeNode<E>
    * @Author: Huabuxiu
    * @Date: 2019/4/6
    */
        avlTreeNode<E> k1;

        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left),height(k2.right))+1;
        k1.height = max(height(k1.left),k2.height)+1;

        return k1;
    }

    private avlTreeNode<E> rightRightRotation(avlTreeNode<E> k1){
        /**
        * @Description: RR单旋
        * @Param: [k1]
        * @return: 树.平衡二叉树.AVLTree<E>.avlTreeNode<E>
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */

        avlTreeNode<E> k2 = k1.right;

        k1.right = k2.left;
        k2.left = k1;

        k1.height = max(height(k1.left),height(k1.right))+1;
        k2.height = max(height(k2.right),k1.height)+1;

        return k2;
    }

    private avlTreeNode<E> leftRightRotation(avlTreeNode<E> k3){
        /**
        * @Description: LR双旋 ，先RR,在LL
        * @Param: [k3]
        * @return: 树.平衡二叉树.AVLTree<E>.avlTreeNode<E>
        * @Author: Huabuxiu
        * @Date: 2019/4/6
        */
        k3.left = rightRightRotation(k3.left);
        return leftLeftRotation(k3);

    }

    private avlTreeNode<E> rightLiftRotation(avlTreeNode<E> k1){
        /**
         * @Description: RL双旋,先LL，然后RR
         * @Param: [k3]
         * @return: 树.平衡二叉树.AVLTree<E>.avlTreeNode<E>
         * @Author: Huabuxiu
         * @Date: 2019/4/6
         */
        k1.right = leftLeftRotation(k1.right);
        return rightRightRotation(k1);
    }




    class avlTreeNode<E extends Comparable<E>>{
        E value;
        int height;
        avlTreeNode<E> left;
        avlTreeNode<E> right;


        public void setValue(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public int getHeight() {
            return height;
        }

        public avlTreeNode(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "value=" + value;
        }

        public avlTreeNode(E value, avlTreeNode<E> left, avlTreeNode<E> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


}
