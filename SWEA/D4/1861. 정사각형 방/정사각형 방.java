import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 정사각형방
 * 
 * N*N형태의 방이있다.
 * 위에서 i번째줄의 왼쪽에서 j번째방에서는 (i행j열) 1이상 N^2이하의 수가 적혀있으며, 이 숫자는 모든방이 서로 다르다
 * 당신이 어떤 방에있다면 상하좌우로 이동할 수 있다
 * 이동하려는 방이 존재해야하고, 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 1커야한다
 * 처음 어떤 수가 적힌 방에서 있어야 가장 많은 개수의방을 이동할 수 있는지 구해라.
 * 
 * 이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 그 중에서 적힌 수가 가장 작은 것을 출력한다.
 */


public class Solution {
	static int[][] map;
	static int[] dx = {-1,1,0,0}; //상하좌우
	static int[] dy = {0,0,-1,1};
	static int maxRoom, maxMove,N;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("src/algo0809/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<N; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			maxMove = 0;
			maxRoom = 0;
			
			// ㅠㅠ..걍 완탐으로 좌표마다 최장길이 탐색할거임 최적화 어렵다
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) solution(j,k,1);
			}
			
			sb.append('#').append(i).append(' ').append(maxRoom).append(' ').append(maxMove).append('\n');
		}
		System.out.println(sb);
	}

	private static int solution(int x, int y, int cnt) { //시작좌표(x,y)와 길이cnt(초기값1)
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx>=0 && ny>=0 && nx<N && ny<N
					&& map[x][y]+1 == map[nx][ny]) {
				cnt = solution(nx, ny, cnt+1);
			}
			
			// 더이상 나아갈 경로가 없으면(본인+1인 방이 주위에 없으면)
			if(maxMove < cnt) { //현재 경로가 가장 긴 경로이면 이동길이와 숫자 저장
				maxMove = cnt;
				maxRoom = map[x][y];
			} else if( maxMove==cnt && maxRoom > map[x][y]) { 
				//최대경로가 현재경로 길이와 같다면 가장 작은 room번호를 저장해줘야하는데,
				//재귀가 끝난후 시작점에 대해서도 이 if문이 작동하므로 map[x][y]를 넣는다.
				maxRoom = map[x][y];
			}
		}
		
		return cnt;
	}
}