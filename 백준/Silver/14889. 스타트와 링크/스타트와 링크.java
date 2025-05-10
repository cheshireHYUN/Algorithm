import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 스타트와 링크
 * Sij = i와 j가 한팀일때 능력치
 * 두 팀의 능력치 차이를 최소로 하려고 할때 이 최솟값을 출력하라.
 *
 * 풀이
 * N이 최대 20까지니까 20C10 = 20!/10!x10! = 약 9만개..
 * 9만개에 대해서 덧셈연산이니까 가능할덧
 * (1) 조합구하고
 * (2) 조합에 따라 능력치 계산
 */
public class Main {
    static int[][] map;
    static boolean[] isVisited;
    static int N, min=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isVisited = new boolean[N];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0,0);
        System.out.println(min);
    }

    private static void combi(int cnt, int start) {
        if(cnt == N/2) {
            //능력치 계산
            min = Math.min(min, calculate());
            return;
        }

        for(int i=start; i<N; i++) {
            if(!isVisited[i]) {
                isVisited[i] = true;
                combi(cnt+1, i+1);
                isVisited[i] = false;
            }
        }
    }

    private static int calculate() {
        int teamStart = 0, teamLink = 0;
        for(int i=0; i<N-1; i++) {
            for(int j=i+1; j<N; j++) {
                //i번째 사람과 j번째 사람이 true라면 start팀
                if(isVisited[i] && isVisited[j]) teamStart += (map[i][j]+map[j][i]);
                else if(!isVisited[i] && !isVisited[j]) teamLink += (map[i][j]+map[j][i]);
            }
        }

        return Math.abs(teamStart-teamLink);
    }
}
