
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int A=0,B=0;
		A =scanner.nextInt();
		B =scanner.nextInt();
		if(A>B) {System.out.println(">");}
		else if(A<B){System.out.println("<");}
		else if(A==B){System.out.println("==");}
	}
}
