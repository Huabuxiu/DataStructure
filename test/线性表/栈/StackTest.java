package 线性表.栈;


import org.junit.Test;

public class StackTest {

    @Test
    public void isEmpty() {
        Stack<Integer> stack = new Stack<>();

        for (int i =0;i<3;i++){
            stack.push(i);
        }
        System.out.println("size:"+stack.getSize());
        while (!stack.IsEmpty()){
            System.out.println(stack.pop());
        }
        stack.MakeEmpty();
        if (stack.IsEmpty()){
            System.out.println("空了");
        }
    }
}
