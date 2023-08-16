import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 암호만들기
 * 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며, 한개의 모음(a,e,i,o,u)과 최소 두개의 자음으로 구성됨
 * 또한 암호는 정렬되어 증가하는 순서로 배열되어있음
 * 
 * 새 시스템에서 암호로 사용했을법한 문자의 종류는 C가지가 있을때 이 알파벳을 입수한 민식, 영식은 암호를 추측한다.
 * C개 문자가 주어졌을때 가능성 있는 암호를 모두 구해라
 * 
 * input : L(알파벳개수) C(가능성있는 암호 갯수)
 * 
 * 풀이 : 즉,,, C개중에서 L개를 뽑는 조합(오름차순이니까)인데 모음을 반드시 하나만 포함해야함
 * (1)암호를 오름차순 정렬한 뒤 (2)조합을 구하고 (3)구해진 조합이 모음을 하나만 포함하는지 확인하여 출력.
 * 이때 (3)은 스도쿠처럼 모음 불린배열을 만들어서 true false 체크하면될듯?
 */
public class Main {
	static int L,C;
	static char[] arr, numbers;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); //알파벳 개수
		C = Integer.parseInt(st.nextToken()); //가능성 있는 암호의 개수
		
		arr = new char[C];
		numbers = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) arr[i] = st.nextToken().charAt(0);
		Arrays.sort(arr);
		
		solution(0,0);
		System.out.println(sb);
	}
	
	private static void solution(int cnt, int start) {
		if(cnt==L) {
			if(checkNumbers()) {
				for(int i=0; i<L; i++) sb.append(numbers[i]);
			}else return;
			
			sb.append('\n');
			return;
		}
		for(int i=start; i<C; i++) {
			numbers[cnt] = arr[i];
			solution(cnt+1,i+1);
		}
	}

	// 조건이 맞으면 true를 반환한다
	private static boolean checkNumbers() {
		check = new boolean[5];
		int cnt = 0, cnt2=0;
		for(char c : numbers) {
			if(c=='a') {
				if(!check[0]) {
					check[0] = true;
					cnt++;
				}
				else return false;
			}
			else if(c=='e') {
				if(!check[1]) {
					check[1] = true;
					cnt++;
				}
				else return false;
			}
			else if(c=='i') {
				if(!check[2]) {
					check[2] = true;
					cnt++;
				}
				else return false;
			}
			else if(c=='o') {
				if(!check[3]) {
					check[3] = true;
					cnt++;
				}
				else return false;
			}
			else if(c=='u') {
				if(!check[4]) {
					check[4] = true;
					cnt++;
				}
				else return false;
			}
			else {
				//자음
				cnt2++;
			}
		}
		if(cnt==0) return false;
		else if(cnt2<2) return false;
		return true;
	}
	
}