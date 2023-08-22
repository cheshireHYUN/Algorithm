import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 적록색약
 * 
 * RGB구역이 이차원배열 위에 있고, 구역으로 나뉘어있다. 같은색상이 상하좌우로 인접하면 글자는 같은구역이다.
 * 적록색약의 경우 빨강과 초록을 같은구역으로 구분한다.
 * 
 * 이때 적록색약이 본 구역과 아닌사람이 본 구역의 수를 구하는 프로그램을 작성하라
 * 
 * 풀이 : DFS이용해서 모든 구역이 체크될때까지 각 알파벳으로 이뤄진 구역을 탐색한다. - 비적록색약
 * 		DFS를 이용하는데, RB를 같은 구역으로 생각하고 탐색한다. - 적록색약 
 * BFS도 물론 되죠..
 */
public class Main {
	static char[][] map;
	static boolean[][] check, check2;
	static int N, can=0, not=0, dx[] = {0,0,1,-1}, dy[]= {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		check = new boolean[N][N];
		check2 = new boolean[N][N];
		
		for(int i=0; i<N; i++) map[i] = br.readLine().toCharArray();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!check[i][j]) {
					check[i][j] = true;
					canDistinguish(i,j, map[i][j]);
					can++;
				}
				if(!check2[i][j]) {
					check2[i][j] = true;
					notDistinguish(i,j,map[i][j]);
					not++;
				}
			}
		}
		System.out.println(can+" "+not);
	}
	
	
	private static void canDistinguish(int x, int y, char c) {
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0 || ny<0 || nx>=N || ny>=N || map[nx][ny]!=c || check[nx][ny]) continue;
			check[nx][ny]=true;
			canDistinguish(nx, ny, c);
		}
	}

	private static void notDistinguish(int x, int y, char c) {
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0 || ny<0 || nx>=N || ny>=N || check2[nx][ny]) continue;
			if(c=='R' && map[nx][ny]=='B') continue;
			if(c=='G' && map[nx][ny]=='B') continue;
			if(c=='B' && map[nx][ny]!=c) continue;
			check2[nx][ny]=true;
			notDistinguish(nx, ny, c);
		}
	}


}