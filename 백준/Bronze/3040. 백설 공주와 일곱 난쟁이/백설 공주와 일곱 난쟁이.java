import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

/* 백설공주와 일곱난쟁이
 * 
 * 9C7을 구한뒤 해당 조합의 합이 100인지 체크하여 리턴한다
 * 
 */
public class Main {
	static int[] arr, selected;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[9];
		selected = new int[7];
		for(int i=0; i<9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		solution(0,0);
		System.out.println(sb);
	}
	private static void solution(int cnt, int start) {// cnt = 현재 선택된 조합 갯수 //start=시작인덱스
		if(cnt == 7) { //7번째는 들어올일 없음
			int result = 0;
			for(int s : selected) {
				result+=s;
				if(result>100) return;
			}
			
			if(result==100) {
				for(int s : selected) sb.append(s).append("\n");
			}
			return;
		}
		
		for(int i=start; i<9; i++) { //이전에 선택된 숫자 이후의 숫자만 찾는다 = 조합
			selected[cnt] = arr[i];
			solution(cnt+1,i+1);
		}
	}
}