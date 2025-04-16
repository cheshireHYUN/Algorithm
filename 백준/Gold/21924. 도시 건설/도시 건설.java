import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** 도시건설
 * 모든 건물이 연결되면서 비용은 최소가 되도록 도로를 만들었을때, 모든 도로 건설했을때보다 얼마나 절약되는지 구하자.
 *
 * 제한
 * 3<=N(건물갯수)<10만
 * 2<=M(도로갯수)<=min(N(N-1)/2, 50만)
 * 1<=c(비용)<=100만
 * 
 * 풀이
 * MST로 구해서 전체값에서 빼면 될거같은데? NlogN이니까...(N은 간선의 갯수)
 * (주의) N-1개가 최대 10^5정도고, 비용최대는 10^6이니까 둘을 곱하면 10^11로 10^3억임 => int안됨
 */
public class Main {
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int cost;
        public Edge(int start, int end, int cost) {
            this.cost = cost;
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        List<Edge> edgeList = new ArrayList<>();
        long totalCost=0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Edge edge = new Edge(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            edgeList.add(edge);
            totalCost += edge.cost;
        }
        Collections.sort(edgeList);
        int edgeCount = 0;
        long minCost = 0;
        for(int i=0; i<edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            if(union(edge.start, edge.end)){
                edgeCount++;
                minCost += edge.cost;
            }
            if(edgeCount == N-1) break;
        }
        if(edgeCount != N-1) System.out.println(-1);
        else System.out.println(totalCost - minCost);
    }


    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parent[bRoot] = aRoot;
        return true;
    }

    private static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}