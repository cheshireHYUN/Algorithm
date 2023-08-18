import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** DFS와 BFS
 * 방문할 수 있는 정점이 여러개인경우 번호가 작은것을 먼저 방문하고,
 * 더이상 방문할 수 있는 점이 없으면 종료
 * 
 * 저번엔 인접리스트로 풀었으니 이번엔 인접행렬로 풀어보자..
 */
public class Main {
	static int[] dx = {};
	static int[] dy = {};
	static int[][] map;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //정점 갯수
		int M = Integer.parseInt(st.nextToken()); //간선 갯수
		int V = Integer.parseInt(st.nextToken()); //시작 정점 번호
		map = new int[N+1][N+1];
		check = new boolean[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
			map[y][x] = 1;
		}
		
		check[V]=true;
		dfs(V);
		sb.append('\n');
		
		Arrays.fill(check, false); //체크배열 초기화
		bfs(V);
		System.out.println(sb);
	}
	
	// v와 연결된 노드 찾기, 즉 map에서 v행의 값이 1인곳 찾기
	private static void dfs(int v) {
		sb.append(v).append(' ');
		for(int i=1; i<map[0].length; i++) {
			if(map[v][i]==1 && !check[i]) {
				check[i] = true;
				dfs(i);
			}
		}
	}
	
	private static void bfs(int v) {
		check[v]=true;
		Queue<Integer> q = new LinkedList<>();
		q.offer(v);
		while(!q.isEmpty()){
			int x = q.poll();
			sb.append(x).append(' ');
			for(int i=0; i<map[0].length; i++) {
				if(map[x][i]==1 && !check[i]) {
					check[i] = true;
					q.offer(i);
				}
			}
		}
	}
}