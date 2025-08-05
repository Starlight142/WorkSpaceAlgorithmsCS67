
import java.util.Random;
import java.util.Scanner;

public class QuickSort {
    static int partition(int[] arr1, int low , int high) {
        int j,temp, i = low+1;
        Random random = new Random();
        int x = random.nextInt(high-low)+low;
        temp = arr1[low];
        arr1[low] = arr1[x];
        arr1[x] = temp;
        for(j = low + 1; j <= high; j++){
            if (arr1[j] <= arr1[low] && j != 1) {
                temp = arr1[j];
                arr1[j] = arr1[i];
                arr1[i++] = temp;
            }else if(arr1[j] <= arr1[low]){
                i++;
            }
        }
        temp = arr1[i-1];
        arr1[i-1] = arr1[low];
        arr1[low] = temp;
        return i-1;
    }
    static void quickSort(int[] arr, int low, int high){
        if (low < high) {
            int mid = partition(arr, low, high);
            quickSort(arr, low, -1);
            quickSort(arr, mid+1, high);
        }
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Quick Sort Test\n");
        int n;
        System.out.println("Enter the number of integer elements: ");
        n = scan.nextInt();

        int arr[] = new int[n];
        System.out.print("\n Enter "+ n + " integer elements: ");
        for(int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        quickSort(arr, 0, arr.length-1);
        System.out.println("Element after sorting: ");
        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");

        }
    }
}
