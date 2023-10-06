import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** N퀸
 * 
 * N*N인 체스판 위에 놓은 퀸 N개를 서로 공격할수없게 두자
 * 퀸은 열또는 행당 하나씩 있을것임 당연함 상하좌우에 해당 안해야되니까~
 * 완전탐색으로 돌수있지만 완탐에서 중간에 불가능한 경로가오면 더이상 서치할필요X
 * =>즉 백트래킹 이용하여 효율적으로 풀수있다.
 */
public class Main {
	static int N, cnt=0;
	static boolean map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		solution(0);
		System.out.println(cnt);
	}
	private static void solution(int start) {
		if(start == N) {
			cnt++;
			return;
		}
		for(int j=0; j<N; j++) {
			map[start][j] = true;
			if(validCheck(start,j)) solution(start+1);
			map[start][j] = false;
		}
	}
	
	//가로, 세로, 대각선을 확인한다
	private static boolean validCheck(int x, int y) {
		//가로체크
		for(int i=0; i<N; i++) {
			if(i==y) continue;
			if(map[x][i]) return false;
		}
		
		//세로체크
		for(int i=0; i<N; i++) {
			if(i==x) continue;
			if(map[i][y]) return false;
		}
		
		//대각선체크.. 근데 자기보다 아래쪽엔 아예 안둔상태니까 윗쪽 대각선만 보면됨
		for(int i=1; i<=x; i++) {
			//오른위 즉 (3,3)이라면 (2,4) (1,5)...(n-1,n+1)
			if(y+(i)>=0 && y+(i)<N && map[x-(i)][y+(i)]) return false;
			//왼쪽위 즉 (3,3)이라면 (2,2) (1,1)... (n-1,n-1)
			if(y-(i)>=0 && y-(i)<N && map[x-(i)][y-(i)]) return false;
		}
		
		return true;
	}	

}