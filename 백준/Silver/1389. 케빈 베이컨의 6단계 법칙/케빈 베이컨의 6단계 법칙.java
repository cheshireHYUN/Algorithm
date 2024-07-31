import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 케빈베이컨 법칙
 * 케빈베이컨 : 케빈베이컨의 수가 가장 작은사람을 찾자(즉 모든사람과 연관된 길이가 가장 작은사람)
 * 모든정점으로부터 모든 정점까지의 거리의 최단거리를 구해야한다. 즉 플로이드-워셜을 구한다.
 * 플로이드워셜은 간단하다. 3중for문을 사용하며, 시작점, 출발점, 그리고 경유점을 고려한다.
 * Dab > Dac+Dcb 일때, Dab = Dac+Dcb로 바꿔준다.
 */
public class Main {
    static int N,M,map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(map[i], N);
            map[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map[start][end] = 1;
            map[end][start] = 1;
        }

        System.out.println(solution());
    }

    private static int solution(){
        //플루이드 워셜
        for(int mid=1; mid<N+1; mid++){
            for(int start=1; start<N+1; start++){
                for(int end=1; end<N+1; end++){
                    if(map[start][end] > map[start][mid]+map[mid][end]){
                        map[start][end] = map[start][mid]+map[mid][end];
                    }
                }
            }
        }
        
        //케빈베이컨이 가장 작은 사람. 즉 한 행의 모든 열값을 합했을때의 값이 가장 작은사람
        int minPerson = 0, minValue=Integer.MAX_VALUE;
        for(int start=1; start<N+1; start++) {
            int sum = 0;
            for (int end = 1; end < N+1; end++) {
                sum += map[start][end];
            }
            if(sum < minValue) {
                minValue = sum;
                minPerson = start;
            }
        }
        return minPerson;
    }
}