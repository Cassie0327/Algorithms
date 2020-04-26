package homework5;
import java.awt.image.BufferedImage;
import  java.util.*;
import javax.imageio.ImageIO;
import java.io.File;


/**
 Name: Qian Cai
 * Created by Qian Cai on 2019/10/7.
 */
public class H5_5 {

    //heapSort
    public void heapSort(double arr[])
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i=n-1; i>=0; i--)
        {

            double temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    void heapify(double arr[], int n, int i)
    {
        int smallest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;


        if (l < n && arr[l] < arr[smallest])
            smallest = l;


        if (r < n && arr[r] < arr[smallest])
            smallest= r;


        if (smallest != i)
        {
            double swap = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = swap;

            heapify(arr, n, smallest);
        }
    }

    //quickSort
    public  void quickSort(double[] arr,int low,int high){
        int i,j;
        double temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        temp = arr[low];

        while (i<j) {
            while (temp>=arr[j]&&i<j) {
                j--;
            }
            while (temp<=arr[i]&&i<j) {
                i++;
            }
            if (i<j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }

        }
        arr[low] = arr[i];
        arr[i] = temp;
        quickSort(arr, low, j-1);
        quickSort(arr, j+1, high);
    }

    //Mergesort
    public static double[] Mergesort(double[] a,int low,int high){
        int mid = (low+high)/2;
        if(low<high){
            Mergesort(a,low,mid);
            Mergesort(a,mid+1,high);
            merge(a,low,mid,high);
        }
        return a;
    }

    public static void merge(double[] a, int low, int mid, int high) {
        double[] temp = new double[high-low+1];
        int i= low;
        int j = mid+1;
        int k=0;
        while(i<=mid && j<=high){
            if(a[i]>a[j]){
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

    public double[] getIntensity(BufferedImage bi) {

        int[] rgb = new int[3];
        int width = bi.getWidth();
        int height = bi.getHeight();
        double[] in = new double[width * height];
        int n = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = bi.getRGB(i, j);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                in[n] = 0.2989 * rgb[0] + 0.5870 * rgb[1] + 0.1140 * rgb[2];
                n++;
            }

        }
        return in;
    }

    public static void main (String[]args)
    {
        H5_5 h=new H5_5();
        try{
            // File img = new File("Boston.jpeg");
           // BufferedImage bi = ImageIO.read(img);
            BufferedImage bi = ImageIO.read(h.getClass().getResource("Boston.jpeg"));
           double arr[]= h.getIntensity(bi);
            long startTime1 = System.currentTimeMillis();
            h.quickSort(arr, 0, arr.length-1);
            long endTime1 = System.currentTimeMillis();
            long startTime2 = System.currentTimeMillis();
            h.Mergesort(arr, 0, arr.length-1);
            long endTime2 = System.currentTimeMillis();
            long startTime3 = System.currentTimeMillis();
            h.heapSort(arr);
            long endTime3 = System.currentTimeMillis();
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
            System.out.println("The time of quickSort is "+ (endTime1 - startTime1));
            System.out.println("The time of mergeSort is "+ (endTime2 - startTime2));
            System.out.println("The time of heapSort is "+ (endTime3 - startTime3));

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
