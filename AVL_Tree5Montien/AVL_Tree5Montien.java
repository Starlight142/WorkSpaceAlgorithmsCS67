import AVLTree.AVLNode; //import the AVLNode Class

public class AVL_Tree5Montien {
private AVLNode root; 
	public AVLTree() { // Declares root node of the tree
		root = null; //Initializes an empty tree by setting root to null
	}
	public boolean isEmpty() { // this code line use to check if tree is empty by verifying if root is null
		return root == null; 
	}
	public void makeEmpty() { // use to make tree null by setting root to null
		root = null;
	}
	public boolean search(int val) { // search method that start from root 
		return search(root, val);
	}
	private boolean search(AVLNode r, int val) {
		boolean found = false;
		while(r != null && !found) {
			int rval = r.data;
			if(val < rval)
				r = r.left;
			else if(val > rval) // logic search use to find traversal and compares left/right subtrees
				r = r.right;    // when value is found it set found = true and breaks it
			else {
				found = true;
				break;
			}
			found = search(r, val);
		}
		return found;
	}
	public int countNode() {    //count method use to count total in nodes
		return countNode(root); 
	}
	private int countNode(AVLNode r) {
		int l;
		if(r == null)
			return 0;
		else {                              //Recursive counting - returns 0 for null nodes
			l = 1;                          //otherwise counts current node plus left and right subtrees
			l += countNode(r.left);
			l += countNode(r.right);
			return l;
		}
	}
	private int height(AVLNode t) {
		return t == null ? -1 : t.height;   //Returns height of node (-1 for null nodes)
	}
	private int max(int lhs, int rhs) {
		return lhs > rhs ? lhs : rhs;       //Returns Maximum of two integers
	}
	private AVLNode rotateLeftChild(AVLNode k2) {   //Performs left rotation for AVL balancing
		AVLNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		k1.height = max(height(k1.left), k2.height) + 1;
		return k1;
	}
	private AVLNode rotateRightChild(AVLNode k1) { // Performs right rotation for AVL balancing similar to left rotation logic
		AVLNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.right), k1.height) + 1;
		return k2;
	}
	@SuppressWarnings("unused")
	private AVLNode doubleLeftChild(AVLNode k1) {       //Double rotation methods for complex balancing scenarios
		k1.left = rotateRightChild(k1.left);            //Double left - right rotation followed by left rotation
		return rotateLeftChild(k1);                     //Double right - left rotation followed by right rotation
	}
	@SuppressWarnings("unused")
	private AVLNode doubleRightChild(AVLNode k2) {      
		k2.right = rotateRightChild(k2.right);
		return rotateRightChild(k2);
	}
	public void insert(int data) { // Insert method start from root
		root = insert(root, data);
	}
	private AVLNode insert(AVLNode r, int x) {
		if(r == null)
			r = new AVLNode(x);
		else if(x < r.data) {                               //Recursive insert with AVL balancing
			r.left = insert(r.left, x);                     //If node is null, creates new node
			if(height(r.left) - height(r.right) == 2) {     //If value is smaller, inserts in left subtree and checks for left imbalance
				if(x < r.left.data)                         //If value is larger, inserts in right subtree and checks for right imbalance
					r = rotateLeftChild(r);                 //If value exists, just updates height
				else
					r = rotateRightChild(r);
			}
		}else if(x > r.data) {
			r.right = insert(r.right, x);
			if(height(r.right) - height(r.left) == 2) {
				if(x > r.right.data)
					r = rotateRightChild(r);
				else
					r = rotateLeftChild(r);
			}
		}else
			r.height = max(height(r.left), height(r.right)) + 1;
		return r;
	}
	public void inorder() {     
		inorder(root);
	}
	private void inorder(AVLNode r) {
		if(r != null) {
			inorder(r.left);
			System.out.print(r.data + " "); //Inorder traversal (Left-Root-Right)
			inorder(r.right);
		}
	}
	public void preorder() {
		preorder(root);
	}
    
	private void preorder(AVLNode r) {
		if(r != null) {
			System.out.print(r.data + " "); // Preorder traversal (Root-Left-Right)
			preorder(r.left);
			preorder(r.right);
		}
	}
	public void postorder() {
		postorder(root);
	}
	private void postorder(AVLNode r) {
		if(r != null) {                     //Postorder traversal (Left-Right-Root)
			postorder(r.left);
			postorder(r.right);
			System.out.print(r.data + " ");
		}
	}
}
