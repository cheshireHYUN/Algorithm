import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/** 탈출
 * 
 * 지도는 R행 C열로 이루어져있고 
 * 빈곳은 . 
 * 물있는곳은 * 
 * 돌은 X 
 * 목적지인비버굴은 D 
 * 출발지인고슴도치는 S
 *
 * 고슴도치는 사방탐색을 하며 물도 매분 인접한(한변이라도 공유) 빈칸으로 확장된다.
 * 고슴도치는 물과 돌 통과 X, 물이 찰 예정인 칸도 X
 * 물은 돌과 비버소굴로 이동X
 * 
 * output : 도치가 안전하게 비버굴로 이동하기위해 필요한 최소시간?
 * 
 * 풀이 : 레벨별 BFS탐색
 * 
 * 1분 시작 -> 물 먼저 번지게함 -> 고슴도치갈곳 큐에 넣음
 * 2분시작 -> 물 먼저 번지게함 -> 고슴도치 갈곳 큐에넣음
 * -> 그러다가 D에 도착하면 초를 답으로 프린트하면 됨
 * 
 * 물 번지게하는거 메소드로 따로 빼야할듯.. 현재 map에서 물 번지게 하는거지 이때 주의할점은
 * (1) 사방탐색(한변이라도 공유하면 물 차는거니까.. 맞겠지?) (2) 돌과 비버소굴엔 가지못함
 * 
 * 풀이2 : 물번지게하는거 미리 해서 맵에 숫자로 저장해두기
 * 그리고 고슴도치 이동하면서 맵의 숫자보다 작은 턴에만 이동..
 * 
 */
public class Main {
	static char[][] map;
	static Node start;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		
		
		for(int i=0; i<r; i++) {
			String line = br.readLine();
			for(int j=0; j<c; j++) {
				char k = line.charAt(j);
				map[i][j] = k;
				if(k=='S') start = new Node(i,j);
			}
		}
		
		bfs();
		
	}
	
	// 레벨별로 탐색하며 (1)물퍼트리기 (2)고슴도치 경로 저장하기를 반복한다
	// 도착지에 도착하면 그 값을 출력, 만약 큐를 다 돌아도 도착하지 못하면 KAKTUS를 출력
	// 고슴도치는 물(*)과 돌(X)을 통과하지 못하며 물은 돌(X)과 비버소굴(D)을 통과하지 못한다.
	private static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(start);
		int time = 0;
		boolean[][] check = new boolean[map.length][map[0].length];
		check[start.x][start.y] = true;
		while(!q.isEmpty()) {
			fillWater();
			int size = q.size();
			time++;
			while(size-->0) {	
				Node node = q.poll();
				for(int i=0; i<4; i++) {
					int nx = node.x+dx[i];
					int ny = node.y+dy[i];
					if(nx>=0 && ny>=0 && nx<map.length && ny<map[0].length 
							&& !check[nx][ny] && map[nx][ny]!='*' && map[nx][ny]!='X') {
						//만약 도착지라면 멈춘다
						if(map[nx][ny]=='D') {
							System.out.println(time);
							return;
						}
						//도착지가 아니라면 계속해서 전개
						q.offer(new Node(nx,ny));
						check[nx][ny] = true;
					}
				}
			}
		}
		
		System.out.println("KAKTUS");
		return;
	}

	//물을 퍼트린다
	private static void fillWater() {
		//체크배열을 만들어서 물을 채운다. 채운물도 미리 체크해두면 체크안한 물만 돌수있지!
		boolean[][] check = new boolean[map.length][map[0].length];
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j]=='*' && !check[i][j]) {
					//사방에 물을 채우고 체크해둔다
					check[i][j] = true;
					for(int k=0; k<4; k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						if(nx>=0 && ny>=0 && nx<map.length && ny<map[0].length &&
								map[nx][ny]!='*' && map[nx][ny]!='X' && map[nx][ny]!='D' &&
								!check[nx][ny]) {
							map[nx][ny] = '*';
							check[nx][ny] = true;
						}
					}
				}
			}
		}
	}
	

	
}