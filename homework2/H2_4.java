package homework2;
import java.util.*;

/**
 * Name: Qian Cai
 * NU ID:001389278
 * Created by Qian Cai on 2019/9/15.
 */
public class H2_4 {
    int size=100;
    int top=-1;
    char stack[]=new char[size];

    public void push(char item)
    {
        if (top>=size-1)
        {
            System.out.println("\nStack Overflow.");
        }
        else
        {
            top=top+1;
            stack[top]=item;
        }
    }
    public char pop() {
        char item;
        if (top < 0) {
            System.out.println("stack under flow: invalid infix expression");
            return 0;
        } else {
            item = stack[top];
            top = top - 1;
            return item;
        }
    }
    public int is_operator(char symbol)
    {
        if(symbol == '^' || symbol == '*' || symbol == '/' || symbol == '+' || symbol =='-')
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    public int precedence(char symbol)
    {
        if(symbol == '^')
        {
            return 3;
        }
        else if(symbol == '*' || symbol == '/')
        {
            return 2;
        }
        else if(symbol == '+' || symbol == '-')          /* lowest precedence */
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    public boolean is_alpha(char ch) {
        if (ch>='A' && ch<='Z'  ||  ch>='a' && ch<='z')
        {
            return true;
        }
        else
            return false;




    }


   public void InfixToPostfix(char infix_exp[]) {
       int i, j;
       char item;
       char x;
       char postfix_exp[] = new char[infix_exp.length];
       push('(');
       i = 0;
       j = 0;
       item = infix_exp[i];
       for(int n=0;n<99;n++)
       {
           while(infix_exp[n]=='\0')
           {
               infix_exp[n]=')';
               n=99;
           }
       }


       while (item !='\0') {

           if (item == '(') {
               push(item);
           }
           else if (Character.isDigit(item) || is_alpha(item)) {
               postfix_exp[j] = item;
               j++;
           }
           else if (is_operator(item) == 1) {
               x = pop();
               while (is_operator(x) == 1 && precedence(x) >= precedence(item)) {
                   postfix_exp[j] = x;
                   j++;
                   x = pop();
               }
              push(x);
               push(item);

           }
           else if (item == ')') {
               x = pop();
               while (x != '(') {
                   postfix_exp[j] = x;
                   j++;
                   x = pop();
               }
           } else {
               System.out.println("\nInvalid infix Expression.\n");

           }
           i++;
           item = infix_exp[i];
       }
       if(top>0)
       {
           System.out.println("\nInvalid infix Expression.\n");        /* the it is illegeal  symbol */

       }
       if(top>0)
       {
           System.out.println("\nInvalid infix Expression.\n");        /* the it is illegeal  symbol */

       }


       postfix_exp[j] = '\0';
       int n=0;
           while(postfix_exp[n]!='\0') {
               System.out.print(String.valueOf(postfix_exp[n]));
               n++;
           }
       }



    public static void main(String[] args)
    {
        H2_4 a=new H2_4();
        char n[]=new char[100];
        n[0]='(';
        n[1]='1';
        n[2]='+';
        n[3]='2';
        n[4]='*';
        n[5]='(';
        n[6]='2';
        n[7]='0';
        n[8]='/';
        n[9]='5';
        n[10]=')';
        n[11]=')';
        char m[]=new char[100];
        m[0]='(';
        m[1]='1';
        m[2]='+';
        m[3]='3';
        m[4]='+';
        m[5]='(';
        m[6]='(';
        m[7]='4';
        m[8]='/';
        m[9]='2';
        m[10]=')';
        m[11]='*';
        m[12]='(';
        m[13]='8';
        m[14]='*';
        m[15]='4';
        m[16]=')';
        m[17]=')';
        m[18]=')';
        char i[]=new char[100];
        i[0]='(';
        i[1]='4';
        i[2]='+';
        i[3]='8';
        i[4]=')';
        i[5]='*';
        i[6]='(';
        i[7]='6';
        i[8]='-';
        i[9]='5';
        i[10]=')';
        i[11]='/';
        i[12]='(';
        i[13]='(';
        i[14]='3';
        i[15]='-';
        i[16]='2';
        i[17]=')';
        i[18]='*';
        i[19]='(';
        i[20]='2';
        i[21]='+';
        i[22]='2';
        i[23]=')';
        i[24]=')';
        char j[]=new char[100];
        j[0]='(';
        j[1]='1';
        j[2]='+';
        j[3]='2';
        j[4]='-';
        j[5]='3';
        j[6]=')';

        a.InfixToPostfix(n);
        System.out.println();
        a.InfixToPostfix(m);
        System.out.println();
        a.InfixToPostfix(i);
        System.out.println();
        a.InfixToPostfix(j);

    }



}
