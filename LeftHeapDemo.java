import java.util.Scanner;

class LeftHeapNode{
	int element, sValue;				// element = ค่าข้อมูล , SValue = ค่า null path length (ระยะทางสั้นสุดไปยังโหนดว่าง)
	LeftHeapNode left, right;			// ชี้ไปยังโหนดซ้ายและขวา
	public LeftHeapNode(int ele) {		// ตัวสร้างแบบมีค่าเดียว (สร้างโหนดใหม่)
		this(ele, null, null);
	}
	public LeftHeapNode(int ele, LeftHeapNode left, LeftHeapNode right) {
		this.element = ele; //correct values 	
		this.left = left;   // left node 
		this.right = right; // right node
		this.sValue = 0;	// value of null path length set to 0
	}
}

class LeftListHeap{					
	private LeftHeapNode root; 
	public LeftListHeap() {		// set values LeftListHeap root to null
		root = null;
	}
	public boolean isEmpty() {  // check if LeftListHeap is empty values = null
		return root == null;
	}
	public void clear() {		// use to clear status LeftListHeap to null
		root = null;
	}
	public void merge(LeftListHeap rhs) {	// merge LeftListHeap to rhp 
		if(this == rhs)						// if it same heap do not merge
			return;
		root = merge(root, rhs.root);		// merge root and rhs.root toghter
		rhs.root = null;					// set rhs.root to empty/null
	}
	private LeftHeapNode merge(LeftHeapNode x, LeftHeapNode y) { // merge method between x and y
		if(x == null) 				//if one of node is null need to return to another node
			return y;
		if(y == null)
			return x;
		if(x.element > y.element) {	//always give x node less than other node
			LeftHeapNode tmp = x;
			x = y;
			y = tmp;
		}
		x.right = merge(x.right, y); // merge x.right with x.right,y
		if(x.left == null) { // if empty values swap from left to right 
			x.left = x.right;
			x.right = null;
		}else {
			if(x.left.sValue < x.right.sValue) { // if x left have values less than x right need to swap location
				LeftHeapNode tmp = x.left;		 
				x.left = x.right;
				x.right = tmp;
			}
			x.sValue = x.right.sValue + 1; // update values of sValue 
		}
		return x; // return value x 
	}
	public void insert(int x) { // create public class to insert int x to heap
		root = merge(new LeftHeapNode(x), root); 	// create new node to merge LeftHeapNode into default heap
	}
	public int deleteMin() {	//delete minimum heap
		if(isEmpty())			//if heap is empty return -1
			return -1;
		int minItem = root.element; // keep minimum values
		root = merge(root.left, root.right); //merge left and right to default root
		return minItem; // return minimum values 
	}
	public void inorder() { // show values inorder traversal
		inorder(root);
		System.out.println();
	}
	private void inorder(LeftHeapNode r) { // method help for inorder traversal
		if(r != null) {
			inorder(r.left);
			System.out.print(r.element + " ");
			inorder(r.right);
			
		}
	}
}


public class LeftHeapDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("LeftHeap Demo\n"); // show name program
		
		LeftListHeap lh = new LeftListHeap(); // create LeftListHeap
		
		char ch; //use for control loop 
		do {
			System.out.println("\nLeftHeap Operations\n");
			System.out.println("1. insert"); // insert values
			System.out.println("2. delete min"); //delete minimum values
			//System.out.println("3. check full");
			System.out.println("4. check Empty"); // check heap is empty
			System.out.println("5. clear"); // clear heap
			
			int choice = scan.nextInt();
			
			switch(choice) {
				case 1:
					try {
						System.out.println("Enter integer element to insert"); // insert values
						lh.insert(scan.nextInt());
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						System.out.println("Min Element = " + lh.deleteMin()); //delete minimum values
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				
				case 3:
					System.out.println("Emptry status = " + lh.isEmpty()); // check heap is empty
					break;
				case 4:
					lh.clear();
					System.out.println("Heap Cleared\n");
				default:
					System.out.println("Wrong Entry\n");
					break;
			}
			System.out.print("\nInorder Traversal: ");  
			lh.inorder();
			System.out.print("\nDo you want to continue (Type y or n) \n");
			ch = scan.next().charAt(0);
		}while(ch == 'y' || ch == 'Y');
	}
}
