package SLLChaining;

public class HashChaining {
	private SLLNode[] table;
	private int size;
	public HashChaining(int tableSize) {
		table = new SLLNode[nextPrime(tableSize)];
		size = 0;
	}
	private static boolean isPrime(int n) {
		if(n == 2 || n == 3)
			return true;
		if(n == 1 || n % 2 == 0)
			return false;
		for(int i = 3; i * i <= n; i += 2) {
			if(n % i == 0)
				return false;
		}
		return true;
	}
	private static int nextPrime(int n) {
		if(n % 2 == 0)
			n++;
		for(; !isPrime(n); n += 2);
		return n;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public void makeEmpty() {
		int l = table.length;
		table = new SLLNode[l];
		size = 0;
	}
	public int getSize() {
		return size;
	}
	private int myHash(Integer x) {
		int hashVal = x.hashCode();
		hashVal %= table.length;
		if(hashVal < 0)
			hashVal += table.length;
		return hashVal;
	}
	public void insert(int val) {
		size++;
		int pos = myHash(val);
		SLLNode nptr = new SLLNode(val);
		if(table[pos] == null)
			table[pos] = nptr;
		else {
			nptr.next = table[pos];
			table[pos] = nptr;
		}
	}
	public void remove(int val) {
		int pos = myHash(val);
		SLLNode start = table[pos];
		SLLNode end = start;
		if(start.data == val) {
			size--;
			table[pos] = start.next;
			return;
		}
		while(end.next != null && end.next.data != val) {
			end = end.next;
		}
		if(end.next == null) {
			System.out.println("\nElement not Found\n");
			return;
		}
		size--;
		if(end.next.next == null) {
			end.next = null;
			return;
		}
		end.next = end.next.next;
		table[pos] = start;
	}
	public void printHashTable() {
		System.out.println();
		for(int i = 0; i < table.length; i ++) {
			System.out.print("Bucket " + i + " : ");
			SLLNode start = table[i];
			while(start != null) {
				System.out.print(start.data + " ");
				start = start.next;
			}
			System.out.println();
		}
	}
}
