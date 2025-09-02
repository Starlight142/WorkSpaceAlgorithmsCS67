package SLLChaining;

import java.util.Scanner;

public class ChainingDemo {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("HASH Table Demo\n");
		System.out.print("Enter Size: ");
		HashChaining htcsll = new HashChaining(scan.nextInt());
		char ch;
		do {
			System.out.println("\nHash Table Opertions\n");
			System.out.println("1. insert");
			System.out.println("2. remove");
			System.out.println("3. clear");
			System.out.println("4. size");
			System.out.println("5. check empty");
			int choice = scan.nextInt();
			
			switch(choice) {
				case 1:
					System.out.println("Enter integer element to insert.");
					htcsll.insert(scan.nextInt());
					break;
				case 2:
					System.out.println("Enter integer element to delete.");
					htcsll.remove(scan.nextInt());
					break;
				case 3:
					System.out.println("Hash Table Cleared\n");
					htcsll.makeEmpty();
					break;
				case 4:
					System.out.println("Size = " + htcsll.getSize());
					break;
				case 5:
					System.out.println("Empty status = " + htcsll.isEmpty());
					break;
				default:
					System.out.println("Wrong Entry\n");
					break;
			}
			htcsll.printHashTable();
			System.out.println("\nDo you want to continue(Y or N)");
			ch = scan.next().charAt(0);
		}while(ch == 'y' || ch == 'Y');
	}

}
