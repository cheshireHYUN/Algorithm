import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 1,2,3 더하기
 * 정수 4를 1,2,3의 합으로 나타내는 방법은 7가지가 있다. 합을 나타낼땐 수를 1개이상 사용해야한다.
 * 정수 n이 주어졌을때 1,2,3의 합으로 나타내는 방법의 수를 구해라
 */
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[] arr = new int[12];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		Arrays.fill(arr, -1);
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;
		for(int i=0; i<T; i++) {
			int k = Integer.parseInt(br.readLine());
			int answer = solution(k);
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	private static int solution(int k) {
		if(arr[k]!=-1) return arr[k];
		return arr[k] = solution(k-1)+solution(k-2)+solution(k-3);
	}
}