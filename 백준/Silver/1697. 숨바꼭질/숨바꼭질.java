import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 숨바꼭질
 * 
 * 수빈이는 동생과 숨바꼭질중, 현재 N에있고 동생은 K에있다
 * 걷거나 숨간이동 할 수 있다.
 * 걷기 :: X일때 X-1또는 X+1로 이동함
 * 순간이동 :: 2*X로 이동함
 * 
 * 수빈이가 동생을 찾을 수 있는 가장 빠른시간?
 * 
 * DP로도 풀수있고
 * BFS로도 풀수있음
 * 
 */
public class Main {
	static int[] map;
	static boolean[] check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		map = new int[Math.max(N, K)+2]; //도착지+1에서 -1을 할 수도 있으니까.. 0~18 까지
		check = new boolean[Math.max(N, K)+2]; //도착지+1에서 -1을 할 수도 있으니까..
		System.out.println(bfs(N,K));
	}

	// 앞, 뒤 , 2배 거리를 bfs 탐색한다
	private static int bfs(int start, int end) {
		int level = 0;
		Queue<int[]> q = new ArrayDeque<int[]>();
		check[start] = true;
		q.offer(new int[] {start,0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] == end) {//도착지 찾으면 멈춤 
				level = cur[1];
				break; 
			}
			//세방향 집어넣기
			if(cur[0]+1>=0 && cur[0]+1<Math.max(end, start)+2 && !check[cur[0]+1]) {
				check[cur[0]+1]=true;
				q.offer(new int[] {cur[0]+1,cur[1]+1});
			}
			if(cur[0]-1>=0 && cur[0]-1<Math.max(end, start)+2 && !check[cur[0]-1]) {
				check[cur[0]-1] = true;
				q.offer(new int[] {cur[0]-1,cur[1]+1});
			}
			if(cur[0]*2>=0 && cur[0]*2<Math.max(end, start)+2 && !check[cur[0]*2]) {
				check[cur[0]*2] = true;
				q.offer(new int[] {cur[0]*2,cur[1]+1});
			}
			level++;
		}
		
		return level;
	}

}