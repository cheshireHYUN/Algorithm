import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/** 색상환
 * 인접한 두 색을 동시에 사용하지 않는다.
 * N개의 색으로 구성된 색상환에서 어떤 인접한 두 색도 동시에 선택하지 않으면서 서로다른 K개의 색을 선택하는 경우의수
 * 경우의 수를 10억3으로 나눈 나머지를 출력한다.
 *
 * 제한
 * 4<=N<=1000
 * 1<=K<=N
 *
 * 풀이
 * 냅색문제와 유사하게 풀 수있다.
 * 1번을 칠했을 경우 -> N-2개의 색상중 K-1개를 선택해야함
 * 1번을 칠하지 않았을 경우 -> N-1개의 색상중 K개를 선택해야함.
 *
 * dp[i][j] = i개의 색상까지 중에서 j개를 선택하는 방법
 * dp[n][k] = dp[i-2][j-1]+dp[i-1][j]
 *
 */
public class Main {
    static int N,K;
    static int[][] dp;
    final static int MOD = 1000000003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        dp = new int[N+1][K+1];

        for(int i=0; i<N+1; i++) {
            dp[i][1] = i; //하나를 선택하는건 결국 모든 색상 선택하는것
            dp[i][0] = 1;
        }

        for(int i=4; i<N+1; ++i) {
            for(int j=1; j<=K; ++j) {
                dp[i][j] = (dp[i-2][j-1]%MOD + dp[i-1][j]%MOD) % MOD;
            }
        }

        System.out.println(dp[N][K]);
    }
}
