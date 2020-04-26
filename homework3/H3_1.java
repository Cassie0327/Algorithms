package homework3;

/**
 *Name: Qian Cai
 * NU ID:001389278
 * Created by Qian Cai on 2019/9/20.
 */
public class H3_1 {

    public static long factorial(int n) {
        if(n<0)throw new RuntimeException("Underflow error in factorial");
        else if (n == 1) return 1;
        else return n * factorial(n-1);
    }
    public static int fib(int n) {
        if (n <= 1) return n;
        else return fib(n - 2) + fib(n - 1);
    }
    public static void moves(int n, boolean left) {

        if (n == 0) return;
        moves(n-1, !left);
        if (left) System.out.println(n + " left");
        else      System.out.println(n + " right");
        moves(n-1, !left);

    }

        public static void main(String [] args)
    {
        H3_1 h=new H3_1();
        System.out.println(h.factorial(6));
        System.out.println(h.fib(5));
        h.moves(5,true);

    }

}
