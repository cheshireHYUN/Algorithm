import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 작업
 * 수행해야 할 작업이 N개 있고, 각 작업에 걸리는 시간이 주어진다.
 * 선행작업이란게 존재 => K번 작업에 대해 선행관계에 있는 작업은 1이상 K-1이하임
 * 모든작업을 완료하기 위한 최소시간을 구해라. 선행관계가 없는 작업은 동시에 수행 가능!
 *
 * 위상정렬을 이용해서 푼다. 방향이 있으며 사이클이 없는 현재 인접리스트에서 사용하기 좋음
 * 선행작업 없음 = 간선없음 으로 해석할 수 있으므로 위상정렬 로직을 따라 작업순서를 거른다.
 * 또한 동시작업이 가능하다는 특성은 다음과 같이 해결할 수 있다
 * 1번작업이 선행작업인 2,3,4번 작업이 있을경우, 가장 작업이 긴시간을 선택해서 하위로 내려주면 된다.
 * 즉 인덱스와 작업시간이 동일하다는 가정하에, 결국 1+4인 5시간을 기다려야함. 마지막작업은 이 5시간을 더해주는것
 *
 * bfs하듯이 레벨별 탐색으로 최댓값을 찾아 더하는 방식을 시도..했으나
 * 그렇게 풀면 안될거라는 결론이 나옴. 각자 다른 경로로 전개될건데 레벨탐색으로 냅다 최댓값을 구해버리는게 말이안됨
 * 오히려 시뮬레이션 처럼 생각하는게 편할듯. q에서 꺼낸 노드는 작업을 끝내는중인거고, 그게 전체노드 시간에 영향을 준다.
 * 각 노드의 입장에서 내 작업을 완료하기까진 최소 nn시간이 필요해요~ 로 전개해나간다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] times = new int[N+1]; //소요시간
        int[] indegree = new int[N+1]; //간선의 갯수 저장
        List<ArrayList<Integer>> list = new ArrayList<>(); //이차원 연결리스트
        for(int i=0; i<N+1; i++) list.add(new ArrayList<>());

        StringTokenizer st;
        for(int i=1; i<=N; i++){
            //작업에 걸리는 시간 , 선행관계의 작업갯수, 선행관계 작업번호 순으로 주어진다.
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j=0; j<cnt; j++) {
                //연결리스트 2번노드가 1,1,1 이니까 1번노드의 뒤에 연결해준다
                list.get(Integer.parseInt(st.nextToken())).add(i);
                indegree[i]++;
            }
        }
        
        //위상정렬을 통해 결과 리턴
        System.out.println(topologicalSort(N, list, indegree, times));

    }

    private static int topologicalSort(int N, List<ArrayList<Integer>> list, int[] indegree, int[] times) {
        //간선이 존재하지않는 노드를 하나씩 선택, 연결 간선을 지워가며 간선없는 노드를 만들어가며 정렬한다.
        Queue<Integer> q = new ArrayDeque<>();

        int result[] = new int[N+1]; //각 작업 수행시 걸리는 시간을 저장한다
        for(int i=1; i<=N; i++ ){
            result[i] = times[i];

            //indegree가 0인 작업(시작점)을 큐에 넣는다
            if(indegree[i]==0) q.offer(i);
        }

        while(!q.isEmpty()){
            int curr = q.poll();
            for(int next : list.get(curr)){
                indegree[next] --; //간선 삭제
                result[next] = Math.max(result[next], result[curr]+times[next]); //상위에서 내려받은 시간을 통해 적어도 이정도 시간이 필요함을 셋팅

                //indegree가 된 작업을 큐에 넣는다.
                if(indegree[next] == 0) q.offer(next);
            }
        }

        int answer = 0;
        for(int i=1; i<=N; i++) answer = Math.max(answer, result[i]);
        return answer;
    }
}