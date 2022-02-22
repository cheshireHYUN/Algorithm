import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		
		
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		
		for(int i=0; i<T; i++) {
			int var1 = scanner.nextInt();
			int var2 = scanner.nextInt();
			int result = var1+var2;
			System.out.println(result);
		}


	}
}