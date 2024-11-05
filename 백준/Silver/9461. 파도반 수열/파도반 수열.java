import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 파도반 수열
 * 삼각형이 나선모양으로 놓여져있고, 첫 삼각형의 변의 길이는 1이다.
 * 나선의 가장 긴변의 길이가 k이고, k를 변으로 하는 정삼각형을 계속 추가한다.
 * P(N)은 나선에 있는 정삼각형 변의 길이이며, P(10)은 1,1,1,2,2,3,4,5,7,9 이다.
 * N이 주어졌을때 P(N)을 구하는 프로그램을 작성하라.
 *
 * 제한
 * 1<=N<=100
 *
 * 풀이
 * 1
 * 1
 * 1
 * 2
 * 2
 * 3(1+2)
 * 4(1+3)
 * 5(1+4)
 * 7(2+5)
 * 9(2+7)
 * 12(3+9)
 * 16(4+12)
 * 21(5+16)
 *
 * 규칙은 A+B인데, A는 자기보다 5번째 전의 숫자이고, B는 직전숫자이다.
 * 즉 DP[i] = i번째 삼각형의 한변의 길이, 점화식은 DP[i]=DP[i-5]+DP[i-1]
 * 여러 테스트케이스에 대해 답을 구하는것임, 탑다운 형식으로 구한다.
 * 주의할점은, N=100에 가까워질수록 값이 아주 커지므로 int범위를 넘을 수 있다는것.
 */
public class Main {
    static long[] dp = new long[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        Arrays.fill(dp, -1);
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<tc; i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(getLength(N)).append('\n');
        }
        System.out.println(sb);
    }

    private static long getLength(int N) {
        if(dp[N] != -1) return dp[N];
        else return dp[N] = getLength(N-5)+getLength(N-1);
    }
}