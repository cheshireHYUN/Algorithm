import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 신기한 소수
 * 백트래킹을 통해 한자리씩 결정하고, 결정된 배열의 1,2,3,4까지 자리가 소수인지 isPrime()으로 판별한다.
 */
public class Main {
	static int N, selected[];
	static boolean checked[];
	static StringBuilder sb = new StringBuilder();
	static StringBuilder result = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		selected = new int[N];
		checked = new boolean[10];
		checked[0] = true;
		
		for(int i=2; i<10; i++) {
			sb.append(i);
			solution(1);
			sb.delete(sb.length()-1, sb.length());
		}
		System.out.println(result);
	}

	private static void solution(int cnt) {
		//소수가 아니면, 즉 false이면 더이상 구하지 않고 return한다.
		if(!isPrime()) return;
		
		if(cnt == N) {
			result.append(sb).append("\n");
			return;
		}
		
		for(int i=1; i<10; i++) { //0이 나오는순간 소수아니므로 1부터 돈다
			sb.append(i);
			solution(cnt+1);
			sb.delete(sb.length()-1, sb.length());
		}
	}

	private static boolean isPrime() { //현재 selected배열이 소수인지 판단하는 메소드
		int num = Integer.parseInt(sb.toString());
		// num이 소수인지 판단한다
		for(int i=2; i<=(int)Math.sqrt(num); i++) {
			if(num%i == 0) return false;
		}
		
		return true;
	}

}