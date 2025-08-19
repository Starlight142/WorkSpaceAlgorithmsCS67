package BinarySearchTree;

public class BST {
	private BSTNode root;
	public BST() {
		root = null;
	}
	public boolean isEmpty() {
		return root == null;
	}
  	public void insert(int data) {
        root = insert(root, data);
    }
	private BSTNode insert(BSTNode node, int data) {
		if(node == null)
			node = new BSTNode(data);
		else {
			if(data <= node.getData())
				node.left = insert(node.left, data);
			else
				node.right = insert(node.right, data);
		}
		return node;
	}
	public boolean search(int val) {
		return search(root, val);
	}
	private boolean search(BSTNode r, int val) {
		boolean found = false;
		while(r != null && !found) {
			int rval = r.getData();
			if(val < rval)
				r = r.getLeft();
			else if(val > rval)
				r = r.getRight();
			else {
				found = true;
				break;
			}
			found = search(r, rval);
		}
		return found;
	}
	public void delete(int d) {
		if(isEmpty())
			System.out.println("Tree Empty");
		else if(search(d) == false)
			System.out.println("Sorry " + d + "is not present.");
		else {
			root = delete(root, d);
			System.out.println(d + "delete from the Tree");
		}
	}
	@SuppressWarnings("unused")
	private BSTNode delete(BSTNode root, int k) {
		BSTNode p1, p2, n;
		if(root.getData() == k) {
			BSTNode fT, rT;
			fT = root.getLeft();
			rT = root.getRight();
			if(fT == null && rT == null)
				return null;
			else if(fT == null) {
				p1 = rT;
				return p1;
			}else if(rT == null) {
				p2 = fT;
				return p2;
			}else {
				p2 = rT;
				p1 = fT;
				while(p1.getLeft() != null) {
					p1 = p1.getLeft();
				}
				p1.setLeft(fT);
				return p2;
			}
		}
		if(k < root.getData()) {
			n = delete(root.getLeft(), k);
			root.setLeft(n);
		}else {
				n = delete(root.getRight(), k);
				root.setRight(n);	
		}
		return root;
	}
	public int countNodes() {
		return countNodes(root);
	}
	private  int countNodes(BSTNode r) {
		int l = 0;
		if(r == null)
			return l;
		else {
			l = 1;
			l += countNodes(r.getLeft());
			l += countNodes(r.getRight());
			return l;
		}
	}
	public void inorder() {
		inorder(root);
	}
	private void inorder(BSTNode r) {
		if(r != null) {
			inorder(r.getLeft());
			System.out.print(r.getData() + " ");
			inorder(r.getRight());
		}
	}
	public void postorder() {
		postorder(root);
	}
	private void postorder(BSTNode r) {
		if(r != null){
			postorder(r.getLeft());
			postorder(r.getRight());
			System.out.print(r.getData() + " ");
		}
	}
	public void preorder() {
		preorder(root);
	}
	private void preorder(BSTNode r){
		if(r != null){
			System.out.print(r.getData() + " ");
			preorder(r.getLeft());
			preorder(r.getRight());
		}
	}
}
