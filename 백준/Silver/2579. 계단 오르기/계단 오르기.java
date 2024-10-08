import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 계단 오르기
 * 계단 오르기 게임은 계단 아래 시작점부터 계단 꼭대기에 위치한 도착점까지 가는 게임이다.
 * 각 계단엔 점수가 있으며, 밟으면 해당 점수를 얻게 된다.
 * - 계단은 한번에 하나 또는 두개씩 오를 수 있다.
 * - 연속된 세계의 계단을 모두 밟아선 안된다
 * - 마지막 도착계단은 반드시 밟아야한다.
 * 이 게임에서 얻을 수 있는 총 점수의 최댓값?
 *
 * 제한
 * 계단 수 <= 300
 * 점수 <= 10000
 *
 * 풀이
 * DP를 사용해 최댓값을 찾아가는 방식으로 전개한다.
 * dp[n]의 경우 dp[n-1]과 dp[n-2]의 최댓값을 찾아서 +arr[n]을 하면 될 줄 알았는데,
 * 조건의 "연속된 세개의 계단"이 문제가 됐다.
 * 이건 다시 생각해보면 간단하다. 우선 두칸 전에서 온 dp[n-2]는 그대로 +arr[n]을 한다.
 * 그러나 한칸 전에있는것에 바로 더하기엔, 연속되었을 확률이 있다.
 * 따라서 그 칸에 접근하려면 2-1-1(n)이어야 하므로 n-3에서 +2한 뒤 +1로 현재에 이를때만 가능하다.
 * 즉, dp[n-3]+arr[n-1]에 arr[n]을 더하면 된다!
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] dp = new int[count+1];
        int[] arr = new int[count+1];

        for(int i=1; i<count+1; i++) arr[i] = Integer.parseInt(br.readLine());
        dp[1] = arr[1];
        if(count>=2) dp[2] = arr[1]+arr[2];
        for(int i=3; i<count+1; i++) {
            dp[i] = Math.max(dp[i-2], dp[i-3]+arr[i-1])+arr[i];
        }

        System.out.println(dp[count]);
    }
}