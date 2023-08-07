import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 구간 합 구하기 4
 * 수 N개가 주어질때 i부터 j까지 합을 구하는 프로그램을 작성하라
 * 첫째줄에 수의개수 N과 합을 구하는 M이 주어진다.
 * 둘째줄에는 N개의 수가 주어진다. 100보다 작거나 같다
 * 셋째줄에는 M개의 줄에 합을 구하는 구간 i와 j가 주어진다
 * 
 * output : M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력하라
 * 
 * [누적합 알고리즘]
 * 누적합을 저장하는 배열을 따로 만들면 간단해진다.
 * S(n) = S(n-1) + a(n)
 * 즉, 2번째부터 4번째까지의 누적합을 구하려면
 * S(4) - S(2) = a(0)~a(4) - a(0)~a(1) = a(2)+a(3)+a(4)인 것이다.
 * 
 * arr = {1,2,3,4,5}
 * sumArr = {0,1,3,6,10,15}
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //수의 개수
		int M = Integer.parseInt(st.nextToken()); //합을 구하는 M번
		
		int[] arr = new int[N];
		int[] sumArr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		sumArr[0] = 0;
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sumArr[i+1] += arr[i]+sumArr[i];
		}
		
		StringBuilder sb = new StringBuilder();
		// i와 j 입력
		for(int t=0; t<M; t++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			sb.append(Math.abs(sumArr[j]-sumArr[i-1])+"\n"); //i~j라는것은 i의 앞까지의 누적합은 빼줘야한다는것
		}
		
		System.out.println(sb);
		
	}

}