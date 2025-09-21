
import java.util.Scanner;

public class InsertionSoftAlgo {
	public static void soft(int[] arr) {
		int n = arr.length;
		int i, j, temp;
		for(i=1; i<n; i++) {
			j = i;
			temp = arr[i];
			while(j>0 && temp < arr[j=1]) {
				arr[j] = arr[j-1];
				j = j - 1;
			}
			arr[j] = temp;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Selection Soft Test\n");
		System.out.print("Enter Number of integer elements: ");
		int n = scan.nextInt();
		int[] arr = new int[n];
		System.out.println("\nEnter " + n + " intger elements");
		for (int i=0; i<n; i++)
			arr[i] = scan.nextInt();
		soft(arr);
		System.out.println("\nElements after softing");
		for(int i=0; i<n; i++)
			System.out.print(arr[i] + " ");
	}
}

