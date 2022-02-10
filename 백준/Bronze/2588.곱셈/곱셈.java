import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	
	int A = scanner.nextInt();
	int B = scanner.nextInt();

	int b1 = B/100;
	int b2 = (B-b1*100)/10;
	int b3 = (B-b1*100-b2*10);
	
	int C = A*b3;
	int D = A*b2;
	int E = A*b1;
	int F = C+D*10+E*100;
	
	System.out.println(C);
	System.out.println(D);
	System.out.println(E);
	System.out.println(F);

}}
