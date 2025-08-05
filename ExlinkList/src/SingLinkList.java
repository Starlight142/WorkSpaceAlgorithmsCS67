
import java.util.Scanner;

public class SingLinkList {
    public static void main(String[] args) {
        // Create a linked list with 6 nodes
        Scanner scan = new Scanner(System.in);
        System.out.println("Singly Link list test\n");
        LinkList list = new LinkList();
        char ch;
        do { 
            System.out.println("Enter intger element to insert: ");
            System.out.println("1. insert at begining");
            System.out.println("2. insert at End");
            System.out.println("3. insert at Position");
            System.out.println("4. delete at Position");
            System.out.println("5. check empty");
            System.out.println("6. get size");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter intger element to insert: ");
                    list.insertAtStart(scan.nextInt());
                    break;
                case 2:
                    System.out.println("Enter intger element to insert: ");
                    list.insertAtEnd(scan.nextInt());
                    break;
                case 3:
                    System.out.println("Enter intger element to insert: ");
                    int num = scan.nextInt();
                    System.out.println("Enter Position: ");
                    int pos = scan.nextInt();
                    if(pos < 1 || pos > list.getSize()){
                        System.out.println("Invalid position");
                    }else {
                        list.insertAtPos(num, pos);
                    }
                    break;
                case 4:
                    System.out.println("Enter Position: ");
                    int p = scan.nextInt();
                    if(p < 1 || p > list.getSize()){
                        System.out.println("Invalid position");
                    }else {
                        list.deleteAtPos(p);
                    }
                    break;
                case 5:
                    System.out.println("Empty status = " + list.isEmpty());
                    break;
                case 6:
                    System.out.println("Size = " + list.getSize() + "\n");
                    break;
                default:
                    System.out.println("Wrong Entry");
            }

            list.display();
            
            System.out.println("\nDo you want to continue(Type Y or N)?");
            ch = scan.next().charAt(0);
        } while (ch == 'y' || ch == 'Y');
    }
}
