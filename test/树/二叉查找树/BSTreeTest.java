package 树.二叉查找树;


import org.junit.Test;


public class BSTreeTest {


    @Test
    public void insert() {
        Integer[] treearray = new Integer[]{6, 2, 1, 8, 4,3,5,7};
        BSTree<Integer> bsTree = new BSTree<>();
        for (int i=0;i<treearray.length;i++){
            bsTree.insert(treearray[i]);
        }
        bsTree.preprint(bsTree.getRoot());
        System.out.println();
        bsTree.delete(6);

        bsTree.preprint(bsTree.getRoot());
        System.out.println();
        bsTree.delete(8);

        bsTree.preprint(bsTree.getRoot());

    }
}
