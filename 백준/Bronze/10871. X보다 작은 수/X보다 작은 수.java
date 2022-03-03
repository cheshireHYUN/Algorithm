import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int X =sc.nextInt();
		int[] arrays = new int[N];
		for(int i=0;i<N;i++) {
			arrays[i] = sc.nextInt();
		}
		
		for(int array : arrays) {
			if(array<X) {
				System.out.print(array+" ");
			}
			else {}
		}
		
	}

	
}