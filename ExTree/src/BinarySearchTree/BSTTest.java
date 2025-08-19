package BinarySearchTree;



import java.util.Scanner;

public class BSTTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BST bst = new BST();
        System.out.println("Binary Search Tree Test\n");
        char ch;
        do {
            System.out.print("\nBinary Search Tree Operations\n");
            System.out.println("1. insert");
            System.out.println("2. delete");
            System.out.println("3. search");
            System.out.println("4. count nodes");
            System.out.println("5. check empty");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter integer element to insert: ");
                    bst.insert(scan.nextInt());
                break;
                case 2:
                    System.out.print("Enter integer element to delete: ");
                    bst.delete(scan.nextInt());
                break;
                case 3:
                    System.out.print("Enter integer element to search: ");
                    System.out.println("Search =" + bst.search(scan.nextInt()));
                break;
                case 4:
                    System.out.println("Node =" + bst.countNodes());
                break;
                case 5:
                    System.out.println("Empty status = " + bst.isEmpty());
                    bst.insert(scan.nextInt());
                break;
                default:
                    System.out.println("Wrong Entry\n");
                
            }

            System.out.print("\nPreorder: ");
            bst.preorder();
            System.out.print("\nInorder: ");
            bst.insert(scan.nextInt());
            System.out.print("\nPostorder: ");
            bst.postorder();

            System.out.println("\nDo you want to continue? (Type y or n)\n");
            ch = scan.next().charAt(0);
        }while(ch == 'y' || ch == 'Y');
    }
}
