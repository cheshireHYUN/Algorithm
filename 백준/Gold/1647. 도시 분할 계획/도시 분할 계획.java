import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** 도시 분할 계획
 * N개의 집과 M개의 길이 있다.
 * 마을을 두개로 분할하는데 분리된 마을 안에서 집들은 서로 연결되어야한다.
 * 길을 최대한 없애고 나머지 길의 유지비 합을 최소로 하는 프로그램을 작성하라.
 *
 * 풀이
 * 가중치가 있는 그래프.
 * 가장 작은 간선부터 선택해서 MST구하면 전체마을이 연결될테고 그중에서도 가장 큰 길이를 뺌으로서 둘로 나누면 되지 않을까?
 * 즉, 크루스칼 알고리즘 후 가장 비싼 간선 제거.
 */
public class Main {
    static class Node implements Comparable<Node>{
        int start;
        int end;
        int cost;
        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N,M;
    static int[] parents;
    static List<Node> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        for(int i=1; i<=N; i++) parents[i] = i;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Node(a,b,c));
        }
        Collections.sort(list);

        int sumCost = 0;
        int maxEdge = 0;
        for(Node node : list) {
            if(union(node.start, node.end)) {
                sumCost += node.cost;
                maxEdge = Math.max(maxEdge, node.cost);
            }
        }

        System.out.println(sumCost-maxEdge);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;
        parents[rootB] = rootA;
        return true;
    }

    static int find(int v) {
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }
}