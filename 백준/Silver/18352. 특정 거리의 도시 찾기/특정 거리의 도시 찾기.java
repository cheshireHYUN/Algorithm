import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 특정 거리의 도시찾기
 * 1번부터 N번까지 도시와 M개의 단방향 도로가 존재한다. 모든 거리는 1이다.
 * 특정 도시 X로부터 도달하는 도시중 최단거리가 K인 도시 오름차순 출력
 * BFS를 레벨별 탐색하면됨 체크 꼭하고
 */
public class Main {
    static int N,M,K,X;
    static List<ArrayList<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //도시갯수
        M = Integer.parseInt(st.nextToken()); //도로갯수
        K = Integer.parseInt(st.nextToken()); //최단거리
        X = Integer.parseInt(st.nextToken()); //출발도시번호

        for(int i=0; i<N+1; i++) list.add(new ArrayList<>());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            list.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> result = bfs();
        Collections.sort(result);
        for(int i: result) sb.append(i).append("\n");
        System.out.println(sb);
    }

    static StringBuilder sb = new StringBuilder();
    private static List<Integer> bfs(){
        boolean[] check = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque();
        q.offer(X);
        check[X] = true;
        int level = 0;
        while(!q.isEmpty()){
            int size = q.size();
            level++;
            for(int i=0; i<size; i++){
                int curr = q.poll();
                for(int a : list.get(curr)){
                    if(!check[a]) {
                        check[a] = true;
                        q.offer(a);
                    }
                }
            }
            if(level == K) break;
        }

        List<Integer> result = new ArrayList<>();
        if(q.isEmpty()) result.add(-1);
        else {
            while(!q.isEmpty()) result.add(q.poll());
        }

        return result;
    }
}