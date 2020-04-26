package homework5;

/**
 * Name: Qian Cai
 * Created by Qian Cai on 2019/10/7.
 */
public class H5_1 {
    int size = 9;
    int a[] = new int[size];
    int head=0;
    int tail=0;
    int flag=0;
    public boolean isEmpty() {
        if(head==tail&&flag==0)return true;
        else return false;

    }

    public boolean isFull() {
        if (head==tail&&flag==size-1) {
            return true;
        }
        return false;
    }

    public void enqueue(int n)
    {
        if(isFull())
        {
            System.out.println("The queue is full!");

        }
       else{
            a[tail]=n;
            tail++;
            flag++;
            while(tail>8)
            {
                tail=0;
            }

        }

    }

    public int dequeue()
    {
        int n=0;
        if(isEmpty())
        {
            System.out.println("The queue is Empty!");
        }
        else
        {
             n=a[head];
            head++;
            flag--;
            while(head>8)
            {
                head=0;
            }

        }
        return n;

    }
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("The queue is Empty!");
            System.out.println("The head is " + head);
            System.out.println("The tail is " + tail);
        } else {
            System.out.println("The head is " + head);
            System.out.println("The numbers in circular queue are");
            if (tail < head) {
                for (int j = 0; j < tail; j++) {
                    System.out.print(" " + a[j]);

                }
                for (int i = head; i < size; i++) {
                    System.out.print(" " + a[i]);

                }
            } else {

                for (int i = head; i < tail; i++) {
                    System.out.print(" " + a[i]);
                }
            }

                System.out.println();
                System.out.println("The tail is " + tail);
            }
            System.out.println("----------------------------------");
        }

    public static void main(String[] args)
    {
        H5_1 h=new H5_1();
        h.displayQueue();
        h.enqueue(7);
        h.enqueue(38);
        h.enqueue(3);
        h.enqueue(9);
        h.enqueue(82);
        h.enqueue(10);
        h.enqueue(31);
        h.enqueue(24);

        System.out.println("The status of step1 is");
        h.displayQueue();
        System.out.println("The status of step2 is");
        System.out.println("Removed element are "+h.dequeue()+","+h.dequeue()+","+h.dequeue());
        h.displayQueue();
        System.out.println("The status of step3 is");
        h.enqueue(7);
        h.enqueue(38);
        h.displayQueue();
        System.out.println("The status of step4 is");
        System.out.println("Removed element are "+h.dequeue()+","+h.dequeue()+","+h.dequeue()+","+h.dequeue()+","+h.dequeue()+","+h.dequeue()+","+h.dequeue()+","+h.dequeue());
        h.displayQueue();



    }


}
