import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 최소비용 구하기
 * N개의 도시가 있고, M개의 버스가 있다.
 * A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 한다.
 * 도시번호는 1부터 N까지다.
 *
 * 입력 : 1<=N<=1000 1<=M<=100000
 * 풀이 : BFS로 도는건 결국 완탐을 해야만 결과를 알수있으니까 불편하고, 다익스트라를 쓰면 될듯
 * 그럼 우선순위큐 썼을때 시간복잡도가 O((V+E)logV)이므로 금방찾을 수 있다.
 * 최소 거리를 저장할 cost[]와 인접리스트 List<ArrayList<>>, 그리고 Bus클래스를 만든다.
 */
public class Main {
    private static class Bus implements Comparable<Bus>{
        int endCity;
        int cost;
        public Bus(int city, int cost) {
            endCity = city;
            this.cost = cost;
        }
        @Override
        public int compareTo(Bus o){
            return this.cost - o.cost;
        }
    }
    static int N,M,cost[];
    static boolean check[];
    static List<ArrayList<Bus>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        cost = new int[N+1];
        check = new boolean[N+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        for(int i=0; i<N+1; i++) list.add(new ArrayList<>());
        StringTokenizer st;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            list.get(Integer.parseInt(st.nextToken()))
                    .add(new Bus(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(br.readLine());
        int min = dijkstra(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        System.out.println(min);
    }

    static int dijkstra(int start, int end){
        Queue<Bus> pq = new PriorityQueue<>();
        pq.add(new Bus(start, 0));
        cost[start] = 0;
        while(!pq.isEmpty()) {
            Bus now = pq.poll();
            if(check[now.endCity]) continue;

            check[now.endCity] = true;
            for(Bus next : list.get(now.endCity)) {
                if(check[next.endCity]) continue;
                if(cost[next.endCity] > now.cost+next.cost){
                    cost[next.endCity] = now.cost+next.cost;
                    pq.add(new Bus(next.endCity, cost[next.endCity]));
                }
            }
        }
        return cost[end];
    }
}