import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 스네이크버드
 * 과일을 먹는 순서가 정해져있지 않으므로 오름차순 정렬해서 먹을수있는 가능성이 높은 먹이를 우선 탐색
 * 하나씩 먹으면서 다음 과일을 먹을 수 있는지 확인한다
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //과일개수
		int L = Integer.parseInt(st.nextToken()); //버드길이
		
		st = new StringTokenizer(br.readLine());
		int[] fruits = new int[N];
		for(int i=0; i<N; i++) fruits[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(fruits); //오름차순 정렬
		
		for(int f : fruits) {
			if(f<=L) L++;
			else break; //오름차순 정렬이므로 현재 버드가 먹을 수 없는 길이가 나온다면 이후는 탐색할 필요없음
		}
		
		System.out.println(L);
	}

}