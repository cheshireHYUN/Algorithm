import javax.print.attribute.standard.PagesPerMinute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 극장좌석
 * 1번부터 N번까지 숫자가 적힌 좌석배열이 있고, 사람들은 입장권에 표시된 자리에 앉는다.
 * 조건1) 자기의 바로 왼쪽오른쪽으로는 옮길 수 있다.
 * 조건2) VIP회원은 반드시 자기자리에 앉아야한다
 * 입장권이 매진된 상황에서 VIP회원의 좌석번호가 주어졌을때, 사람들이 좌석에 앉는 서로 다른 방법의 가짓수를 구하라.
 *
 * 제한
 * 1<=N(좌석의 갯수)<=40, 0<=M(고정석의 갯수)<=N
 * 방법의 가짓수는 21만 이하 즉 int형
 *
 * 풀이
 * 수열 등 완탐을 쓰면 시간복잡도가 터지기 때문에 DP로 푼다. N이 작을때부터 계산해보면 된다.
 * N=1일때 => (1)
 * N=2일때 => (12) (21)
 * N=3일때 => (123) (213) (132)
 * N=4일때 => (1234) (2134) (1324) (1243)
 * N=5일때 => (12345) (21345) (13245) (12435) (12354) (21435) (21354) (13254)
 * N=6일때 => (123456) (213456) (132456) (124356) (123546) (123465) (214356) ...
 * VIP가 없을경우 위와 같고, VIP를 4로 상정한뒤 위에서 틀린 수열을 제거하면 규칙이 보인다.
 * 1부터 6까지 순서대로 [1,2,3,3,3,6]의 갯수를 가진다.
 *
 * 즉, DP[N] = 좌석의 갯수가 N일때 가지는 방법의 갯수일 경우
 * DP[N] = DP[N-1]+DP[N-2]이다. 이때 VIP와 관련한 두가지 조건을 가진다
 * 조건 1. DP[N]에서 N이 VIP의 자리번호일 경우, DP[N] = DP[N-1]이다.
 * 조건 2. DP[N]에서 N이 VIP의 자리번호+1일 경우, DP[N] = DP[N-1]이다(오른쪽 교환도 불가능하게)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] vipNumber = new boolean[41];
        for(int i=0; i<M; i++) {
            vipNumber[Integer.parseInt(br.readLine())] = true;
        }

        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;

        int curr = 2;
        while(curr <= N) {
            if(vipNumber[curr] || vipNumber[curr-1]) dp[curr] = dp[curr-1];
            else dp[curr] = dp[curr-1]+dp[curr-2];
            curr++;
        }

        System.out.println(dp[N]);
    }

}
