import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 01타일
 * 현재 타일은 00 또는 1임
 * N이 주어졌을때 지원이가 만들 수 있는 모든 가짓수를 세고, 15746으로 나눈 나머지를 출력하라.
 * 타일은 무한히 많다.
 *
 * 제한
 * 1<=N<=100만
 *
 * 풀이
 * DP를 이용해서 부분해로부터 최적해를 찾는다.
 * N=1일때, 1개 => 1
 * N=2일때, 2개 => 00,11
 * N=3일때, 3개 => 001, 100, 111
 * N=4일때, 5개 => 0000, 0011, 1001, 1100, 1111
 * N=5일때, 8개
 *         00001, 00100, 10000,
 *         11001, 10011, 00111, 11100
 *         11111
 * N=6일때, 13개
 *         000000
 *         000011 001100 110000 100100 001001 100001
 *         001111 100111 110011 111001 111100
 *         111111
 *
 * 00과 1의 조합이므로, N=5를 구할때 다음과같이 생각할수있음
 * N=3일때의 해에 00을 더하면 N=5일때의 해가 됨 (11은 이미 N=4일때의 해에 포함되어있으므로 거름)
 * N=4일때의 해에 1을 더하면 N=5일때의 해가 됨
 * DP[i]는 N=i일때 가질 수 있는 최대 가짓수
 * 따라서 DP[5]= DP[3]+DP[4]
 */
public class Main {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;

        if(N==1) System.out.println(dp[N]);
        else {
            dp[2] = 2;
            getTileCount(N);
            System.out.println(dp[N]);
        }
    }

    private static int getTileCount(int N) {
        if(dp[N] != -1) return dp[N];
        else return dp[N] = (getTileCount(N-1)+getTileCount(N-2))%15746;
    }
}