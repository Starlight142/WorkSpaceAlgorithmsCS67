
import  java.util.Scanner;
public class ECsort {
    public static void sort(int[] arr) {
        int n = arr.length;
        int i, j, temp;
        for(i=1; i < n; i++) {
            j = i;
            temp = arr[i];
            while(j > 0 && temp < arr[j-i]){
                arr[j] = arr[j-1];
                j = j-1;
            }
            arr[j] = temp;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Selection Sort Test\n");
        System.out.println("Enter Number or integer elements: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.println("\nEnter " + n + " integer elements: ");
        for(int i=0; i<n; i++)
            arr[i] = scan.nextInt();
        sort(arr);
        System.out.println("\nElements after sorting");
        for(int i=0; i<n; i++)
            System.out.print(arr[i] + " ");
       
}
}
 