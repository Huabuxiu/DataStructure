package 树.二叉树;


import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class BinaryTreeTest {

    @Test
    public void preprint() {
        Integer[] treearray = new Integer[7];
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        for (int i=1;i<treearray.length+1;i++){
            treearray[i-1] = i;
        }
        binaryTree.preprint(binaryTree.createtree(treearray));
    }
}
