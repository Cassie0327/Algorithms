package homework4;
import java.nio.charset.Charset;
import java.util.*;

/**
 Name: Qian Cai
 * Created by Qian Cai on 2019/10/2.
 */
public class H4_2 {
    public void getStringBuilder(int n,int m)
    {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

            long startTime = System.currentTimeMillis();
           for(int j=0;j<m;j++) {
               StringBuilder sb = new StringBuilder(n);
               StringBuilder reverse=new StringBuilder(n);
               for (int i = 0; i < n; i++) {

                   int index = (int) (AlphaNumericString.length() * Math.random());

                   sb.append(AlphaNumericString.charAt(index));
               }
               for (int i = n - 1; i > 0; i--) {
                   reverse.append(sb.charAt(i));
               }
           }
        long endTime = System.currentTimeMillis();

        System.out.println(" "+ (endTime - startTime));





    }
    public void getString(int n,int m)
    {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
       // Random ran=new Random();

        long startTime = System.currentTimeMillis();

        for(int j=0;j<m;j++)
        {
            String s="";
            String reverse="";
            for (int i = 0; i < n; i++) {
                int index = (int)(AlphaNumericString.length() * Math.random());
                //int index=ran.nextInt(AlphaNumericString.length());
                s=s+(AlphaNumericString.charAt(index));
            }

            for(int i = n-1; i >0; i--)
            {

                reverse = reverse + s.charAt(i);

            }

        }
        long endTime = System.currentTimeMillis();

       System.out.println(" "+ (endTime - startTime));


    }

    public static void main(String [] args)
    {
    H4_2 h=new H4_2();
    h.getStringBuilder(400,150000);
   h.getString(400,150000);


    }

}
