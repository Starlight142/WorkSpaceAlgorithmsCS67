import java.util.Scanner;
import java.util.Stack;

public class PostFixTest {

	static int evaluatePostFix(String exp) {
		Stack<Integer> stack = new Stack<>(); //Stack
		
		for(int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			if(Character.isDigit(c))
			stack.push(c - '0');
			else {
				int val1 = stack.pop();
				int val2 = stack.pop();
				
				switch (c) {
				case '+':
					stack.push(val2 + val1);
					break;
				case '-':
					stack.push(val2 - val1);
					break;
				case '*':
					stack.push(val2 * val1);
					break;
				case '/':
					stack.push(val2 / val1);
					break;
				}
			}
		}
		return stack.pop();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String exp;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("The Postfix Expression is ");
		exp = scan.nextLine();
		System.out.println("Postfix Evaluation: " + evaluatePostFix(exp));
	}
}
