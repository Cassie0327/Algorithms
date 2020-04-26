package homework3;

/**
 *Name: Qian Cai
 * NU ID:001389278
 * Created by Qian Cai on 2019/9/20.
 */
public class H3_5 {
    public int sumDigits(int n) {


        if (n < 0) {
            throw new IllegalArgumentException("The integer specified is negative");
        }
        else {
            return(n==0)?0:n%10+sumDigits(n/10);
        }

    }

    public static void main(String [] args)
    {

        H3_5 h=new H3_5();
        System.out.println(h.sumDigits(26497));

    }
}
