import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/** DFS와 BFS
 * 방문할 수 있는 정점이 여러개인경우 번호가 작은것을 먼저 방문하고,
 * 더이상 방문할 수 있는 점이 없으면 종료
 * 
 * 인접리스트를 이용하여 풀어보장~
 * 외않되?
 * 아 인접리스트로 풀면 작은것부터 선택하기가 쉽지않네 입력순으로 들어가니까..
 */
public class Main {
	static List<ArrayList<Integer>> list;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //정점 갯수
		int M = Integer.parseInt(st.nextToken()); //간선 갯수
		int V = Integer.parseInt(st.nextToken()); //시작 정점 번호
		
		list = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<=N; i++) list.add(new ArrayList<Integer>()); //각 정점에 대한 연결리스트 생성
		
		check = new boolean[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.get(x).add(y);
			list.get(y).add(x);
		}
		
		for(int i=1; i<=N; i++) Collections.sort(list.get(i));
		
		check[V]=true;
		dfs(V);
		sb.append('\n');
		Arrays.fill(check, false); //체크배열 초기화
		bfs(V);
		
		System.out.println(sb);
	}
	
	// v와 연결된 노드 찾기, 즉 list.get(v)에 연결된 노드 찾기
	private static void dfs(int v) {
		sb.append(v).append(' ');
		for(int i : list.get(v)) {
			if(!check[i]) {
				check[i] = true;
				dfs(i);
			}
		}
	}
	
	// 
	private static void bfs(int v) {
		check[v]=true;
		Queue<Integer> q = new LinkedList<>();
		q.offer(v);
		while(!q.isEmpty()){
			int x = q.poll();
			sb.append(x).append(' ');
			for(int i : list.get(x)) {
				if(!check[i]) {
					check[i] = true;
					q.offer(i);
				}
			}
		}
	}
}