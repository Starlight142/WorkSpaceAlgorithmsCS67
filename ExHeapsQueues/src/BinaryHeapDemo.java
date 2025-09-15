import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

class BinaryHeap{
	private static final int d =2;
	private int heapSize;
	private int[] heap;
	
	public BinaryHeap(int capacity) {
		heapSize = 0;
		heap = new int[capacity + 1];
		Arrays.fill(heap, -1);
	}
	public boolean isEmpty() {
		return heapSize == 0;
	}
	public boolean isFull() {
		return heapSize == heap.length;
	}
	public void makeEmpty() {
		heapSize = 0;
	}
	private int parent(int i) {
		return (i - 1)/d;
	}
	private int kthChild(int i, int k) {
		return d* i + k;
	}
	private int minChild(int ind) {
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while((k <= d) && (pos < heapSize)) {
			if(heap[pos] < heap[bestChild])
				bestChild = pos;
			pos = kthChild(ind, k++);
		}
		return bestChild;
	}
	private void heapifyUp(int childInd) {
		int tmp = heap[childInd];
		while(childInd > 0 && tmp < heap[parent(childInd)]) {
			heap[childInd] = heap[parent(childInd)];
			childInd = parent(childInd);
		}
		heap[childInd] = tmp;
	}
	private void heapifyDown(int ind) {
		int child;
		int tmp = heap[ind];
		while(kthChild(ind, 1) < heapSize) {
			child = minChild(ind);
			if(heap[child] < tmp)
				heap[ind] = heap[child];
			else
				break;
			ind = child;
		}
		heap[ind] = tmp;
	}
	public void insert(int x) {
		if(isFull())
			throw new NoSuchElementException("Overflow Exeception");
		heap[heapSize++] = x;
		heapifyUp(heapSize - 1);
	}
	public int findmin() {
		if(isEmpty())
			throw new NoSuchElementException("Overflow Exeception");
		return heap[0];
	}
	public int delete(int ind) {
		if(isEmpty())
			throw new NoSuchElementException("Overflow Exeception");
		int keyItem = heap[ind];
		heap[ind] = heap[heapSize - 1];
		heapSize--;
		heapifyDown(ind);
		return keyItem;
	}
	public int deleteMin() {
		delete(0);
		int keyMin = heap[0];
		return keyMin;
	}
	public void printHeap() {
		System.out.print("\nHeap = ");
		for(int i = 0; i < heapSize; i++) 
			System.out.print(heap[i] + " ");
		System.out.println();
	}
}

public class BinaryHeapDemo {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Binary Heap Demo");
		System.out.print("Enter size of Biary heap: ");
		
		BinaryHeap bh = new BinaryHeap(scan.nextInt());
		
		char ch;
		do {
			System.out.println("\nBinary Heap Operations\n");
			System.out.println("1. insert");
			System.out.println("2. delete min");
			System.out.println("3. check fill");
			System.out.println("4. check empty");
			System.out.println("5. clear");
			
			int choice = scan.nextInt();
			switch(choice) {
			case 1: {
				try {
					System.out.println("Enter integer element to insert: ");
					bh.insert(scan.nextInt());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 2:
				try {
					System.out.println("Min Element = " + bh.deleteMin());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Full status = " + bh.isFull());
				break;
			case 4:
				System.out.println("Empty status = " + bh.isEmpty());
				break;
			case 5:
				bh.makeEmpty();
				System.out.println("Heap cleared\n");
				break;
			default:
				System.out.println("Wrong Entry\n");
				break;
			}
			
			bh.printHeap();
			System.out.println("\nDo you want to continue(Y or N)");
			ch = scan.next().charAt(0);
		}while(ch == 'y' || ch == 'Y');
	}

}
