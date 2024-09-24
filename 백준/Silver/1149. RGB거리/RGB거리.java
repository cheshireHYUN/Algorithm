import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** RGB거리
 * RGB거리엔 집이 N개 있다. 1부터 N번집이 순서대로 있음
 * 집은 빨강, 초록, 파랑중 하나의 색으로 칠해야한다.
 * (1) 1번과 2번집의 색은 달라야한다
 * (2) N번집의 색은 N-1번집의 색과 달라야한다
 * (3) i(2<=N-1)번 집의 색은 i-1번, i+1번 집의 색과 달라야한다.
 * 
 * 입력 : 2<=N<=1000, 각 집을 빨/초/파로 칠하는 비용
 * 출력 : 모든 집을 칠하는 비용의 최솟값
 *
 * 풀이 : 그때그때 작은걸 선택했을경우 이후의 선택지가 엄청 작을땐 최적해를 구하지 못한다.
 * 따라서 완전탐색인데, DP를 사용해서 누적합 방식으로 연산속도를 줄인다.
 * 탑다운 즉 재귀로 풀이한다.
 * Cost가 저장된 이차원배열, 그리고 누적합을 저장할 DP 이차원배열을 생성한다.
 * DP배열의 첫번째 행을 Cost배열의 값을 통해 채운다.
 * 마지막집인 N일때부터 전개하며, DP[N][색] == 0일때 재귀한다. 아닐땐 DP값인 DP[N][색]을 리턴한다
 * 점화식은 예를들어 DP[N][R] = Cost[N][R] + Math.min(solution(N-1, G), solution(N-1, B))
 * 그리고 Dp[N][R] Dp[N][G] Dp[N][B]를 모두 확인함으로써 가장 작은 값을 출력한다.
 */
public class Main {
    static int[][] dp, cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N][3];
        cost = new int[N][3];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) cost[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int j=0; j<3; j++) dp[0][j] = cost[0][j];
        int min = Math.min(find(N-1, 0), Math.min(find(N-1, 1), find(N-1, 2)));
        System.out.println(min);
    }
    private static int find(int houseNumber, int color) {
        if(dp[houseNumber][color] == 0) { //아직 방문하지 않은 누적합
            if(color == 0) dp[houseNumber][color] = cost[houseNumber][color] + Math.min(find(houseNumber-1, 1), find(houseNumber-1, 2));
            if(color == 1) dp[houseNumber][color] = cost[houseNumber][color] + Math.min(find(houseNumber-1, 0), find(houseNumber-1, 2));
            if(color == 2) dp[houseNumber][color] = cost[houseNumber][color] + Math.min(find(houseNumber-1, 0), find(houseNumber-1, 1));
        }

        return dp[houseNumber][color];
    }
}