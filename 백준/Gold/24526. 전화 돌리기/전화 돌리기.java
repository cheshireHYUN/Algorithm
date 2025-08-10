import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 전화돌리기
 * 전화를 넘길때 부원들이 어떻게 넘기더라도 한사람이 두번이상 받지 않도록 한다.
 * 회장이 전화를 넘길 수 있는 부원의 수를 구한다.
 *
 * 제한
 * 부원은 2명이상 10만명 이하
 *
 * 풀이
 * 전화를 넘길때 부원들이 어떻게 넘기더라도 = 즉 사이클 포함, 사이클과 연결된 정점에도 방문하면 X
 * 따라서 포인트는 싸이클이 되지않는(연결도 X) 정점들만 구하는것 - 싸이클은 어떻게 판단하지?
 * (Hint) 부원끼리 선후관계가 있으니까 위상정렬을 활용할 수 있다.
 *
 * -> 일반적인 위상정렬의 경우 다음노드가 사이클이면 그냥 정렬이 멈춰버림..
 * -> 따라서 사이클만 로직에서 분리하기 위해서는 => 간선을 역으로 뒤집는다!
 * 역방향 그래프로 위상정렬시 큐에서 뺀 노드 갯수 = 전화를 넘길 수 있는 노드수가 된다
 * (사이클은 여전히 사이클이고, 사이클로 전화를 넘기던 노드들의 indegree가 생긴것이므로)
 *
 * (1) 인접리스트로 역방향 그래프, indgreee[] 설정
 *      - a가 b에게 전화 = (a,b) -> list.get(b).add(a); indegree[a]++;
 * (2) indgree[i]=0인 정점들을 Queue에 저장
 * (3) 큐가 빌때까지 순회하며
 *      - cnt++
 *      - list.get(q.poll())의 인접 정점들을 돌면서 indegree[i]--;
 *      - indegree[i]==0이면 q.add()
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] indegree = new int[N+1];
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<N+1; i++) graph.add(new ArrayList<>());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(end).add(start);
            indegree[start]++;
        }

        //위상정렬
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<N+1; i++) {
            if(indegree[i] == 0) q.add(i);
        }
        int cnt = 0;
        while(!q.isEmpty()) {
            cnt++;
            for(int next : graph.get(q.poll())) {
                indegree[next]--;
                if(indegree[next]==0) q.add(next);
            }
        }
        System.out.println(cnt);
    }
}
