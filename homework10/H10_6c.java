package homework10;

public class H10_6c {
    public void run(int[] A,int T)
    {
        int  numberOfThings = 0, currentTime = 0;
        for(int i = 0;i < A.length;++i)
        {
            currentTime += A[i];
            if(currentTime > T)
                break;
            numberOfThings++;
        }
        System.out.println("The numberOfThings is "+numberOfThings);
    }

    public static void main (String[] args)
    {
        int []A={1,2,3,4,5,6,7,8};
        int T=15;
        H10_6c h=new H10_6c();
        h.run(A,T);


    }
}
