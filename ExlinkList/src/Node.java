public class Node {
	protected int data;
	protected Node link;
	public Node() {
		data = 0;
		link = null;
	}
	public Node(int data, Node n) {
		this.data = data;
		link = n;
	}
	public int getData() {
		return data;
	}
	public Node getlink() {
		return link;
	}
	public void setData(int data) {
		this.data = data;
	}
	public void setlink(Node n) {
		link = n;
	}
}