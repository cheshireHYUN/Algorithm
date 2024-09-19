import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 내리막길
 * 지도의 각 칸에 높이가 쓰여있다. 상하좌우로 이동할수있다.
 * 출발은 0,0 도착은 n,n이다.
 * 항상 높이가 더 낮은 지점으로만 이동해 목표지점까지 가고자 한다.
 * 경로의 갯수?
 * 풀이 : 엥 그냥 dfs 쓰면되는거 아닌가 -> 시간초과나네요
 * a에서 b,c로 갈수있고, b에서 목적지까지 5가지, c에서 목적지까지 2가지라면 a에서 목적지까지는 7이다.
 * 즉 DP를 활용해서 최적화를 해줘야한다
 */
public class Main {
    static int M,N;
    static int[][] map, dp;
    static int[] dx = new int[]{0,0,1,-1}, dy = new int[]{1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); //세로
        N = Integer.parseInt(st.nextToken()); //가로
        map = new int[M][N];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[M][N];
        for(int i=0; i<M; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0,0));

    }

    static int dfs(int x, int y){
        //이미 계산된 점일경우
        if(dp[x][y] != -1) return dp[x][y];

        //방문체크
        dp[x][y] = 0;

        //목표지점에 도달한 첫번째 경우의 수
        if(x == M-1 && y==N-1){
            dp[x][y] = 1;
            return dp[x][y];
        }

        for(int i=0; i<4; i++){
            int nextX = x+dx[i];
            int nextY = y+dy[i];
            if(nextX<0 || nextY<0 || nextX>=M || nextY>=N) continue;
            //내리막길이면 진행하며 메모이제이션
            if(map[x][y] > map[nextX][nextY]){
                dp[x][y] += dfs(nextX, nextY);
            }
        }

        return dp[x][y];
    }
}