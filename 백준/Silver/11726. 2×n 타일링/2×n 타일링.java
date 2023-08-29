import java.util.Arrays;
import java.util.Scanner;

/** 2xn 타일링
  * 시작점은 1cm, 또는 2cm두개로 고정, 즉 남은 cm만큼 이전의 경우의수를 더해서 둘을 더해주면됨
  */
public class Main {
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		arr = new int[n+1];
		Arrays.fill(arr, -1);
		
		arr[1] = 1;
		if(n>1) arr[2] = 2;
		System.out.println(solution(n));
	}
	
	private static int solution(int n) {
		if(arr[n] != -1) return arr[n]%10007;
		//arr[n]은 처음을 1cm로 뒀을때 경우의 수 n-1일때와, 2로둔 n-2일때를 더해줘야함
		return arr[n] = (solution(n-1)+solution(n-2))%10007;
	}
}