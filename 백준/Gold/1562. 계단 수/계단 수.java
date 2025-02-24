import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 계단수
 * 인접한 모든자리의 차이가 1인수를 계단수라고 한다.
 * N이 주어질때 길이가 N이면서 0부터 9까지 숫자가 모두 등장하는 계단수가 총 몇갠지 구하는 프로그램을 구하라(0으로 시작X)
 * N은 1이상 100이하이다.
 * 첫줄에 정답을 10억으로 나눈 나머지를 출력한다.
 *
 * 풀이
 * (힌트) 비트마스킹 + DP
 * 우선 n번째 자리에 k라는 수가 오기 위해서는 n-1번 자리에 k+1이나 k-1이 있어야 한다.
 * 따라서 dp[n][k] = dp[n-1][k-1]+dp[n-1][k+1]이 된다.
 * 다만 조건이 하나 있는데, 0~9까지 모든 수를 한번씩 이용해야한다는것이다.
 * 따라서 어떤 수를 사용했었는지 비트마스킹을 통해 기록한다.
 * 비트마스크의 각 자리는 특정 숫자를 사용했는지 안했는지를 나타낸다. (0000001110)은 1,2,3 숫자를 사용한것
 *
 * 따라서 dp배열을 3차원 배열로 생성하여 비트마스크 정보를 나타내는 인덱스를 만들어준다.
 * dp[n][k][visit | (1<<k)] = dp[n-1][k-1][visit] + dp[n-1][k+1][visit]
 * 여기서 visit | (1<<k)의 의미 : visit은 현재까지 방문한 상태값이고, (1<<k)는 k번째 비트가 1인값임. 따라서 OR연산을 통해 k번째 비트를 visit에 추가함.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N+1][10][1<<10];
        int MOD = 1000000000;
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for(int i=2; i<=N; i++) {
            for(int j=0; j<=9; j++) {
                for(int k=0; k<(1<<10); k++) {
                    if(j==0) {
                        //올수있는 숫자가 0일경우, 올수있는 수는 0또는 1
                        dp[i][0][1<<0 | k] = (dp[i][0][1<<0 | k] + dp[i-1][1][k]) % MOD;
                    } else if(j==9) {
                        //올수있는 숫자가 9일경우 올수있는 수는 9또는 8
                        dp[i][9][1<<9 | k] = (dp[i][9][1<<9 | k] + dp[i-1][8][k]) % MOD;
                    } else {
                        dp[i][j][1<<j | k] = (dp[i][j][1<<j|k] + dp[i-1][j-1][k] + dp[i-1][j+1][k]) % MOD;
                    }
                }
            }
        }
        int ans = 0;
        for(int i=0; i<10; i++) ans = (ans+dp[N][i][(1<<10)-1]) % MOD;
        System.out.println(ans);
    }
}