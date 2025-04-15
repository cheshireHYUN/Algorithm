import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 동물원
 * 가로두칸 세로N칸인 우리가 있다.
 * 사자를 우리에 가둘 때, 가로세로로 붙어있으면 안된다.
 * 사자를 배치하는 경우의수는 몇가지인지? (사자를 한마리도 배치하지 않는 경우도 상정)
 * 경우의수를 9901로 나눈 나머지를 출력하라.
 *
 * 제한
 * 1<=N(우리의 크기)<=10만
 *
 * 풀이
 * 어려운DP였다... 그림을 잘 구조화해 그려봐야 한다.
 * N=1일때의 경우 => 없음, (1), (2) => 3개가 된다
 * N=2일때의 경우 => 없음, (1), (2)
 * 새 칸 왼쪽 채움 =     (2,3) (3)
 *       오른쫏   =     (1,4) (4) => 도합 7개가 된다.
 *
 * N=3일때의 경우에도
 * 새칸을 채우지 않는 경우 => N=2일때에 슬롯추가 형태가 되고(7개)
 * 새 칸의 왼쪽을 채우는 경우 => 2x2쪽 모양을 보면 N-2의 모양 + N-1에서 N-2모양을 뺀 나머지의 반
 * 새 칸의 오른쪽을 채우는 경우 => 2x2쪽 모양을 보면 N-2의 모양 + N-1에서 N-2모양을 뺀 나머지의 반
 *
 * 즉, DP로 표현허면 dp[i] = i행을 안채움 + i행을 채움
 *                      = dp[i-1] + 2*(dp[i-2]+(dp[i-1]-dp[i-2]/2)
 *                      = dp[i-1] + 2*dp[i-2]+dp[i-1]-dp[i-2]
 *                      = 2*dp[i-1]+dp[i-2]
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N==1) System.out.println(3);
        else {
            int[] dp = new int[N + 1];
            dp[1] = 3;
            dp[2] = 7;
            for(int i=3; i<=N; i++) {
                dp[i] = (2*dp[i-1]+dp[i-2])%9901;
            }
            System.out.println(dp[N]);
        }
    }
}