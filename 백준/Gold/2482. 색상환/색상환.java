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
 * 1번을 칠했다 -> 2번을 못칠함 -> 남은 N-2개에서 k-1개를 칠해야한다
 * 1번을 안칠했다 -> N-1개에서 k개를 칠해야한다.
 *
 * 따라서 dp[i][j] = dp[i-2][j-1]+dp[i-1][j]
 *
 * 그런데 이렇게 선형으로 dp를 채우고 나서,
 * 결국 우리는 원형그래프에 대한 답을 만들어야함.
 * 따라서 원형그래프에선 1번을 칠하면 2,N번 둘다 못칠하니까 -> 남은 N-3개에서 k-1개를 칠한다.
 * 결국 답은 dp[n-3][k-1]+dp[n-1][k]
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

        dp[1][0] = 1;
        dp[1][1] = 1;

        for(int i=2; i<N+1; i++) {
            for(int j=1; j<K+1; j++) {
                if(j==1) dp[i][j] = i;
                else if(i>j) dp[i][j] = (dp[i-2][j-1]%MOD + dp[i-1][j]%MOD) % MOD;
            }
        }

        if(K == 1) System.out.println(N);
        else System.out.println((dp[N-3][K-1]+dp[N-1][K])% MOD);
    }
}
