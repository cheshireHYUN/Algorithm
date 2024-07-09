import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 줄세우기
 * N명의 학생을 키순서로 정렬. 두 학생의 키를 비교하며..
 * 일부학생 키를 비교한 결과가 주어졌을때 줄세우는 프로그램 작성
 * 정답이 여러개면 그중 아무거나 출력
 *
 * 풀이 : 방향이있는 그래프이며 사이클이 없으므로 위상정렬 사용 가능.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //학생수
        int M = Integer.parseInt(st.nextToken()); //키를 비교한 횟수

        ArrayList<List<Integer>> list = new ArrayList<>();
        int[] indegree = new int[N+1];
        for(int i=0; i<=N; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            list.get(front).add(back);
            indegree[back] ++; //간선의 갯수 셋팅
        }

        System.out.println(topologicalSort(N, list, indegree));


    }

    private static String topologicalSort(int N, ArrayList<List<Integer>> list, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new ArrayDeque<>();

        //큐 셋팅
        for(int i=1; i<=N; i++) if(indegree[i]==0) q.offer(i);

        while(!q.isEmpty()){
            int curr = q.poll();
            sb.append(curr).append(' ');

            for(int i : list.get(curr)){
                if(-- indegree[i] == 0) q.offer(i);
            }
        }

        return sb.toString();
        
    }


}