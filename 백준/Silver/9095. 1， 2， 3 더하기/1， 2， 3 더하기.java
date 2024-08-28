import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

/** 1,2,3 더하기
 * 정수 4를 1,2,3의 합으로 나타내는 방법은 총 7가지다.
 * 합을 나타낼때는 수를 1개 이상 사용해야한다.
 * 정수 n이 주어졌을때 n을 1,2,3의 합으로 나타내는 방법의 수를 구하는 프로그램 작성하라
 *
 * 풀이 : dp배열을 1~4까지 작성해보면 규칙을 찾을 수 있다.
 * 즉, dp[1] dp[2] dp[3]을 만들면 그 안의 값은 당연히 각각 1,2,3이다
 * dp[4]의 경우 1+1+1+1의 1+1+1은 dp[3]에서 찾을수있고, 1+2+1의 1+2도 dp[3]에서 찾을수있다.
 * 즉 dp[1]dp[2]dp[3]의 원소로 dp[4]를 만들 수 있다. 규칙성을 바탕으로 세운 점화식은 다음과 같다.
 * dp[n] = dp[n-1]+dp[n-2]+dp[n-3]
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[12];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        int[] nums = new int[N];
        int max = 0;
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(br.readLine());
            max = Math.max(nums[i], max);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=4; i<=max; i++) dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
        for(int i : nums) sb.append(dp[i]).append('\n');
        System.out.println(sb);
    }
}