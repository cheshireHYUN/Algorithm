import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 마트
 * N개의 과자가 존재하고 ai그램이다
 * M그램을 초과하지 않는 최대의 2개과자 무게합?
 * 
 * 결국 nC2 조합문제겠지... if(cnt==2)일때 Math.max(,)
 */
public class Solution {
	static int[] snack;
	static int[] result = new int[2];
	static int answer, M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("src/algo0810/input_9229.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			answer = Integer.MIN_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			snack = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0, k=0; i<N; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp>=M) continue;
				snack[k++] = tmp;
			}
			sb.append('#').append(tc).append(' ');
			solution(0,0);
			if(answer == Integer.MIN_VALUE) sb.append(-1).append('\n');
			else sb.append(answer).append('\n');
		}

		System.out.println(sb);
		
	}
	private static void solution(int cnt, int index) {
		if(cnt == 2) {
			if(result[0]+result[1] <= M) answer = Math.max(answer, result[0]+result[1]);
			return;
		}
		
		for(int i=index; i<snack.length; i++) {
			if(snack[i]==0) return;
			result[cnt] = snack[i];
			solution(cnt+1, i+1);
		}
	}

}