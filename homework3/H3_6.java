package homework3;

/**
 *Name: Qian Cai
 * NU ID:001389278
 * Created by Qian Cai on 2019/9/20. */
public class H3_6 {
    public int H3_6(int n)
    {
        return count(n,0)+count(n,1);
    }
    public int count(int n,int lastDisgit) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        if (lastDisgit == 1) {
            return count(n - 1, 0) + count(n - 1, 1);
        } else {
            return count(n - 1, 1);
        }


    }

    public static void main(String[] args) {
        H3_6 h=new H3_6();

        System.out.println(h.H3_6(4));
    }
}