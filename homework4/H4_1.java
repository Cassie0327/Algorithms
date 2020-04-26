package homework4;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.*;
/**
 * Name: Qian Cai
 * Created by Qian Cai on 2019/10/2.
 */
public class H4_1 {
    public static void printCode(HuffmanNode root, String s)
    {

        if (root.left == null && root.right == null && Character.isLetter(root.c)||root.c==' ') {

            System.out.println(root.c + ":" + s);

            return;
        }

        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    // main function
    public static void main(String[] args)
    {


        char[] charArray = { 'T', 'q', 'z', 'f', 'o', 'e','n','h','i','a','r','d','u','t','s',' '};
        int[] charfreq = { 1, 1, 1, 1, 1, 1,1,2,2,2,2,2,2,2,3,5};
        int n =charArray.length ;

        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new MyComparator());

        for (int i = 0; i < n; i++) {

            HuffmanNode hn = new HuffmanNode();

            hn.c = charArray[i];
            hn.data = charfreq[i];

            hn.left = null;
            hn.right = null;

            q.add(hn);
        }


        HuffmanNode root = null;

        while (q.size() > 1) {

            HuffmanNode x = q.peek();
            q.poll();


            HuffmanNode y = q.peek();
            q.poll();

            HuffmanNode f = new HuffmanNode();


            f.data = x.data + y.data;
            f.c = '-';

            f.left = x;

            f.right = y;

            root = f;

            q.add(f);
        }

        printCode(root, "");
    }
}
