import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 게리맨더링
 * 공평하게 선거구를 확정한다.
 * N개 구역이 있고, 두개의 선거구로 나눈다.
 * 선거구는 적어도 하나씩은 있어야되고, 한 선거구에 포함된 구역은 모두 연결되어야 한다.
 * 두 선거구 사이의 인구차이를 최소로 하고 싶을때, 그 최솟값을 구해보자.
 *
 * 풀이
 * N이 최대 10이니까 (1)선거구를 나누는 모든 부분집합을 구하고(2^10=1024) (2)가능한 분류인지 검사
 * 가능한 분류인지 검사 => bfs 두번하면서 각각 연결요소인지 확인
 */
public class Main {
    static int N, allPerson=0;
    static int[] personCnt;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N+1; i++) graph.add(new ArrayList<>());
        personCnt = new int[N+1];
        isSelected = new boolean[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) {
            personCnt[i] = Integer.parseInt(st.nextToken());
            allPerson += personCnt[i];
        }

        //인접한 구역의 수, 인접한 구역의 번호
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j=0; j<cnt; j++) graph.get(i+1).add(Integer.parseInt(st.nextToken()));
        }

        solution(1);
        if(minPerson == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minPerson);
    }

    static boolean[] isSelected;
    static int minPerson = Integer.MAX_VALUE;
    private static void solution(int cnt) {
        if(cnt == N+1) {
            //부분집합 구하기 완료 => 가능
            int nowPerson = 0, anotherPerson = 0;
            for(int i=1; i<N+1; i++) {
                if(isSelected[i]) nowPerson += personCnt[i];
                else anotherPerson += personCnt[i];
            }

            //현재 선택된 두 선거구를 기준으로 연결되었는지 확인
            int target=0;
            for(int i=1; i<=N; i++) {
                if(isSelected[i]) target++;
            }
            if(!bfs(true, target)) return;
            if(!bfs(false, N-target)) return;

            minPerson = Math.min(minPerson, Math.abs(nowPerson-anotherPerson));
            return;
        }

        isSelected[cnt] = true;
        solution(cnt+1);
        isSelected[cnt] = false;
        solution(cnt+1);
    }

    private static boolean bfs(boolean flag, int target) {
        boolean[] isVisited = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++) {
            if(isSelected[i]==flag) {
                q.add(i);
                isVisited[i] = true;
                break;
            }
        }

        while(!q.isEmpty()) {
            int curr = q.poll();
            target--;
            List<Integer> list = graph.get(curr);
            for(int next : list) {
                if(isSelected[next]==flag && !isVisited[next]) {
                    q.add(next);
                    isVisited[next] = true;
                }
            }
        }

        return target == 0;
    }
}
