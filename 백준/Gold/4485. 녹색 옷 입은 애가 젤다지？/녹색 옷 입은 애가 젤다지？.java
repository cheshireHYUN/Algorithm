import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 녹색 옷 입은 애가 젤다지?
 * 
 * NxN크기 동굴의 제일 왼쪽에 링크가 있다. [N-1][N-1], 즉 오른쪽 아래까지 이동해야한다.
 * 동굴의 각 칸마다 도둑루피(소지한 루피 감소)가 있는데, 해당 도둑루피 크기만큼 소지금이 줄어든다 
 * 잃는 금액을 최소화해서 이동해야하며 사방탐색한다.
 * output : 링크가 잃는 최소금액?
 * 
 * 풀이 : 왼쪽위에서 오른쪽아래로 사방탐색하며 이동한다. 가중치가 가장 작은거 구하면 되는거같은데?
 * 음의가중치는 없으므로 다익스트라 알고리즘 쓰면 되지 않나?
 * 
 * 다익스트라 알고리즘 : 한 정점에서 다른 모든 정점으로의 최단경로
 *
 */
public class Main {
	static int map[][], dis[][];
	static StringTokenizer st;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int cost;
		public Node(int x, int y, int c) {
			this.x = x;
			this.y = y;
			cost = c;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N==0) return;
			map = new int[N][N];

			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			dis = new int[N][N];
			for(int i=0; i<N; i++) Arrays.fill(dis[i], Integer.MAX_VALUE);
			System.out.println("Problem "+(tc++)+": "+solution());
		}
		
		
	}
	
	//다익스트라 알고리즘으로 최단거리를 구한다
	private static int solution() {
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.cost-b.cost);
		pq.offer(new Node(0,0,map[0][0])); //시작노드 넣기
		dis[0][0] = map[0][0];
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			for(int i=0; i<4; i++) {
				int nx = cur.x+dx[i];
				int ny = cur.y+dy[i];
				
				if(nx>=map.length || ny>=map.length || nx<0 || ny<0) continue;
				//범위안에있다면, dis배열을 업데이트할수있는지 확인한다. 즉 현재 curr노드비용+인접노드비용이 이전에 저장된 dis비용보다 작은지 확인
				if(dis[nx][ny] > cur.cost+map[nx][ny]) {
					dis[nx][ny] = cur.cost+map[nx][ny];
					pq.offer(new Node(nx,ny,dis[nx][ny]));
				}
			}
		}
		
		return dis[map.length-1][map.length-1];
	}
}