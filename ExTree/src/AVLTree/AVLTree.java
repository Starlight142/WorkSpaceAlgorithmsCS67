package AVLTree;

public class AVLTree {
	private AVLNode root;
	public AVLTree() {
		root = null;
	}
	public boolean isEmpty() {
		return root == null;
	}
	public void makeEmpty() {
		root = null;
	}
	public boolean search(int val) {
		return search(root, val);
	}
	private boolean search(AVLNode r, int val) {
		boolean found = false;
		while(r != null && !found) {
			int rval = r.data;
			if(val < rval)
				r = r.left;
			else if(val > rval)
				r = r.right;
			else {
				found = true;
				break;
			}
			found = search(r, val);
		}
		return found;
	}
	public int countNode() {
		return countNode(root);
	}
	private int countNode(AVLNode r) {
		int l;
		if(r == null)
			return 0;
		else {
			l = 1;
			l += countNode(r.left);
			l += countNode(r.right);
			return l;
		}
	}
	private int height(AVLNode t) {
		return t == null ? -1 : t.height;
	}
	private int max(int lhs, int rhs) {
		return lhs > rhs ? lhs : rhs;
	}
	private AVLNode rotateLeftChild(AVLNode k2) {
		AVLNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		k1.height = max(height(k1.left), k2.height) + 1;
		return k1;
	}
	private AVLNode rotateRightChild(AVLNode k1) {
		AVLNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.right), k1.height) + 1;
		return k2;
	}
	@SuppressWarnings("unused")
	private AVLNode doubleLeftChild(AVLNode k1) {
		k1.left = rotateRightChild(k1.left);
		return rotateLeftChild(k1);
	}
	@SuppressWarnings("unused")
	private AVLNode doubleRightChild(AVLNode k2) {
		k2.right = rotateRightChild(k2.right);
		return rotateRightChild(k2);
	}
	public void insert(int data) {
		root = insert(root, data);
	}
	private AVLNode insert(AVLNode r, int x) {
		if(r == null)
			r = new AVLNode(x);
		else if(x < r.data) {
			r.left = insert(r.left, x);
			if(height(r.left) - height(r.right) == 2) {
				if(x < r.left.data)
					r = rotateLeftChild(r);
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
			System.out.print(r.data + " ");
			inorder(r.right);
		}
	}
	public void preorder() {
		preorder(root);
	}
	private void preorder(AVLNode r) {
		if(r != null) {
			System.out.print(r.data + " ");
			preorder(r.left);
			preorder(r.right);
		}
	}
	public void postorder() {
		postorder(root);
	}
	private void postorder(AVLNode r) {
		if(r != null) {
			postorder(r.left);
			postorder(r.right);
			System.out.print(r.data + " ");
		}
	}
}
