import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 호텔
 * 도시(홍보 비용, 늘어난 고객수)
 * 홍보비용에 정수배만큼 투자 가능
 * 각 도시엔 무한고객이 존재할때 호텔의 고객을 적어도 C명 늘리기 위해 투자해야하는 돈의 최솟값
 *
 * 풀이
 * DP느낌 씨게나는디 맞네.. 근데 정수배까지 고려해야됨
 *
 * dp[i] = i명의 고객을 유치하는데 드는 최소비용
 * 탑다운으로 생각해보면, dp[i] = (dp[i], dp[i-customers]+cost)
 * 계단수 찾기와 비슷하게 생각하면 된다. 특정값을 더하기 전단계에서 값을 가져오는형태...
 * 포인트는 "이상"이니까 c+101로 만들어서 가장 작은 비용을 찾는것이 중요함.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken()); //최소 유치 고객 조건
        int N = Integer.parseInt(st.nextToken()); //도시의 갯수

        int[] dp = new int[C+101];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            for(int j=customer; j<C+101; j++) {
                //cost=3,customer=5일경우
                //dp[5] = Math.min(dp[5], cost+dp[5-5])
                //즉 5명 유치하는 최소비용 = Math.min(기존5명비용, 0명 비용+5명유치비(3))
                dp[j] = Math.min(dp[j], cost+dp[j-customer]);
            }
        }

        int result = Integer.MAX_VALUE;
        for(int i=C; i<C+101; i++) result = Math.min(result ,dp[i]);
        System.out.println(result);
    }
}
