import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**부분수열의 합
 * N개의 정수로 이루어진 수열이 있을때 크기가 양수인 부분수열 중 그 수열의 원소를 다 더한값이 S가 되는 경우의 수를 출력하라
 * input : 첫줄에 정수갯수인 N과 정수 S가 주어진다. 둘째줄에 N개의 정수 주어짐
 * output : 합이 S가 되는 부분수열의 갯수
 */
public class Main {
	static int N, S, arr[], result=0;
	static boolean[] isPicked;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		isPicked = new boolean[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		solution(0);
		if(S==0) System.out.println(result-1); 
		else System.out.println(result);//여기서 공집합 제외해주면 안되는 이유 : 만약 원하는수가 0이 아니라 자연수면 result에 포함 안됨ㅋㅋ 바보!
	}

	// 모든 부분집합을 만들어서 합이 S가 되는 부분수열의 갯수를 카운트.
	private static void solution(int cnt) { //cnt:몇개의 원소를 고려했는지 카운트함
		if(cnt == N) {
			int sum = 0;
			for(int i=0; i<N; i++) {
				if(isPicked[i]) sum+=arr[i];
			}
			
			if(sum==S) result++;
			return;
		}
		
		isPicked[cnt] = true;
		solution(cnt+1);
		isPicked[cnt] = false;
		solution(cnt+1);
		
	}
}