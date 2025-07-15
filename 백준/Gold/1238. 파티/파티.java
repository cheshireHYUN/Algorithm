import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 파티
 * N개의 숫자로 구분된 마을에 1명씩 살고있다.
 * N명이 X마을에 모여 파티를 벌인다. 마을사이에 총 M개의 단방향 도로가 있고, Ti의 시간을 소모한다.
 * 각 학생들은 파티 참석을 위해 걸어갔다가 각자의 마을로 돌아온다.
 * 모두 최단시간을 오고갈때, N명의 학생들 중 오고가는데 가장 많은 시간을 소비하는 학생은 누구?
 *
 * 풀이
 * 단방향, 0이상 가중치가 존재하는 그래프에서 각 노드의 X까지의 왕복 비용을 구해야한다.
 * 따라서 다익스트라 알고리즘을 활용, X마을부터 모든마을까지의 최단거리를 구한다
 * 다만 "왕복거리"임이 중요한데. x에서 모든노드로 가는 최단거리를 구하고 + 모든노드에서 X로 가는 최단거리를 구해야함.
 * 그러나 두번째(모든노드->X)를 다익스트라로 구하는것은 비효율적이다. 따라서 그래프를 역방향으로 뒤집어서 풀면 됨!
 * 플로이드워셜은(O^3)이므로 10억연산이 필요 -> 시간초과
 * 다익스트라는O(ElogV)이므로 10000*log1000 = 10만, 2번 돌리니까 20만
 *
 * (1) Node(int index, int dist) 클래스 및 graph 리스트, 그리고 최종 값을 저장할 dist[]배열을 INF로 초기화해 만든다.
 * (2) 시작점을 X로 두고, 해당 Node를 우선순위 큐(dist가 작은것을 선출)에 저장, dist[x]=0으로 변경한다.
 * (3) 큐가 빌때까지 순회하면서 다음을 수행한다.
 *  3-0. dist[curr.index] < curr.dist 즉 index에 기록된 dist[]값이 현재노드의 dist보다 작으면 continue;
 *  3-1. 나머지는 curr.index와 연결된 next 노드들을 순회한다.
 *  3-2. dist[next.index] > dist[curr.index]+next.dist일 경우
 *          dist[next.index] = dist[curr.index]+next.dist로 변경하고
 *          pq에 new Node(next.index, dist[next.index])를 넣는다.
 * (4) 이렇게 X에서 모든노드까지의 거리를 구했으니, 이제 반대로 모든노드에서 X로 가는 거리를 모든 간선을 반전해서 반복한다.
 *      그러면 dist1[]과 dist2[]가 존재하므로, 인덱스별로 각각 합해서 결과를 만들면 됨.
 */
public class Main {
    static int N,M,X;
    static int[] times1, times2;
    public static class Node implements Comparable<Node> {
        public int index;
        public int time;
        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }
        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
    static List<List<Node>> graph1 = new ArrayList<>();
    static List<List<Node>> graph2 = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //입력받기
        setInput();

        //다익스트라 - times1, times2 채우기
        times1 = dijkstra(graph1);
        times2 = dijkstra(graph2);

        //결과에서 가장 오래걸리는 학생 찾기
        int max = Integer.MIN_VALUE;
        for(int i=1; i<=N; i++) {
            if(i != X) max = Math.max(max, times1[i]+times2[i]);
        }
        System.out.println(max);
    }

    public static int[] dijkstra(List<List<Node>> graph) {
        int[] times = new int[N+1];
        Arrays.fill(times,Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(X,0));
        times[X] = 0;

        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            if(times[curr.index] < curr.time) continue;
            for(Node next : graph.get(curr.index)) {
                if(times[next.index] > times[curr.index]+next.time) {
                    times[next.index] = times[curr.index]+next.time;
                    pq.add(new Node(next.index, times[next.index]));
                }
            }
        }

        return times;
    }

    public static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //마을 수
        M = Integer.parseInt(st.nextToken()); //간선 수
        X = Integer.parseInt(st.nextToken()); //파티하는 곳(X)

        for(int i=0; i<=N; i++) {
            graph1.add(new ArrayList<>());
            graph2.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph1.get(start).add(new Node(end, time));
            graph2.get(end).add(new Node(start, time));
        }
    }
}
