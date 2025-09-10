public class Lab1 {
    /** Sorting algorithms **/

    // Insertion sort.

    public static void insertionSort(int[] array) {
        throw new UnsupportedOperationException();
    }

    // Quicksort.

    public static void quickSort(int[] array) {
        int pivot=partition(array, 0, array.length);
        quickSort(array,0,pivot-1);
        quickSort(array,pivot,array.length);
    }

    // Quicksort part of an array
    private static void quickSort(int[] array, int begin, int end) {
    }

    // Partition part of an array, and return the index where the pivot
    // ended up.
    private static int partition(int[] array, int begin, int end) {
        int pivot=median(begin,end,array[array.length/2]);
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
        throw new UnsupportedOperationException();
    }

    // Mergesort part of an array
    private static int[] mergeSort(int[] array, int begin, int end) {
        throw new UnsupportedOperationException();
    }

    // Merge two sorted arrays into one
    private static int[] merge(int[] left, int[] right) {
        throw new UnsupportedOperationException();
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