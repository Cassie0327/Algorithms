package homework2;

/**
 * Name: Qian Cai
 * NU ID:001389278
 * Created by Qian Cai on 2019/9/15. */
public class H2_2 {
    private static int count = 0;
    int a=100;
    int b=2;
    int n=0;

    public void constant()
    {
        n=a+b;
    }
    //running times: constant 1

    public void logN()
    {
        while (a>1)
        {
            a=a/2;
        }

    }
    // running times: logN
    public void N()
    {
        for (n=0;n<a;n++)
        {
            b+=n;
        }
    }
    //running times: N

    public static int[] sort(int[] a,int low,int high){
        int mid = (low+high)/2;
        if(low<high){
            sort(a,low,mid);
            sort(a,mid+1,high);
            merge(a,low,mid,high);
        }
        return a;
    }

    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int i= low;
        int j = mid+1;
        int k=0;
        while(i<=mid && j<=high){
            if(a[i]<a[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        }
        while(i<=mid){
            temp[k++] = a[i++];
        }

        while(j<=high){
            temp[k++] = a[j++];
        }
        for(int x=0;x<temp.length;x++){
            a[x+low] = temp[x];
        }
    }
    //running times: NlogN

    public void N_2()
    {for (int i = 0; i < a; i++)
        for (int j = 0; j < a; j++)
        {
            b=b+i+j;
        }

    }
    //running times: N^2
    public void N_3()
    {
        for (int i = 0; i < a; i++)
            for (int j = 0; j < a; j++)
                for (int k = 0; k < a; k++)
                {
                    b=i+j+k;
                }

    }
    //running times: N^3

        public int fib(int n) {
        if (n <= 1) return n;
        else return fib(n - 2) + fib(n - 1);

    }
    //running times: 2^N
    public static void main(String[] args)
    {

    }
}
