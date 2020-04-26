package homework1;

/**
 *  Name: Qian Cai
 * Created by Qian Cai on 2019/9/10.
 */
public class H1_9 {

    private Object[][] s;
    private int N = 0;
    public H1_9(int capacity)
    {
        s = new Object[capacity][capacity];
    }
    public boolean isEmpty()
    {
        return N == 0;
    }
    public void push(int Age,String Name)
    {
        if(N==s.length) resize(2*s.length);
        s[N++][0] =Age;
        s[N++][1]=Name;
    }
    public void resize(int capacity){
        Object[][] copy = new Object[capacity][capacity];
        for(int i = 0;i<s.length;i++)
        for(int j=0;j<s.length;j++)
        {
            copy[i][j] = s[i][j];
        }
        s = copy;
    }
    public Object pop()
    {
        Object item = s[--N][0];
        s[N][0] = null;
        s[N][1]=null;
        if(N>0 && N == s.length/4) resize(s.length/2);
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
