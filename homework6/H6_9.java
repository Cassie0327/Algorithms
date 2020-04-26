package homework6;

import java.awt.image.BufferedImage;
import  java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
/**
 Name: Qian Cai
 NU ID:001389278

 * Created by Qian Cai on 2019/10/12.
 */
public class H6_9 {

    //Insertion Sort
    public void InsertionSort(double arr[]) {
        double n = arr.length;
        for (int i = 1; i < n; ++i) {
            double key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    //selection sort
    public void SelectionSort(double arr[]) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            int max_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] > arr[max_idx])
                    max_idx = j;

            double temp = arr[max_idx];
            arr[max_idx] = arr[i];
            arr[i] = temp;
        }
    }

    //quickSort
    public void quickSort(double[] arr, int low, int high) {
        int i, j;
        double temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        temp = arr[low];

        while (i < j) {
            while (temp >= arr[j] && i < j) {
                j--;
            }
            while (temp <= arr[i] && i < j) {
                i++;
            }
            if (i < j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }

        }
        arr[low] = arr[i];
        arr[i] = temp;
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }

    //Mergesort
    public double[] Mergesort(double[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            Mergesort(a, low, mid);
            Mergesort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
        return a;
    }

    public void merge(double[] a, int low, int mid, int high) {
        double[] temp = new double[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (a[i] > a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= high) {
            temp[k++] = a[j++];
        }
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
    }

    // TimSort
    static int RUN =32;

    public static void insertionSort(double[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            double temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] < temp && j >= left) {
                arr[j + 1] = arr[j];
                j = j - 1;

            }
            arr[j + 1] = temp;
        }
    }

    public void timSort(double[] arr, int m, int n) {
        int left = m;
        int right = n;
        int mid = (left + right) / 2;
        if (n - m < RUN){
           {
                insertionSort(arr, m,n);
                return;
            }
            }
            timSort(arr,left,mid);
            timSort(arr,mid+1,right);
            merge(arr, left, mid, right);
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
                 H6_9 h=new H6_9();
            try{
              //  File img = new File("Boston.jpeg");
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
                 h.InsertionSort(arr);
                long endTime3 = System.currentTimeMillis();
                long startTime4 = System.currentTimeMillis();
                h.SelectionSort(arr);
                long endTime4 = System.currentTimeMillis();
                long startTime5 = System.currentTimeMillis();
                h.timSort(arr,0,arr.length-1);
                long endTime5 = System.currentTimeMillis();

              for (int i = 0; i < arr.length; i++) {
                   System.out.println(arr[i]);
                }
                System.out.println("The time of quickSort is "+ (endTime1 - startTime1));
                System.out.println("The time of mergeSort is "+ (endTime2 - startTime2));
                System.out.println("The time of Insertion Sort is "+ (endTime3 - startTime3));
                System.out.println("The time of Selection Sort is "+ (endTime4 - startTime4));
                System.out.println("The time of TimSort is "+ (endTime5 - startTime5));

            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


