package 树.平衡二叉树;


import org.junit.Test;

public class AVLTreeTest {
    @Test
    public void preprint() {

        Integer[] treearray = new Integer[]{6, 3, 1, 8, 4 ,5};

        AVLTree<Integer> avlTree = new AVLTree<>();

        for (int i =0;i < treearray.length;i++){
            avlTree.insert(treearray[i]);
        }

        avlTree.preprint(avlTree.getRoot());
        System.out.println();
        avlTree.delete(1);
        avlTree.preprint(avlTree.getRoot());
    }
}
