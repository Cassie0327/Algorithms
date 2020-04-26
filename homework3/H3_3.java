package homework3;

/**
 *Name: Qian Cai
 * NU ID:001389278
 * Created by Qian Cai on 2019/9/20.
 */
public class H3_3 {
    private String[]s;

    private int head;
    private int tail;

    public H3_3(int capacity)
    { s = new String[capacity]; }

    public boolean isEmpty()
    { if(tail==head)
          return true;
    else return false;
    }

    public void enqueue(String item)
    {
        if(isEmpty())
        {
            head=0;
            s[head]=item;
            tail=1;
        }
        else{
            s[tail]=item;
            tail++;
        }
    }
    public String dequeue()
    {
        if(isEmpty())
            return null;
        else{
           String item=s[head];
           head++;
           return item;
        }


    }
    public static void main(String[] args)
    {
        H3_3 h=new H3_3(100);
        h.enqueue("Java");
        h.enqueue("is");
        h.enqueue("the");
        h.enqueue("best");
        h.enqueue("programming");
        h.enqueue("language");
        System.out.print(h.dequeue()+" "+h.dequeue()+" "+h.dequeue()+" "+h.dequeue()+" "+h.dequeue()+" "+h.dequeue());




    }

}
