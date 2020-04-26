package homework3;

import sun.security.util.BigInt;

import java.math.BigInteger;

/**
 * Name: Qian Cai
 * NU ID:001389278
 * Created by Qian Cai on 2019/9/20.
 */
public class H3_4 {
    public int hashcode(char []h)
    {
        int hashcode=0;


        for(int n=0;n<h.length;n++){
            hashcode+=(int)h[n]*multiply(h.length-1-n);
        }
        return hashcode;
    }
    public int multiply(int n)
    {
        int m=1;
        for(int i=0;i<n;i++)
        {
            m=31*m;
        }
        return m;
    }

    public static void main(String[] args) {
        H3_4 h = new H3_4();
        String s = "Hello to the World";
        char [] a= s.toCharArray();
        System.out.println(h.hashcode(a));
        System.out.println(s.hashCode());


    }
}
