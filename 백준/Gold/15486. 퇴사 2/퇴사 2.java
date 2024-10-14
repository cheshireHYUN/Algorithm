import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 퇴사2
 * 오늘로부터 N+1일에 퇴사하기 위해 N일동안 최대한 많은 상담을 하려고 한다.
 * 비서는 하루에 하나씩 서로 다른 사람의 상담을 잡아놓았다.
 * 각 상담을 완료하는데 걸리는 기간 Ti와 상담했을때 받는 금액 Pi가 주어진다.
 * - 상담을 하는데 필요한 기간은 1일보다 클 수 있으므로 모든상담을 할 수는 없다.
 * - 또한 N+1일째에는 회사에 없기 때문에 이 이후에 끝나는 상담도 할 수 없다.
 * 이때 백준이가 얻을수있는 최대이익을 구하는 프로그램을 작성하세요.
 *
 * 제한
 * 1 <= N <= 150만
 *
 * 풀이
 * O^2만 되어도 시간초과가 발생함. 따라서 완전탐색이 아닌 다른방법을 시도해야 한다.
 * 그리디를 사용하기엔 현재의 선택이 이후에 영향을 미친다.. 따라서 DP접근을 고려한다.
 * DP[i] = i일의 최대이익
 * (1) i+Ti가 7일차보다 이전인지 체크, false라면 continue;
 * (2) 상담이 끝나는 날의 경우엔 DP[endDay] = Math.max(dp[endDay], dp[startDay]-1+Pi)
 *        << 즉 전날까지의 최대이익에 + Pi한게 원래보다 큰지
 *     상담이 진행중인 날의 경우엔 DP[i] = Math.max(dp[i], dp[startDay])
 *         << 따로 반복문 돌필요 없고, 어차피 순회하니까 그때 업데이트하면 됨
 *
 *  이렇게하면 한번 탐색만 하면 되므로 O(N)
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        StringTokenizer st;
        for(int startDay=1; startDay<=N; startDay++) {
            dp[startDay] = Math.max(dp[startDay], dp[startDay-1]);

            st = new StringTokenizer(br.readLine());
            int endDay = startDay+Integer.parseInt(st.nextToken())-1;
            int money = Integer.parseInt(st.nextToken());
            if(endDay > N) continue;
            dp[endDay] = Math.max(dp[endDay], dp[startDay-1]+money);
        }

        System.out.println(dp[N]);
    }
}