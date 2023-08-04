import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 로또
 * {1,...,49} 중에서 6개를 고른다.
 * 번호를 선택하는 전략은 49가지수 중 k(k>6)개의 수를 골라 집합을 만든 뒤 그수중 번호를 선택하는것임
 * 즉 k=8이 주어지면 8개의 수를 뽑는 모든 경우의수를 찾으라는것임.
 * 집합 S와 k가 주어졌을때 6개의 수를 고르는 모든 방법?
 * 
 * input : 첫번째 수는 k이고, 다음 k개의 수는 집합 s에 포함되는 수이다.s의 원소는 오름차순이며 입력의 마지막줄은 0
 * output : 테케마다 수를 고르는 모든 방법을 사전순으로 출력한다.
 */
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[] arr, picked = new int[6];
	static int k;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		
		while(true) {
			String line = br.readLine();
			if(line.equals("0")) {
				System.out.println(sb);
				break;
			}
			
			tk = new StringTokenizer(line);
			k = Integer.parseInt(tk.nextToken());
			arr = new int[k];
			for(int i=0; i<k; i++) {
				arr[i] = Integer.parseInt(tk.nextToken());
			}
			
			solution(0,0);
			sb.append("\n");
			
		}
	}

	// k개의 수중에서 6개의 수를 골라 조합을 만들어 출력한다.
	
	private static void solution(int cnt, int index) {
		if(cnt == 6) {
			for(int p : picked) sb.append(p+" ");
			sb.append("\n");
			return;
		}
		
		for(int i=index; i<k; i++) {
			picked[cnt] = arr[i];
			solution(cnt+1, i+1);
		}
	}

}