import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 이분 그래프
 * 그래프의 정점의 집합을 둘로 분할하여 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할 할 수 있을때
 * 그러한 그래프를 특별히 이분그래프라 부른다.
 * 그래프가 입력으로 주어졌을때 이분그래프인지 판별하는 프로그램을 작성하라.
 *
 * 입력
 * 테스트케이스 2<=K<=5
 * 각 테케의 1<=정점갯수V<=2만과 1<=간선갯수E<=20만
 * 각 정점은 1~V까지 번호가 붙어있음.
 * E개 줄에 걸쳐 간선에 대한 정보(u,v)가 주어짐.
 *
 * 출력
 * 이분그래프이면 YES, 아니면 NO를 출력한다.
 *
 * 풀이
 * 이분그래프 특성을 이용한다. dfs/bfs로 그래프를 순회하며 모든 정점 방문해보면서 할당된 색이 바뀌는 일이 있는지 확인함
 * 연결요소가 여러개일 수 있으므로 상태가 0인게 있으면 계속 순회 시도
 * 0=할당전 1=주황색 2=회색
 */
public class Main {
    static StringBuilder sb = new StringBuilder();
    static List<ArrayList<Integer>> graph;
    static int[] colors;
    static boolean stopFlag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t=0; t<K; t++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); //정점갯수
            int E = Integer.parseInt(st.nextToken()); //간선갯수
            graph = new ArrayList<>();
            for(int i=0; i<V+1; i++) graph.add(new ArrayList<>());
            for(int i=0; i<E; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                graph.get(v1).add(v2);
                graph.get(v2).add(v1);
            }
            colors = new int[V+1];
            stopFlag = false;
            for(int i=1; i<V+1; i++) {
                if(colors[i] == 0) bfs(i);
                if(stopFlag) break;
            }

            if(stopFlag) sb.append("NO").append('\n');
            else sb.append("YES").append('\n');
        }
        System.out.println(sb);
    }

    public static void bfs(int startV) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(startV);
        colors[startV] = 1;
        while(!q.isEmpty()) {
            int curr = q.poll();
            int nextColor = (colors[curr]==1 ? 2:1);
            for(int next : graph.get(curr)) {
                //이전에 방문한적 없음(0)이면 새로운 색깔 할당,
                // 이전에 방문한적 있음(!=0)이면 현재색과 같은지 확인후 다르면 이분그래프가 아니므로 종료해야함.
                if(colors[next] == 0) {
                    colors[next] = nextColor;
                    q.add(next);
                }
                else {
                    if(colors[next] == nextColor) continue;
                    else {
                        stopFlag = true;
                        return;
                    }
                }
            }
        }

    }
}