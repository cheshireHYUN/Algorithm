import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 구간 합 구하기 5
 * NxN개의 수가 NxN개의 표에 채워져 있을때, (x1,y1)부터 (x2,y2)까지 합을 구하는 프로그램을 작성하라
 *
 * 입력
 * N은 1024까지, M은 10만까지 >> 꽤크므로 DP를 이용한다.
 * 누적합 점화식은 dp[i][j] = dp[i-1][j]+dp[i][j-1] - dp[i-1][j-1] + arr[i][j]
 * 구간합 점화식 dp(x2,y2) - dp(x1-1,y2) - dp(x2, y1-1) + dp(x1-1,y1-1) 이런식으로 될듯
 */
public class Main {
    public static int N,M;
    public static int[][] arr,dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];
        dp = new int[N+1][N+1];
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        //누적합 채우기
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
            }
        }


        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            //dp(x2,y2) - dp(x1-1,y2) - dp(x2, y1-1) + dp(x1-1,y1-1)
            int result = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
            sb.append(result).append('\n');
        }

        System.out.println(sb);
    }

}
