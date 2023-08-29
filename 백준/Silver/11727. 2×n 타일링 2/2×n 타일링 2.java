import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		arr = new int[n+1];
		Arrays.fill(arr, -1);
		arr[1] = 1;
		if(n>1) arr[2] = 3;
		
		System.out.println(solution(n));
		
	}
	private static int solution(int n) {
		if(arr[n]!=-1) return arr[n]%10007;
		return arr[n] = (solution(n-1)+solution(n-2)*2)%10007;
	}

}