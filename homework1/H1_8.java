package homework1;

/**
 * Name: Qian Cai
 * Created by Qian Cai on 2019/9/10.
 */
public class H1_8 {
    private Object[][] s;
    private int N = 0;
    public H1_8(int capacity)
    {
        s = new Object[capacity][capacity];
    }
    public boolean isEmpty()
    {
        return N == 0;
    }
    public void push(int Age,String Name)
    {
        s[N++][0] =Age;
        s[N++][1]=Name;
    }
    public Object pop()
    {
        Object item=s[--N][0];
        s[N][0]=null;
        s[N][1]=null;
        return item;
    }
    public static void main(String[] args)
    {
        H1_8 n=new H1_8(10);
        n.push(31, "Name1");
        n.push(24, "Name2");
        n.push(10, "Name3");
        n.push(44, "Name4");
        n.push(81, "Name5");
        n.isEmpty();
        n.pop();
    }
}
