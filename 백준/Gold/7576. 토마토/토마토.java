import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 토마토
 * 토마토를 2차원 격자에 넣어 보관한다.
 * 익은 토마토 근처(상하좌우)의 토마토는 익는다.
 * 며칠이 지나면 모든 토마토가 익는지 최소 일수를 구해라!
 *
 * 풀이 : 익은 토마토 배열을 만들고, 매일(반복문) 그 토마토들의 사방을 업데이트해준다.
 * 안익은 토마토 갯수에서 -1을 하다가 0이되면 종료하면 될듯?,., 완전탐색에 가까운데..
 * => BFS와 유사하게 전개했다! 이젠 레벨별 BFS로 전환해보자.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); //열
        int N = Integer.parseInt(st.nextToken()); //행
        int[][] map = new int[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        int totalNotRedCnt = 0;
        //N열 M행, 1은익은토마토 0은안익은토마토 -1은빈칸
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) q.offer(new int[]{i, j});
                else if (map[i][j] == 0) totalNotRedCnt++;
            }
        }

        //익은 토마토를 기준으로 매일을 사방탐색으로 기록한다
        //하루치 전이를 끝낸 토마토는 이후 반복부턴 돌지않음
        int day = solution(totalNotRedCnt, q, N, M, map);
        System.out.println(day);
    }

    private static int solution(int totalNotRedCnt, Queue<int[]> q, int N, int M, int[][] map) {
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int day = 0;
        if (totalNotRedCnt == 0) return 0;

        //레벨별 BFS탐색으로 변환해보자.
        while (!q.isEmpty()) {
            day++;
            int len = q.size();
            for (int l = 0; l < len; l++) {
                int[] redTomato = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nextX = redTomato[0] + dx[i];
                    int nextY = redTomato[1] + dy[i];

                    if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
                    if (map[nextX][nextY] == 0) { //안익은 토마토가 있을때만
                        map[nextX][nextY] = 1;
                        q.offer(new int[]{nextX, nextY});
                        totalNotRedCnt--;
                        if (totalNotRedCnt == 0) return day;
                    }
                }
            }
        }
        return -1;
    }
}