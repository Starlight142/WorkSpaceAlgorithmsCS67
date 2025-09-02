package AVLTree;

public class AVLNode {
	int data;
	AVLNode left, right;
	int height;
	public AVLNode() {
		left = null;
		right = null;
		data = 0;
		height = 0;
	}
	public AVLNode(int d) {
		left = null;
		right = null;
		data = d;
		height = 0;
	}
}
