package BinarySearchTree;

public class BSTNode {
	int data;
	BSTNode left, right;
		public BSTNode() {
			data = 0;
			left = null;
			right = null;
		}
		public BSTNode(int n) {
			data = n;
			left = null;
			right = null;
		}
		public void setLeft(BSTNode n) {
			left = n;
		}
		public void setRight(BSTNode n) {
			right = n;
		}
		public void setData(int d) {
			data = d;
}
		public BSTNode getLeft() {
			return left;
		}
		public BSTNode getRight() {
			return right;
		}
		public int getData() {
			return data;
		}
}
		
