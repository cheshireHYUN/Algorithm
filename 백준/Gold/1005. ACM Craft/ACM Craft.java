import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** ACM Craft
 * 매 게임 시작시 건물 짓는 순서가 주어진다. 모든 건물은 각각 건설을 시작해 완성될때까지 딜레이가 존재한다.
 * 문제가 엄청 위상정렬의 정석 문제인듯 a건물이 b번물보다 먼저 지어져야한다 같은 조건이 주어짐.
 * 특정건물을 가장 빨리 지ㅣ을떄까지 걸리는 최소시간을 알아내는 프로그램을 작성하라.
 *
 * 풀이
 * 위상정렬(사이클이 없는 방향그래프의 모든노드를 방향성에 거스르지 않고 순서대로 나열하는것)
 * 위상정렬은 큐를 이용해 구현한다. 진입차수가 0인 노드를 넣어두고 하나씩 방문처리 하면서 타 진입차수를 빼준다.
 *
 */
public class Main {
    static int N,K, target;
    static int[] deplay, indegree;
    static List<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //건물의 갯수
            K = Integer.parseInt(st.nextToken()); //건물간의 건설순서 규칙 1~N번
            deplay = new int[N+1]; //각 건물당 건설에 걸리는 시간
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) deplay[i] = Integer.parseInt(st.nextToken());

            indegree = new int[N+1]; //각 건물의 진입차수
            graph = new ArrayList<>();//각 건물의 진입정보
            for(int i=-0; i<=N; i++) graph.add(new ArrayList<>());
            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int pre = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());
                indegree[next] += 1;
                graph.get(pre).add(next); //pre(진입차수)를 삭제했을때 영향받을 노드들을 저장
            }
            target = Integer.parseInt(br.readLine());
            sb.append(topologySort()).append('\n');
        }

        System.out.println(sb);
    }

    //1,2,3이 4의 선행차수일때, 그냥 순서대로 돌아버리면 3의 누적시간+4의 딜레이시간을 써버려서 1,2가 누적시간이 더 커도 잡히지 않음
    //따라서 DP배열을 선언한뒤 dp[i] = i번건물을 짓는데 드는 가장 긴 누적시간을 저장해서 사용
    private static int topologySort() {
        int[] dp = new int[N+1]; //각 노드의 최대 누적시간을 저장
        Queue<Integer> q = new ArrayDeque<>();
        //진입차수가 0인것들 먼저 실행
        for(int i=1; i<=N; i++) {
            if(indegree[i] == 0) q.add(i);
            dp[i] = deplay[i];
        }
        
        while(!q.isEmpty()) {
            int curr = q.poll();

            for(int next: graph.get(curr)) {
                dp[next] = Math.max(dp[next], dp[curr]+deplay[next]);
                indegree[next] -= 1;
                if(indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        return dp[target];
    }
}