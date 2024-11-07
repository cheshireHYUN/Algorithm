import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 포도주 시식
 * 될수있는대로 많은 양의 포도주를 맛보기 위해 잔을 선택한다.
 * 1~n까지의 포도주 잔이 있고, 양이 각각 주어진다.
 * (1) 안에있는 포도주는 모두 마시며, 마신후 원위치한다.
 * (2) 연속으로 놓여있는 3잔을 모두 마실수는 없다.
 *
 * 제한
 * 1<= 포도잔의 갯수 n<= 1만
 *
 * 풀이
 * 연속 3잔은 마실 수 없음..
 * dp[i] = i위치의 포도주 차례까지 왔을때 최대로 마실수있는 양
 * 5번째 포도주를 마실 수 있는 경우는 다음과같다
 * 2 -> 3 -> 5 (즉 세번째에서 바로 다섯번째 마시기)
 * 2 -> 4 -> 5 (즉 두번째에서 4를 거쳐 5번마시기. 3을 거치면 안됨)
 * 2,5와 같은 방식도 가능하지만 최대한 많이 먹으려면 건너뛰는 잔이 적어야함.
 * 즉, dp[5] = Math.max(dp[3], dp[2]+arr[4])+arr[5];
 * 재귀로 풀 경우, dp[n]에서 시작할때 dp[n-1]을 호출하지 않으므로 바텀업
 *
 * 라고 생각했는데 반례가 있었습니다
 * 110011 이런게 있으면 4가 나와야 하는데, 00부분에서 dp값이 0이 나와버리니까 최대가 3밖에안나옴
 * 즉, 연속3잔에 함정이있다! 방법이 반드시 1칸up, 2칸up만 있는게 아니라 훨씬 전도 가능함
 * 따라서 dp값에 Math.max(dp[n-1], 점화식) 이렇게 나와줘야함.
 */
public class Main {
    static int[] cups;
    static int[] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cups = new int[10001];
        for(int i=1; i<N+1; i++) cups[i] = Integer.parseInt(br.readLine());
        dp = new int[10001];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = cups[1];
        dp[2] = cups[1]+cups[2];

        System.out.println(getMaxWine());
    }

    private static int getMaxWine() {
        int maxWine = dp[2];
        for(int i=3; i<=N; i++) {
            dp[i] = Math.max(dp[i-1],
                    Math.max(dp[i-2],dp[i-3]+cups[i-1])+cups[i]);
            maxWine = Math.max(maxWine, dp[i]);
        }
        return maxWine;
    }
}