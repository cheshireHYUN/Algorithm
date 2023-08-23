import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** 최소스패닝트리
 * 1. 간선리스트를 가중치 기준으로 오름차순 정렬
 * 2. 정점을 Make-Set하고
 * 3. 간선리스트에서 비용이 작은 순서대로 꺼내면서 union연산
 * 	3-1. 싸이클이 발생하면(즉 같은 집합안에 있으면) union false일거고
 * 	3-2. 싸이클이 발생하지 않으면(다른집합안에 있음) union true일것
 * 4. 따라서 true일때 가중치를 누적한다.
 */
public class Solution {
	
	static int V,E, parents[];
	static List<Node> list;
	static StringBuilder sb = new StringBuilder();
	
	static class Node implements Comparable<Node>{
		int to, from;
		long weight;
		public Node(int x, int y, long weight) {
			super();
			this.from = x;
			this.to = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return (int) (this.weight-o.weight);
		}
	}
	
	private static void makeSet() {
		parents = new int[V+1];
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int v) {
		if(v == parents[v]) return v;
		return parents[v] = find(parents[v]);
	}
	
	private static boolean union(int v1, int v2) {
		int root1 = find(v1);
		int root2 = find(v2);
		if(root1==root2) return false;
		parents[root1] = root2;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1; tc<=t; tc++) {
			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				long weight = Integer.parseInt(st.nextToken());
				list.add(new Node(from,to,weight));
			}
			Collections.sort(list);
			
			//각 독립적 집합 생성
			makeSet();
			
			//작은것부터 하나씩 꺼내면서 합집합 연산, 같은 집합내에있으면 false, 다른집합이면 합치고 true하므로 이때 카운트함
			long total = 0;
			for(Node n : list) {
				if(union(n.to, n.from)) {
					total += n.weight;
				}
			}
			sb.append('#').append(tc).append(' ').append(total).append('\n');
		}
		
		System.out.println(sb);
	}
}