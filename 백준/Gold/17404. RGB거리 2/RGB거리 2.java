import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** RGB 거리2
 * RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수있고, 1번부터 N번집이 있다.
 * 집은 빨/초/파중 하나로 칠하며, 각 색의 비용이 주어졌을때 규칙을 만족하게 칠해야한다
 * 1. 1번집의 색은 2,N번집의 색과 같지 않아야한다.
 * 2. N번집의 색은 N-1번, 1번집의 색과 같지 않아야한다.
 * 3. i(2<=i<=N-1)번 집의 색은 양옆집의 색과 같지 않아야한다.
 * 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값?
 * 
 * 풀이
 * 문제를 잘못 이해해서 풀이에 오래걸렸다 주의할점은 3번이 양옆이 서로 달라야하는게 아니라 다르기만 하면된단거.
 * 그럼 기본적으로 dp[i][j] = i번째 집을 칠할때 j색을 사용할경우 최소누적비용
 * 그럼 dp[i][R] = Math.min(dp[i-1][G],dp[i-1][B])+cost를 사용한다.
 * 1번과 2번은 사실상 같은 조건인데, 마지막 집이 같은색을 선택하지 않으려면, 애초에 DP를 세번에 거쳐 진행할 필요가 있다.
 * 즉, 첫번째 집을 R로 칠하는 DP를 구하며 초기 R=cost, 나머지는 최대cost+1인 1001을 넣는다.
 * 이상태로 DP값을 채운다면 결국 R은 확정되어있는거니까 Math.min(dp[N][G], dp[N][B])를 선택하면 됨.
 *
 */
public class Main {
    private static final int R=0, G=1, B=2, maxSize=1001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) cost[i][j]=Integer.parseInt(st.nextToken());
        }

        int minCost = Integer.MAX_VALUE;
        for(int i=0; i<3; i++) {
            int[][] dp = new int[N][N];
            //시작점의 색깔만 cost 대입, 나머지 색깔은 최댓값 저장
            for(int j=0; j<3; j++) {
                if(i==j) dp[0][j] = cost[0][j];
                else dp[0][j] = maxSize;
            }
            
            //dp 채우기
            for(int j=1; j<N; j++) {
                dp[j][R] = Math.min(dp[j-1][G],dp[j-1][B])+cost[j][R];
                dp[j][G] = Math.min(dp[j-1][R],dp[j-1][B])+cost[j][G];
                dp[j][B] = Math.min(dp[j-1][R],dp[j-1][G])+cost[j][B];
            }

            int min = Integer.MAX_VALUE;
            if(i==R) min = Math.min(dp[N-1][G],dp[N-1][B]);
            if(i==G) min = Math.min(dp[N-1][R],dp[N-1][B]);
            if(i==B) min = Math.min(dp[N-1][R],dp[N-1][G]);

            minCost = Math.min(minCost,min);
        }

        System.out.println(minCost);
    }
}
