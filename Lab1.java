import java.util.Arrays;

public class Lab1 {
    /** Sorting algorithms **/

    // Insertion sort.

    public static void insertionSort(int[] array) {
        for (int i=1;i<array.length;i++){
            int value=array[i];
            for (int j=i-1;j>=0;j--){
                if (array[j]>value){
                    array[j+1]=array[j];
                    array[j]=value;
                }
            }
        }
    }

    // Quicksort.

    public static void quickSort(int[] array) {
        if (array.length<2){return;}
        int pivot=partition(array, 0, array.length-1);
        quickSort(array,0,pivot-1);
        quickSort(array,pivot,array.length-1);
    }

    // Quicksort part of an array
    private static void quickSort(int[] array, int begin, int end) {
        if (end<=begin+1){
            return;
        }
        int pivot=partition(array,begin,end);
        int opposite=end;
        int front=begin;
        while(front<pivot||opposite>pivot){
            if (array[front]<array[pivot]){
                front++;
            }else{
                swap(array,front,opposite);
            }
            if (array[opposite]>array[pivot]){
                opposite++;
            }
        }
        quickSort(array,0,pivot-1);
        quickSort(array,pivot+1,array.length);
    }

    // Partition part of an array, and return the index where the pivot
    // ended up.
    private static int partition(int[] array, int begin, int end) {
        int pivot=median(array[begin],array[end],array[(begin+end)/2]);
        int index=begin;
        for (int i=begin;i<end;i++){
            if (array[i]<pivot){
                index++;
            }
        }
        return index;
    }

    // Swap two elements in an array
    private static void swap(int[] array, int i, int j) {
        int x = array[i];
        array[i] = array[j];
        array[j] = x;
    }

    // Mergesort.

    public static int[] mergeSort(int[] array) {
        return mergeSort(array,0,array.length);
    }

    // Mergesort part of an array
    private static int[] mergeSort(int[] array, int begin, int end) {
        if (end<=begin+1){
            return Arrays.copyOfRange(array,begin,end);
        }
        int[] left=mergeSort(array,begin,(begin+end)/2);
        int[] right=mergeSort(array,(begin+end/2),end);
        for (int i:left){
            System.out.println("Left"+i);
        }
        for (int i:right){
            System.out.println("Right"+i);
        }
        return merge(left,right);
    }

    // Merge two sorted arrays into one
    private static int[] merge(int[] left, int[] right) {
        int left1=0;
        int right1=0;
        int[] combined=new int[left.length+right.length];
        for (int i=0;i<left.length+right.length;i++){
            if (left1>=left.length){
                combined[i]=right[right1];
                right1++;
            }else if (right1>=right.length||left[left1]<right[right1]){
                combined[i]=left[left1];
                left1++;
            }else{
                combined[i]=right[right1];
                right1++;
            }
        }
        return combined;
    }
    private static int median(int a, int b, int c){
        if (a>=b&&a<=c||a>=c&&a<=b){
            return a;
        }else if(b>=a&&b<=c||b>=c&&b<=a){
            return b;
        }
        return c;
    }
}