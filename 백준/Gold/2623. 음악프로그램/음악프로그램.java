import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

/** 음악 프로그램
 * 각 보조피디가 정한 순서를 따르는 전체 순서를 만들어라.
 * 답이 여러개면 그중 하나를, 불가능하면 0을 출력한다.
 * 
 * 풀이
 * 이것도 대표적인 위상정렬 문제같음. 
 * 1. indegree 배열을 선언해서 인풋을 저장
 * 2. graph를 선언해서 연결정보를 저장 graph.get(1) = 1을 input으로 하는 노드들을 저장
 * 3. queue를 선언해서 indegree=0인것들을 모두 집어넣고, 연결된 노드들을 indegree--;
 * 4. 사이클이 발생하는건 어쩌죠 => 위상정렬 로직을 타면 그 결과가 사이클을 절대 포함 불가능하므로 정점수만 체크
 * (왜 사이클 포함 불가? 1->2->3->1 방식으로 연결되어있으면 어쨋든 시작점이 필요함,
 * 예를들어 4번이 1번에 연결되어있다 치면 결국 1은 indegree[2]가 되므로 영원히 큐에 못들어옴)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //가수의 수
        int M = Integer.parseInt(st.nextToken()); //보조PD의 수
        int[] indegree = new int[N+1];
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<N+1; i++) graph.add(new ArrayList<>());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int[] arr = new int[cnt];
            for(int j=0; j<cnt; j++) arr[j] = Integer.parseInt(st.nextToken());
            for(int j=1; j<cnt; j++) {
                graph.get(arr[j-1]).add(arr[j]);
                indegree[arr[j]] += 1;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<N+1; i++) {
            if(indegree[i] == 0) q.add(i);
        }

        List<Integer> result = new ArrayList<>();
        while(!q.isEmpty()) {
            int curr = q.poll();
            result.add(curr);
            for(int next :graph.get(curr)) {
                indegree[next] -= 1;
                if(indegree[next] == 0) q.add(next);
            }
        }

        if(result.size() == N) {
            StringBuilder sb = new StringBuilder();
            for(int i: result) sb.append(i).append('\n');
            System.out.println(sb);
        } else System.out.println(0);
    }
}
