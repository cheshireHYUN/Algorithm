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
 * 근데 이렇게 푸니까 시간이 꽤나 소요된다~~
 * 그냥 애초에 DP값을 모두 구하는게 더 빠를 수있음 -> 같은부분에 대한 연산이 더 줄어들테니까...!!!
 * (1) i=j일때 전부 true
 * (2) i와 j가 1 차이일때 두 값이 같으면 true, 다르면 false
 * (3) 이중for문에서 우선 문자열 길이를 기준으로 3~N까지 돌면서 시작위치 전부에 대한 팰린드롬 여부를 검사해 저장
 *      즉, arr[시작점][시작점+문자열길이]의 값이 arr[시작점]과 같다면, dp[시작점+1][시작점+문자열길이-1]이 true면 true임
 */
public class Main {
    static boolean[][] dp;
    static int[] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new boolean[N+1][N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());
        setPalindrome();
        StringBuilder sb = new StringBuilder();
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            sb.append(dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] ? 1 : 0).append('\n');
        }
        System.out.print(sb);
    }

    public static void setPalindrome() {
        for(int i=1; i<=N; i++) dp[i][i] = true;
        for(int i=1; i<N; i++) {
            if(arr[i] == arr[i+1]) dp[i][i+1] = true;
            else dp[i][i+1] = false;
        }
        for(int i=2; i<N; i++) { //시작위치를 뺀 부분문자열의 길이(즉 길이3인것들부터 dp채우려고 함)
            for(int j=1; j<=N-i; j++) { //부분문자열의 시작위치
                if(arr[j]==arr[j+i] && dp[j+1][j+i-1]) { //시작위치가 끝위치와 값이 같고, 그사이의 팰린드롬dp가 true하면
                    dp[j][j+i] = true;
                }
            }
        }
    }
}