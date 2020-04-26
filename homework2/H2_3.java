package homework2;

/**
 * Name: Qian Cai
 * NU ID:001389278
 * Created by Qian Cai on 2019/9/15.
 */
public class H2_3 {
    int[]a=new int[10];
    int N=a.length;
    public void count() {
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        count++;
    }
}
