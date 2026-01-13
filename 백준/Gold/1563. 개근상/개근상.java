import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 개근상
 * 학기가 끝날무렵 출결을 보고 개근상 줄말 결정
 * 출결은 출석(O)/지각(L)/결석(A)으로 나뉜다
 * (1)지각을 두번이상 했거나 (2)결석을 세번연속으로 하면 개근상을 받을 수 없다.
 * 한학기가 며칠인지 주어졌을때(N) 개근상을 받을 수 있는 출결정보의 갯수를 세는 프로그램을 작성하라.
 *
 * 풀이
 * 완전탐색 -> N일에 해당하는 모든 순열을 구하면서 두 조건에 해당하지 않는것만 찾기를 떠올릴 수 있음.
 * 그런데 사실 이미 앞서서 지각을 두번했다면 그 뒷부분을 더 찾을 필요가 없음 -> DP로 풀 수 있을듯
 *
 * 개근상을 받기 위해 따져봐야하는 변수 -> 전체일수, 연속된결석횟수, 지각횟수
 * dp[문자열길이][연속된A횟수][L이포함된횟수]
 * (1) dp[i][0][0] = 연속A가 0이 되려면 어제의 뒤에 붙일 오늘이 o이면 되니까 어제의 후보들은
 *      dp[i-1][0][0] + dp[i-1][1][0] + dp[i-1][2][0] 인것 되는것.
 * (2) dp[i][0][1] = 오늘이 o이거나 l인거니까
 *      dp[i-1][0][1] + dp[i-1][1][1] + dp[i-1][2][1]
 *      + dp[i-1][0][0] + dp[i-1][1][0] + dp[i-1][2][0]
 * (3) dp[i][1][0]이나 dp[i][1][1]은 연속된a가 반드시 1개여야하니까(다른게오면 연속이 끊기게됨)
 *      dp[i][1][0] = dp[i-1][0][0]
 *      dp[i][1][1] = dp[i-1][0][1]
 * (4) dp[i][2][0]이나 dp[i][2][1]은 어제도 오늘도 a라는의미니까
 *      dp[i][2][0] = dp[i-1][1][0]
 *      dp[i][2][1] = dp[i-1][1][1]
 */
public class Main {
    static int n;
    static int[][][] dp;
    static int[] arr;
    static int MOD = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1][3][2]; //[전체][결석][지각]
        arr = new int[n+1];
        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;
        getDP();
        int answer = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<2; j++) answer = (answer+dp[n][i][j])%MOD;
        }
        System.out.println(answer);
    }
    private static void getDP() {
        for(int i=2; i<=n; i++) {
            dp[i][0][0] = (dp[i-1][0][0]+dp[i-1][1][0]+dp[i-1][2][0])%MOD;
            dp[i][0][1] = (dp[i-1][0][0]+dp[i-1][1][0]+dp[i-1][2][0]
                    +dp[i-1][0][1]+dp[i-1][1][1]+dp[i-1][2][1])%MOD;
            dp[i][1][0] = (dp[i-1][0][0])%MOD;
            dp[i][1][1] = (dp[i-1][0][1])%MOD;
            dp[i][2][0] = (dp[i-1][1][0])%MOD;
            dp[i][2][1] = (dp[i-1][1][1])%MOD;
        }
    }
}
