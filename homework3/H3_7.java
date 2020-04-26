package homework3;

/**
 *Name: Qian Cai
 * NU ID:001389278
 * Created by Qian Cai on 2019/9/20.
 */
public class H3_7 {
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
        H3_7 a=new H3_7();
        char i[]=new char[100];
        i[0]='A';
        i[1]='*';
        i[2]='B';
        i[3]='/';
        i[4]='C';
        i[5]='+';
        i[6]='(';
        i[7]='D';
        i[8]='+';
        i[9]='E';
        i[10]='-';
        i[11]='(';
        i[12]='F';
        i[13]='*';
        i[14]='(';
        i[15]='G';
        i[16]='/';
        i[17]='H';
        i[18]=')';
        i[19]=')';
        i[20]=')';


        System.out.println();
        a.InfixToPostfix(i);

    }
}
