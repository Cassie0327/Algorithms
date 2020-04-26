package homework1;

import javax.lang.model.element.Name;

/**
 * Name: Qian Cai
 * Created by Qian Cai on 2019/9/10.
 */
public class H1_7 {
    private Node first = null;
    private class Node
    {
        int Age;
        String Name;
        Node next;

    }
    public boolean isEmpty()
    { return first == null; }
    public void push(int Age, String Name)
    {
        Node oldfirst = first;
        first = new Node();
        first.Age = Age;
        first.Name= Name;
        first.next = oldfirst;
    }

    public String pop()
    {
        int Age=first.Age;
        String Name = first.Name;
        first = first.next;
        return Name;


}
    public static void main(String[] args)
    {
        H1_7 n=new H1_7();
        n.push(31, "Name1");
        n.push(24, "Name2");
        n.push(10, "Name3");
        n.push(44, "Name4");
        n.push(81, "Name5");
        n.pop();
        n.isEmpty();



    }
}
