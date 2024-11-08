import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 스티커
 * 스티커가 2행 n열로 배치되어있다.
 * 한장을 떼면 변을 공유하는 스티커가 모두 찢어진다.
 * 각 스티커에 점수를 매기고, 점수의 합이 최대가 되도록 스티커를 떼어낸다.
 * 즉, 점수의 합이 최대이면서 서로 변을 공유하지 않는 스티커 집합을 구한다.
 *
 * 제한
 * 1<=n(열 갯수)<=10만
 * 0<=점수<=100
 *
 * 풀이
 * 어떤 위치의 스티커를 뗼지말지 고려할때, 이전의 선택을 이어가는(대각선)것이 클지,
 * 또는 아예 이번에 스티커를 떼지않을때의 경우의수가 큰지 고르면 됨.. 즉
 * 50 10
 * 30 50
 * 의 예시에서, 1행 2열의 위치에서 가질 수 있는 값은
 * 30+10(대각선의 최댓값)으로 둘다 떼거나(점수가 양수라 뗼수있으면 떼는게 좋음)
 * 50(1행1열의 최댓값)으로 아예 떼지않거나..
 *
 * dp[i][j] = i,j의 위치까지 왔을때 가질 수 있는 최댓값.
 * dp[i][j]위치의 스티커를 뗀다/안뗀다 라는 선택지중 뭘 했을때 더 큰값을 가지는지 찾는다.
 * 안뗀다 = 이전에 어떤쪽을 선택했던지 상관없음 = Math.max(dp[1][j-1], dp[2][j-1])
 * 뗸다 = 이전에 선택한 곳에 현재이득을 더함 = dp[1이면 2, 2면 1][j-1]+arr[i][j];
 * 그리고 Math.max(뗀다, 안뗀다);
 */
public class Main {
    static int n;
    static int[][] scores, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            scores = new int[3][n + 1];
            dp = new int[3][n + 1];
            for (int i = 1; i <= 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) scores[i][j] = Integer.parseInt(st.nextToken());
            }
            sb.append(getMaxScore()).append('\n');
        }
        System.out.println(sb);
    }

    private static int getMaxScore(){
        dp[1][1] = scores[1][1];
        dp[2][1] = scores[2][1];
        for(int j=2; j<=n; j++) {
            for(int i=1; i<=2; i++) {
                int passSticker = Math.max(dp[1][j-1],dp[2][j-1]);
                int getSticker = dp[i==1?2:1][j-1]+scores[i][j];
                dp[i][j] = Math.max(passSticker, getSticker);
            }
        }
        return Math.max(dp[1][n], dp[2][n]);
    }
}