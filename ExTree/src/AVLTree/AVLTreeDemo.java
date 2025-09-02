package AVLTree;

import java.util.Scanner;

public class AVLTreeDemo {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		AVLTree avlt = new AVLTree();
		
		System.out.println("AVL Tree Test\n");
		char ch;
		do {
			System.out.println("\nAVL Tree Opertions\n");
			System.out.println("1. insert");
			System.out.println("2. search");
			System.out.println("3. count nodes");
			System.out.println("4. check empty");
			System.out.println("5. clear tree");
			
			int choice = scan.nextInt();
			switch(choice) {
				case 1:
					System.out.println("Enter integer element to insert");
					avlt.insert(scan.nextInt());
					break;
				case 2:
					System.out.println("Enter integer element to search");
					System.out.print("Search result = " + avlt.search(scan.nextInt()));
					break;
				case 3:
					System.out.println("\nNode = " + avlt.countNode());
					break;
				case 4:
					System.out.println("Empty status = " + avlt.isEmpty());
					break;
				case 5:
					System.out.println("\nTree Cleared.");
					avlt.makeEmpty();
					break;
				default:
					System.out.println("Worng Entry\n");
			}
			System.out.print("\nPreorder: ");
			avlt.preorder();
			System.out.print("\nInorder: ");
			avlt.inorder();
			System.out.print("\nPostorder: ");
			avlt.postorder();
			System.out.print("\nDo you want to countinue(Y or N)\n");
			ch = scan.next().charAt(0);
		}while(ch == 'y' || ch == 'Y');
	}
}
