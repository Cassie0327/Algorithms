package homework1;

import java.util.Stack;

/**
 * Name: Qian Cai
 * Created by Qian Cai on 2019/9/10.
 */
public class H1_6 {
    public static void stackMethod()
    {
        Stack<String> stack=new Stack<String>();
        stack.push("time");
        stack.push("of ");
        stack.push("best");
        stack.push("the");
        stack.push("was");
        stack.push("It");

        if (stack.isEmpty())
        {
            System.out.println("The stack is Empty");
        }
        else
        {
            System.out.print(stack.pop());
            System.out.print(" "+stack.pop());
            System.out.print(" "+stack.pop());
            System.out.print(" "+stack.pop());
            System.out.print(" "+stack.pop());
            System.out.print(" "+stack.pop());
        }
    }



    public static void main(String[] args)
    {stackMethod();

    }
}
