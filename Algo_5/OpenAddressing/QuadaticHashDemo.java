import java.util.Scanner;

public class QuadaticHashDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Hash Table Demo\n");
		System.out.print("Enter Size: ");
		QuadraticHashTest5Montien qdht = new QuadraticHashTest5Montien(scan.nextInt());
		char ch;
		do {
			System.out.println("\nHash Table Opertions\n");
			System.out.println("1. insert");
			System.out.println("2. remove");
			System.out.println("3. get");
			System.out.println("4. size");
			System.out.println("5. clear");
			
			int choice = scan.nextInt();
			switch(choice) {
				case 1:
					System.out.println("Enter key and value");
					qdht.insert(scan.next(), scan.next());
					break;
				case 2:
					System.out.println("Enter key");
					qdht.remove(scan.next());
					break;
				case 3:
					System.out.println("Enter key");
					System.out.println("Val = " + qdht.get(scan.next()));
					break;
				case 4:
					System.out.println("Size: " + qdht.getSize());
					break;
				case 5:
					qdht.makeEmpty();
					System.out.println("Hash Table Cleared\n");
					break;
				default:
					System.out.println("Worng Entry\n");
					break;
			}
			qdht.printHash();
			System.out.print("\nDo you want to continue(Type y or n)");
			ch = scan.next().charAt(0);
		}while(ch == 'y' || ch == 'Y');
	}
}
