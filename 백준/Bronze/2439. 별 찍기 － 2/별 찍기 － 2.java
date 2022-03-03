import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		N= N-1;
		for(int i=N; i>=0; i--) {
			for(int j=i; j>0; j--) {
				System.out.print(" ");
			}
			for(int j=i; (N>=j)&&(j>=i); j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		
		
	}
}