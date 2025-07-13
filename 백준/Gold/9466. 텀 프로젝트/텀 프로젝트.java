import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 텀프로젝트
 * 학생들은 자기자신을 포함해 함께하고 싶은 팀원1명을 선택한다.
 * 어느 팀에도 속하지 않는 학생의 수를 구하는 프로그램을 작성하라.
 *
 * 풀이
 * 같은팀이란 사이클을 의미한다. 답은 "사이클이 아닌것의 갯수를 표시"
 * 학생수가 10만까지 이므로... 위상정렬 이용하면 될듯
 * DFS를 이용한 풀이도 있는데 경로 추적에 좀더 최적화됨 -> 근데 나는 갯수만 세면 되니까 위상정렬함
 * 따라서 위상정렬 돌려서 나온것 카운트 = 사이클이 아닌것의 갯수
 */
public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static List<List<Integer>> graphs = new ArrayList<>();
    private static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-->0) {
            graphs.clear();
            int n = Integer.parseInt(br.readLine());
            indegree = new int[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<=n; i++) graphs.add(new ArrayList<>());
            for(int start=1; start<=n; start++) {
                int end = Integer.parseInt(st.nextToken());
                indegree[end] += 1;
                graphs.get(start).add(end);
            }
            sb.append(topologySort()).append('\n');
        }
        System.out.println(sb);
    }

    private static int topologySort() {
        int cnt = 0; //사이클이 아닌것만 카운트
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<indegree.length; i++) {
            if(indegree[i] == 0) {
                q.add(i);
                cnt++;
            }
        }
        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int next : graphs.get(curr)) {
                indegree[next] --;
                if(indegree[next] == 0) {
                    cnt++;
                    q.add(next);
                }
            }
        }
        return cnt;
    }
}
