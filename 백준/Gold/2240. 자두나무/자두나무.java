import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 자두나무
 * 매 초마다 두 나무중 하나의 나무에서 열매가 떨어진다.
 * 떨어질때 그 아래 서있으면 먹을 수 있다.
 * 자두는 T초동안 떨어진다. 최대 W번만 움직여서 먹을 수 있는 최대 자두 양을 구하라.
 * (자두는 초기에 1번나무에 있다.)
 *
 * 풀이
 * dp삘이 오는데요? dp[t][w] = t초까지 w번 움직였을때 먹을 수 있는 자두의 최대 갯수
 * 그래프를 그려보면 
 * 1초에 자두는 2 => dp[1][0]=0, dp[1][1]=1 이고
 * 이 후부턴 1초전 dp값에다 이동X일때와 이동O일때를 비교해서 더 큰값을 dp에 저장하는 방식이다.
 * 2초에 자두는 1 => dp[2][0] => dp[1][0]+(현재위치가 자두떨어지는 나무면 +1)
 *                 dp[2][1] = MAX(dp[1][1]+(자두떨어지면+1), dp[1][0])
*
 *  즉 dp[t][w] = Math.max(dp[t-1][w]+(자떨1), dp[t-1][w-1]+(자떨1))
 *  자두가 떨어지는 나무와 현재위치를 비교하는 법 : w값이 홀수면 2번나무 짝수면 1번나무임
 */
public class Main {
    static int T,W;
    static Integer[][] dp;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        tree = new int[T+1];
        for(int i=1; i<=T; i++) tree[i] = Integer.parseInt(br.readLine());
        dp = new Integer[T+1][W+1];

        for(int i=0; i<=W; i++) dp[0][i] = 0;

        int max = 0;
        for(int i=1; i<=T; i++) {
            for(int j=0; j<=W; j++) {
                int fruit = 0;
                if(j%2==0 && tree[i]==1) fruit = 1;
                if(j%2!=0 && tree[i]==2) fruit = 1;

                if(j-1 < 0) dp[i][j] = dp[i-1][j]+fruit;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1])+fruit;
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max);
    }
}