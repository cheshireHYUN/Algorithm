import java.util.Arrays;
import java.util.Scanner;

/** 이친수
 * 0과1로 이뤄진 이진수중 특별한 성질 갖는걸 이친수라한다
 * 
 * 1. 이친수는 0으로 시작하지 않는다
 * 2. 이친수에서는 1이 두번 연속으로 나타나지 않는다.
 * 
 * ex) 1, 10, 101, 1000, 1001등
 * 
 * N자리 이친수의 갯수를 구해라
 */
public class Main {
	static long[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		//결과가 피보나치 수열이다.
		arr = new long[n+1];
		Arrays.fill(arr, -1);
		arr[1] = 1;
		if(n>1) arr[2] = 1;
		System.out.println(solution(n));
	}
	
	private static long solution(int n) {
		if(arr[n] != -1) return arr[n];
		return arr[n] = solution(n-2)+solution(n-1);
	}
	
}