import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 팰린드롬?
 * 시간복잡도 터져서 그냥 구현은 안됨
 * DP[i][j], i는 시작점 j는 끝점으로 두고,
 * (1) i=j이면 팰린드롬이다.
 * (2) |i-j|=1이면 arr[i]=arr[j]여야 팰린드롬이다.
 * (3) DP[i][j]값이 null이면 투포인터?로 비교하면서 드가고, 만약 이미 true인데 찾으면 맞고 종료 false면 팰린드롬아님
 */
public class Main {
    static Boolean[][] dp;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new Boolean[N+1][N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            boolean b = isPalindrome(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append(b ? 1 : 0).append('\n');
        }
        System.out.print(sb);
    }

    public static boolean isPalindrome(int start, int end) {
        if(dp[start][end] != null) return dp[start][end];
        if(start == end) return dp[start][end] = true;
        if(Math.abs(start-end) == 1) {
            if(arr[start]==arr[end]) return dp[start][end] = true;
            else return dp[start][end] = false;
        }
        //투포인터로 비교 - dp값이 있을때까지 비교..
        int s=start, e=end;
        while(start < end) {
            if(dp[start][end] == null) {
                if(arr[start] == arr[end]) {
                    start++;
                    end--;
                }
                else return dp[start][end]=false;
            }
            else return dp[start][end];
        }
        return dp[s][e] = true;
    }
}
