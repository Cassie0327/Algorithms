package homework3;

/**
 *Name: Qian Cai
 * NU ID:001389278
 * Created by Qian Cai on 2019/9/20.
 */
public class TestLinkedListQueue {
    private Node first, last;
    private class Node
    { String item;
        Node next;}

    public boolean isEmpty()
    { return first == null; }

    public void enqueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
        first = last;
        else oldlast.next = last; }

        public String dequeue() {
        String item = first.item;
        first = first.next;
        if(isEmpty()) last=null;
        return item;
    }
    public static void main(String []args)
    {
        TestLinkedListQueue q=new TestLinkedListQueue();

    }
}
