import java.util.Scanner;

class LeftHeapNode{
	int element, SValue;
	LeftHeapNode left, right;
	public LeftHeapNode(int ele) {
		this(ele, null, null);
	}
	public LeftHeapNode(int ele, LeftHeapNode left, LeftHeapNode right) {
		this.element = ele;
		this.left = left;
		this.right = right;
		this.SValue = 0;
	}
}

class LeftListHeap{
	private LeftHeapNode root;
	public LeftListHeap() {
		root = null;
	}
	public boolean isEmpty() {
		return root == null;
	}
	public void clear() {
		root = null;
	}
	public void merge(LeftListHeap rhs) {
		if(this == rhs)
			return;
		root = merge(root.right, rhs.root);
		rhs.root = null;
	}
	private LeftHeapNode merge(LeftHeapNode x, LeftHeapNode y) {
		if(x == null)
			return y;
		if(y == null)
			return x;
		if(x.element > y.element) {
			LeftHeapNode tmp = x;
			x = y;
			y = tmp;
		}
		x.right = merge(x.right, y);
		if(x.left == null) {
			x.left = x.right;
			x.right = null;
		}else {
			if(x.left.SValue < x.right.SValue) {
				LeftHeapNode tmp = x.left;
				x.left = x.right;
				x.right = tmp;
			}
			x.SValue = x.right.SValue + 1;
		}
		return x;
	}
	public void insert(int x) {
		root = merge(new LeftHeapNode(x), root);
	}
	public int deleteMin() {
		if(isEmpty())
			return -1;
		int minItem = root.element;
		root = merge(root.left, root.right);
		return minItem;
	}
	public void inorder() {
		inorder(root);
		System.out.println();
	}
	private void inorder(LeftHeapNode r) {
		if(r != null) {
			inorder(r.left);
			System.out.print(" " + r.element);
			inorder(r.right);
		}
	}
}

public class LeftHeapDemo {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("LeftHeap  Demo");
		
		LeftListHeap lh = new LeftListHeap();
		
		char ch;
		do {
			System.out.println("\nLeftListHeap Operations\n");
			System.out.println("1. insert");
			System.out.println("2. delete min");
			//System.out.println("3. check fill");
			System.out.println("4. check empty");
			System.out.println("5. clear");
			
			int choice = scan.nextInt();
			switch(choice) {
			case 1: {
				try {
					System.out.println("Enter integer element to insert: ");
					lh.insert(scan.nextInt());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 2:
				try {
					System.out.println("Min Element = " + lh.deleteMin());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Empty status = " + lh.isEmpty());
				break;
			case 4:
				lh.clear();
				System.out.println("Heap cleared\n");
				break;
			default:
				System.out.println("Wrong Entry\n");
				break;
			}
			
			System.out.print("\nInorder Traversal:");
			lh.inorder();
			System.out.println("\nDo you want to continue(Y or N)");
			ch = scan.next().charAt(0);
		}while(ch == 'y' || ch == 'Y');
	}
}
