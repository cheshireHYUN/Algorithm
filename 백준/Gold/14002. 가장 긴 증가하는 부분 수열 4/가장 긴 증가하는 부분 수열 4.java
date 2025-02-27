import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/** 가장 긴 증가하는 부분수열 4
 * 수열 A가 주어져을때 가장 긴 증가하는 부분수열을 구하세요.
 * 
 * 풀이
 * 수열의 길이 자체는 이전에 했던것처럼 len을 순차적으로 저장하다 더 작은 수가 나올경우 이분탐색으로 lowerbound에 끼워넣는 방법이 있다.
 * 그러나 이경우 수열자체 출력은 불가능함.
 * 따라서 DP를 활용해서 문제를 풀어보자.
 *
 * dp[i]는 arr[i]이하 입력값에서 만들 수 있는 증가하는 수열의 최대길이이다
 * arr = 10 20 10 30 20 50
 *  dp = 1  2  1  3  2  4
 * 위 dp배열을 보면, 결국 dp[i]값보다 작은 dp[i]들 중에서 가장 큰값에 +1을 해주는것이 됨(당연히 i자체도 기준 i보다 작아야함)
 * 따라서 이를 활용해 길이문제는 해결!
 * 수열 자체는 Stack을 활용해서 출력할 수 있다.
 * 잘생각해보면, 결국 위 dp의 값은 특정 수열의 인덱스순서라고도 볼 수 있다.
 * 따라서 dp[i]의 값이 4-3-2-1이 되도록 Stack에 넣고 출력(뒤집으면) 된다. reverse 해도 되고..
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[A];
        int[] dp = new int[A];
        for(int i=0; i<A; i++) arr[i] = Integer.parseInt(st.nextToken());
        dp[0] = 1;
        int maxLen = 1, maxIndex = 0;
        for(int i=1; i<A; i++) {
            int max = 0;
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i]) max = Math.max(max, dp[j]);
            }
            dp[i] = max+1;
            maxLen = Math.max(maxLen, dp[i]);
            maxIndex = i;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(maxLen).append('\n');

        int[] lis = new int[maxLen];

        int count = 0;
        for(int i=0; i<A; i++) {
            if(dp[i] == maxLen) {
                //가장 큰 값을 우선 저장함
                lis[count++] = arr[i];
                for(int j=i; j>=0; j--) {
                    //현재 가장 큰 값보다 인덱스도 작고 값도 작으면서 dp값이 -1크기인것 찾아 저장
                    if(arr[j]<arr[i] && dp[i]-1 == dp[j]) {
                        lis[count++] = arr[j];
                        dp[i]--;
                        arr[i] = arr[j];
                    }
                }
                break;
            }
        }

        for(int i=maxLen-1; i>=0; i--) sb.append(lis[i]).append(' ');
        System.out.println(sb);
    }
}