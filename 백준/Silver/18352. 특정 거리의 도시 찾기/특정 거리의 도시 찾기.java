import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 어떤나라에서 1번부터 N번까지 도시와 M개의 단방향 도로가 존재하며 도로의 거리는 1이다.
 * 특정 도시 X로부터 출발하여 도착하는 도시중 최단거리가 K인 도시번호를 출력하라.
 * (풀이) BFS를 사용하여 레벨별탐색으로 K번째 단계의 도시를 출력하면 될듯
 */
public class Main {
    static List<ArrayList<Integer>> list;
    static int N,M,K,X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //도시의 갯수
        M = Integer.parseInt(st.nextToken()); //도로의 갯수
        K = Integer.parseInt(st.nextToken()); //목표 거리
        X = Integer.parseInt(st.nextToken()); //출발도시 번호

        list = new ArrayList<>();
        for(int i=0; i<N+1; i++) list.add(new ArrayList<>());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            list.get(Integer.parseInt(st.nextToken()))
                    .add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> result = bfs();
        if(result.size() == 0) System.out.println(-1);
        else {
            Collections.sort(result);
            for(int i : result) System.out.println(i);
        }

    }

    private static List<Integer> bfs() {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(X);
        boolean[] check = new boolean[N+1];
        check[X] = true;
        int level = 0;
        while(!q.isEmpty()){
            level++;
            int len = q.size();
            for(int i=0; i<len; i++){
                int curr = q.poll();
                for(int li : list.get(curr)){
                    if(!check[li]){
                        check[li] = true;
                        q.offer(li);
                        if(level == K) result.add(li);
                    }
                }
            }
            if(level == K) break;
        }
        return result;
    }
}