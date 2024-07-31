import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 연속합
 * n개의 정수로 이루어진 임의의 수열이 있다. 연속된 몇개의 수를 선택해서
 * 구할 수 있는 합중 가장 큰 합을 구하려고 한다.
 * DP를 사용한 기본적인 문제이다. 연속된걸 찾는거니까 얼마나 연속해야할까?
 * 10 -4를 더했을때 6이나오고, 이건 -4부터 시작하는것보단 크다.
 * 6+3=9이고, 3부터 시작하는것 보다 크다. 이런식으로 구하는거다.
 * 즉 dp[i-1]+arr[i] > arr[i]일때 dp[i] = dp[i-1]+arr[i]
 * 만약 dp[i-1]+arr[i] <= arr[i]라면 dp[i] = arr[i]
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        dp[0] = arr[0];
        int max = arr[0];
        for(int i=1; i<N; i++){
            if(dp[i-1]+arr[i] > arr[i]) dp[i] = dp[i-1]+arr[i];
            else dp[i] = arr[i];
            max = Math.max(max,dp[i]);
        }
        System.out.println(max);
    }
}