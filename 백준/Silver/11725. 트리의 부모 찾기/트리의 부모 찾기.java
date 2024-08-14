import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 트리의 부모찾기
 * 루트없는 트리가 주어진다. 이때 루트를 1이라고 가정했을때 각 노드의 부모를 구하는 프로그램을 작성하라.
 * (풀이) bfs를 통해서 루트노드부터 탐색한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //노드의 갯수
        StringTokenizer st;
        List<ArrayList<Integer>> list = new ArrayList<>();

        for(int i=0; i<N+1; i++) list.add(new ArrayList<>());
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            list.get(node1).add(node2);
            list.get(node2).add(node1);

        }

        int[] parents = new int[N+1];
        parents[1] = 1;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);

        while(!q.isEmpty()){
            int curr = q.poll();
            for(int li : list.get(curr)){
                if(parents[li] == 0) {
                    parents[li] = curr;
                    q.add(li);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=2; i<parents.length; i++) sb.append(parents[i]).append('\n');
        System.out.println(sb);


    }
}