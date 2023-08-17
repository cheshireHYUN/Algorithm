import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*  알파벳
 * R*C의 이차원배열에 대문자알파벳이 하나씩 적혀져있고, 서로다른 알파벳 길로 지나가려고함(사방탐색하면서)
 * 좌측상단에서 시작해서 최대 몇칸을 지날 수 있을지?(초기 좌표 포함함)
 * 
 * 풀이 : 사방탐색 DFS이며 가장 긴 경로를 찾으면 됨. 이때 체크배열은 A-Z까지 만들어주거나 해쉬셋 이용한다
 * 
 */
public class Main {
	static int R,C, max=0;
	static char[][] map;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static Set<Character> set = new HashSet<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int i=0; i<R; i++) map[i] = br.readLine().toCharArray();

		set.add(map[0][0]);
		dfs(0,0);
		System.out.println(max);
	}

	// 가장 긴 알파벳경로를 찾는 dfs
	private static void dfs(int x, int y) {
		max = Math.max(max, set.size()); //경로를 돌면서 가장 긴 방문사이즈를 업데이트 해둔다
		
		for(int k=0; k<4; k++) {
			int nx = x+dx[k];
			int ny = y+dy[k];
			
			if(nx>=0 && ny>=0 && nx<R && ny<C
					&& !set.contains(map[nx][ny])) {
				set.add(map[nx][ny]);
				dfs(nx,ny);
				set.remove(map[nx][ny]); //백트래킹
			}
		}
	}
	
}