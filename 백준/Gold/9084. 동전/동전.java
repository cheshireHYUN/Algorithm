import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 동전
 * 1원 5원 10원 50원 100원 500원
 * 동전의 종류가 주어질때 주어진 금액을 만드는 모든 방법을 세는 프로그램
 * 주의 : 입력으로 주어지는게 나의 단위인거임 우리나라 화폐단위랑은 상관없음
 * 입력 : 1<=N<=20
 * 풀이 : 딱봐도 약간 DFS마냥 이전의 값을 이용해서 선택하는문젠데 DP를 이용하면 될듯
 * 그냥 냅다 만들어보면 답도 없음. 풀이 참고해보니 발상을 살짝 전환해야함
 * 1,2,5를 통해 5를 만든다고 가정했을때 1로만/1,2로만/1,2,10으로만 만들어본다
 * 그러면 규칙성이 찾아짐. DP[i] = DP[i]+DP[i-현재코인값]
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) coins[i] = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M+1]; //dp[M]=M을만들기위한 동전의 갯수
            dp[0]=1;

            for(int coin : coins){
                for(int i=0; i<=M; i++){
                    if(i >= coin) {
                        //즉 현재 코인값을 사용하지 않아도 되는 합계일 경우
                        dp[i] += dp[i-coin];
                    }
                }
            }

            sb.append(dp[M]).append('\n');
        }

        System.out.println(sb);
    }
}