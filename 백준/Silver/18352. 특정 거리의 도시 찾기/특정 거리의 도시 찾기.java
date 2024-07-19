import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 특정 거리의 도시찾기
 * 1~N번까지 도시와 M개의 단방향 도로가 있음
 * X로부터 출발하여 도달할수있는 도시 중 최단거리가 정확히 K인 모든 도시들의 번호를 출력해라.
 * 풀이 : BFS를 레벨별 탐색으로 돌린다! 이미 그전에 최단거리로 도착한 부분들은 체크하는거 잊지말기
 * 또한 이차원배열이 아닌 인접리스트로 풀어야함..
 */
public class Main {
    static int N, M, K, X;
    static boolean check[];
    static StringBuilder sb = new StringBuilder();
    static List<ArrayList<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //도시의 갯수
        M = Integer.parseInt(st.nextToken()); //도로의 갯수
        K = Integer.parseInt(st.nextToken()); //목표 거리 정보
        X = Integer.parseInt(st.nextToken()); //출발도시 번호

        check = new boolean[N+1];
        for(int i=0; i<N+1; i++) list.add(new ArrayList<>());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            list.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer> result = solution();
        if(result.size() == 0) System.out.println(-1);
        else {
            Collections.sort(result);
            for(int i : result) sb.append(i).append("\n");
            System.out.println(sb);
        }

    }

    private static ArrayList<Integer> solution() {
        Queue<Integer> q = new ArrayDeque();
        q.add(X);
        check[X] = true;
        int step = 0;
        ArrayList<Integer> result;

        while(!q.isEmpty()){
            result = new ArrayList<>();
            int len = q.size();
            for(int i=0; i<len; i++){
                int currNode = q.poll();
                for(Integer li : list.get(currNode)) {
                    if(!check[li]){
                        check[li] = true;
                        result.add(li);
                        q.add(li);
                    }
                }
            }
            step++;
            if(step == K){
                return result;
            }
        }

        return new ArrayList<>();
    }
}