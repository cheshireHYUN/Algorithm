import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 규영이와 인영이의 카드게임
 * 1. 규영이의 카드 숫자를 제외한 인영이 카드배열을 생성한다. (boolean배열 이용할수있을듯)
 * 2. 인영이 카드배열의 순열을 구함
 * 3. 각 순열이 완성될때 승패를 계산하는 솔루션 실행
 *    최적화 ) 누적값이 1+..+18/2를 초과할경우 더이상 체크할 필요 없음!
 * 4. 누적된 승패값 출력
 */
public class Solution {
	static int[] myCard = new int[9], rivalCard = new int[9], select = new int[9];
	static boolean[] allCard, check;
	static int win, lose;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("src/algo0810/input_6808.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			allCard = new boolean[19];
			check = new boolean[9];
			win = 0;
			lose = 0;
			
			// 1. 카드배열 생성
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<9; i++) {
				myCard[i] = Integer.parseInt(st.nextToken());
				allCard[myCard[i]] = true;
			}
			for(int i=1, k=0; i<19; i++) {
				if(!allCard[i]) rivalCard[k++] = i;
			}
			// 2. rival의 가능한 모든 순열 구함
			getPermutation(0);
			sb.append("#"+tc+" "+lose+" "+win+"\n");
		}
		System.out.println(sb);
	}
	
	// rival의 순열 구한뒤 승패 비교함
	private static void getPermutation(int cnt) {
		if(cnt == 9) {
			// 3. 각 순열이 완성될때 승패를 계산하는 솔루션 실행
			whoIsWinner();
			return;
		}
		
		for(int i=0; i<9; i++) { //각 자리에 넣을 숫자를 구함 - 즉 지금까지 선택되지 않은 숫자를 구함
			if(check[i]) continue;
			check[i] = true;
			select[cnt] = rivalCard[i];
			getPermutation(cnt+1);
			check[i] = false; // 해당 순열 다 찾았으면 방금 넣었던거 다시 뺌
		}
	}

	// 라이벌의 순열인 select와 myCard를 이용하여 승자를 체크 (무승부 가능)
	private static void whoIsWinner() {
		int mySum = 0, rivalSum=0;
		for(int i=0; i<9; i++) {
			if(select[i] > myCard[i]) mySum += select[i]+myCard[i];
			else if(select[i] < myCard[i]) rivalSum += select[i]+myCard[i];
			
			// 최적화 : 1+..+18/2=178/2=89, 즉 한쪽의 총점이 89를 넘으면 그사람이 승리한거라 뒤는 계산 안해도됨
			if(mySum>89 || rivalSum>89) {
				if(mySum>89) win++;
				else lose++;
				return;
			}
		}
		
		if(mySum>rivalSum) win++;
		else if(mySum<rivalSum) lose++;
		return;
	}
}