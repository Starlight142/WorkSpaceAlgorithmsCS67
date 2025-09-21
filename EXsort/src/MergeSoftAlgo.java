import java.util.Scanner;

public class MergeSoftAlgo {
	static void merge(int arr[], int low, int mid, int high) {
		int i, j, k;
		int[] c = new int[high - low + 1];
		k = 0;
		i = low;
		j = mid + 1;
		while(i <= mid && j <= high) {
			if(arr[i] <= arr[j])
				c[k++] = arr[i++];
			else
				c[k++] = arr[j++];
		}
		while(i <= mid) {
			c[k++] = arr[i++];
		}
		while(j <= high) {
			c[k++] = arr[j++];
		}
		k = 0;
		for(i=low; i<=high; i++)
			arr[i] = c[k++];
	}
	
	static void mergeSoft(int[] arr, int low, int high) {
		if(high - low + 1 > 1) {
			int mid = (low + high) / 2;
			mergeSoft(arr, low, mid); //a = i
			mergeSoft(arr, mid+1, high); //b = j
			merge(arr, low, mid, high);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("MergeSoft Test\n");
		int i, n;
		System.out.println("Enter number of integer elements");
		n = scan.nextInt();
		int[] arr = new int[n];
		System.out.println("Enter " + n + " integer elements");
		for(i=0; i<n; i++)
			arr[i] = scan.nextInt();
		
		mergeSoft(arr, 0, arr.length - 1);
		System.out.println("\nElement after softing ");
		for(i=0; i<n; i++)
			System.out.print(arr[i] + " ");
	}

}
