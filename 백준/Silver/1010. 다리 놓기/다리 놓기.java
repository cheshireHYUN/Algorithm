import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 다리놓기
 * 모든경우의수..

 * 그림 그려보면.. 조합 그자체이다
 * 따라서 최소 n의 갯수만큼은 m의 인덱스가 확보되어야함. 즉 m-n이라는 인덱스부터는 시작해야됨..
 * 1. 즉 구해야할건 n의 첫번째 점이 m의 첫번째부터 m-n까지중에 하나 선택할때
 * 2. 그럼 남은것중에 선택하는 경우의수.. 즉 팩토리얼연산?
 * 
 */
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int n,m,T, comb[][] = new int[30][30]; //N이 30까지고, 그중에 최대 30개까지 뽑을 수 있음..
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		setting(); //미리 셋팅해둔뒤 꺼내서 쓰기만하자
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			sb.append(comb[m][n]).append('\n');
		}
		System.out.println(sb);
	}
	
	private static void setting() {
		comb[1][0] = 1;
		comb[1][1] = 1;

		//행렬 규칙 mCn = m-1Cn-1+-m-1Cn
		for(int i=2; i<30; i++) {
			comb[i][0] = 1; //왼쪽은 다 채워줌
			for(int j=1; j<=i; j++) { //0열은 위에서 채워줬으니 1부터 채워줌
				comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
			}
		}
	}

}