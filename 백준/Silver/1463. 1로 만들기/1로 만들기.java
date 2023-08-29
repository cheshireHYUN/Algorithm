import java.util.Arrays;
import java.util.Scanner;

/* 1로 만들기
 * 
 * 어떤 수 n이 되기 위한 방법을 역추적한다
 * 즉 10의 경우(f10)를 위해서는 
 * f(9)+1 (왜냐면 10에서 1을빼면 9니까)
 * f(5)+1 (10에서 2를 나누면 5니까)
 * f(10/3)+1 (자연수안되므로 거름)
 * 이때 4, 3이 나오니까 둘중 작은수인 3을 사용
 */
public class Main {
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		arr = new int[n+1];
		Arrays.fill(arr, -1);
		arr[1] = 0;
		if(n>1) arr[2] = 1;
		if(n>2) arr[3] = 1;
		System.out.println(solution(n));
		//System.out.println(Arrays.toString(arr));
	}
	private static int solution(int n) {
		if(arr[n] != -1) return arr[n];
		else {
			int tmp1=Integer.MAX_VALUE,tmp2=Integer.MAX_VALUE,tmp3=Integer.MAX_VALUE;
			if(n%2==0) tmp1 = solution(n/2)+1;
			if(n%3==0) tmp2 = solution(n/3)+1;
			tmp3 = solution(n-1)+1;
			
			return arr[n] = Math.min(Math.min(tmp1, tmp2), tmp3);
		}
	}
}