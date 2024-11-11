import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 동전 1
 * n가지 종류의 동전이 있고, 각자 가치가 다르다.
 * 이를 적당히 사용해서 가치의 합이 k원이 되도록 하고싶다.
 * 그 경우의 수를 구하시오. (구성이 같고 순서가 다른것은 같은경우임)
 *
 * 제한
 * 1<=n(종류 수)<=100
 * 1<=k(가치의 합)<=1만
 * 각 동전의 가치는 10만보다 작거나 같은 자연수
 *
 * 풀이
 * dp[i]를 만들고, 도합 i원을 만드는 경우의수의 갯수를 저장하면 됨
 * 예제) 1원 2원 5원있을때 도합 10 만들기
 * dp[1] = 1 => 1개
 * dp[2] = 1+1, 2 => 2개 (dp[2-1]에다가 2원 하나더!)
 * dp[3] = 1+1+1, 2+1 => 2개 (dp[3-1])
 * dp[4] = 1+1+1+1, 2+1+1, 2+2 => 3개 (dp[4-1]에다가 dp[4-2]-1?)
 * dp[5] = 1+1+1+1+1, 2+1+1+1, 2+2+1, 5 => 4개 (dp[5-1]에다가 5원 하나더!)
 * dp[6] = 1+1+1+1+1+1, 2+1+1+1+1, 2+2+1+1, 2+2+2, 5+1 => 5개
 * dp[7] = 1+1+1+1+1+1+1, 2+1+1+1+1+1, 2+2+1+1+1, 2+2+2+1, 5+1+1, 5+2 => 6개
 * dp[8] = 1+1+1+1+1+1+1+1, 2+1+1+1+1+1+1, 2+2+1+1+1+1, 2+2+2+1+1, 2+2+2+2, 5+1+1+1, 5+2+1 => 7개
 * dp[9] = 1+1+1+1+1+1+1+1+1, 2+1+1+1+1+1+1+1, 2+2+1+1+1+1+1, 2+2+2+1+1+1, 2+2+2+2+1, 5+1+1+1+1, 5+2+1+1, 5+2+2 => 8개
 * dp[10] = 1+1+1+1+1+1+1+1+1+1, 2+1+1+1+1+1+1+1+1, 2+2+1+1+1+1+1+1, 2+2+2+1+1+1+1, 2+2+2+2+1+1, 2+2+2+2+2
 *             5+1+1+1+1+1, 5+2+1+1+1, 5+2+2+1, 5+5 => 10개
 *
 * 원래는 처음부터 dp를 채울수있도록 계산해보려고 했는데, 안됨
 * 그렇다면 순서대로 채워보면 된다. 1로만 채우는 경우의수를 다 채우고
 * 2를 고려해서 채우고, 5를 고려해서 채운다.
 * 논리적으로 생각해보면, 1로만 채운 상태에서 2로도 채우려고 할때,
 * dp[5]를 구할경우 dp[3]의 각 경우에 2를 더해주면 5가 된다. 기존의 dp[5]에는 1로 채우는 경우의수가 들어있다.
 * 따라서 dp[i] = dp[i]+dp[i-coin]이 된다.
 *
 */
public class Main {
    static int n, k, coins[], dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //가짓수
        k = Integer.parseInt(st.nextToken()); //최종가치
        coins = new int[n];
        for(int i=0; i<n; i++) coins[i] = Integer.parseInt(br.readLine());
        dp = new int[k+1];
        dp[0] = 1;
        for(int i=0; i<n; i++) {
            int coin = coins[i];
            for(int j=1; j<=k; j++) {
                if(coin <= j) dp[j] = dp[j]+dp[j-coin];
            }
        }
        System.out.println(dp[k]);
    }
}