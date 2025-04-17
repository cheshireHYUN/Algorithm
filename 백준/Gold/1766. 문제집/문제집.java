import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 문제집
 * 난이도순서로 정렬된 1~N번 문제가 있다.
 * N개의 문제를 모두 풀때, 먼저 푸는것이 좋은 문제를 먼저 푼다. 가능하면 쉬운문제부터 푼다.
 * 2번보다 4번먼저, 1번보다 3번먼저인 문제가 있을때 3-1-2-4
 * 조건을 만족하면서 순서를 결정해주는 프로그램을 작성하라.
 *
 * 제한
 * 1<=N(문제 수)<=32000
 * 1<=M(먼저 푸는게 좋은 문제 수)<=10만
 *
 * 풀이
 * 위상정렬 : 그래프를 정렬하는 개념으로, 방향이 있고 사이클이 없을때 사용 가능하다.
 * 각 노드가 자신을 가리키는 간선의 수를 갖는다. 큐에서 1을 빼주며 노드1에 가리켜지는 간선수를 다 줄인다.
 * 이때, 간선수가 0이된 노드들은 큐에 넣는다.
 * 순서대로 다음 노드를 꺼내고, 연결된 간선을 지우며 각 간선수들을 줄인다. 그리고 반복..
 * 결과적으로 큐에서 나온 순서가 위상정렬된 결과이다.
 * 결론) 이 알고리즘은 진입차수(자신을 가리키는 간선)이 0인 정점부터 차례대로 정렬하는것으로, 일의 순서를 정할때 사용!
 *
 * 따라서 이 문제를 분석했을때,
 * (1) 자신의 선수문제가 없다면 풀 수 있고,
 * (2) 선수문제가 있다면 이후에 그걸 푼 뒤에 현재문제 풀이가 가능한것이다.
 *
 * 1. 특정 문제(노드)에 대해 먼저 풀어야하는 선문제의 갯수를 indegree 배열에 저장한다.
 * 2. indegree[i]=0 즉 선문제가 없는 문제를 먼저 우선순위큐에 넣는다(원래 위상정렬은 큐만 써도 ㄱㅊ지만 이건 더 쉬운문제부터 푼다는 조건이있음)
 * 3. 우선순위큐에서 꺼낸 문제는 답에 누적하고, 해당 문제가 indegree에 해당되는 후문제들이 있다면 indegree-- 해준다. 그결과가 0인녀석들은 큐에 넣는다.
 * 4. 큐가 empty할때까지 반복한다.
 */
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] indegree;
    static List<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //연결정보 저장
        graph = new ArrayList<>();
        for(int i=0; i<N+1; i++) graph.add(new ArrayList<>());
        //각 문제의 진입간선(선문제) 갯수를 저장
        indegree = new int[N+1];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            indegree[next] += 1;
            graph.get(pre).add(next);
        }
        topologySort();
        System.out.println(sb);
    }

    private static void topologySort() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<N+1; i++) {
            if(indegree[i] == 0) pq.offer(i);
        }
        while(!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now).append(' ');
            List<Integer> nextNode = graph.get(now);
            for(int next : nextNode) {
                indegree[next] -= 1;
                if(indegree[next] == 0) pq.offer(next);
            }
        }
    }

}