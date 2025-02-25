import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 알약
 * 총 N개의 약이 있고, 매일 약을 먹는데, 한조각을 꺼내면 W를 반조각을 꺼내면 H를 보낸다.
 * 2N일이 지나 2N인 문자열이 되었을때 가능한 서로 다른 문자열의 갯수는 몇개?
 *
 * 풀이
 * 트리형식으로 전개했을때, 각 경우의 수에서 w와 h는 항상 0이상이며, 둘다 0이면 종료된다.
 * 반개를 먹는 경우의 수는 한개짜리는 그대로, 반개짜리는 -1
 * 한개를 먹는 경우의 수는 한개짜리는 -1개, 반개짜리는 +1
 * 또한 위 식을 DP를 활용해서 계산할 수 있다.
 * dp[w][h] = dp[w-1][h+1] + dp[w][h-1]
 *
 * 어렵게 생각하지말자.
 * 트리를 그리고, 그 트리를 바텀업 방식으로 갯수를 세는것이다.
 * 트리의 마지막 노드를 보면, 결국 갯수가 N=3일때 (N-3,0)인것들만 유효하다
 * 따라서 종료조건을 (0,0)으로 두고 이때 return 1로 갯수를 세어준다(둘중하나가 0미만이면 return 0)
 * 즉 (N-3, 1)의 갯수는 (N-3,0)과 (N-4,2)의 결과값의 합이 되는것이다.
 * 즉 이를 점화식으로 세운게 dp[w][h] = dp[w][h-1] + dp[h-1][h+1]
 */
public class Main {
    public static long[][] dp = new long[31][31];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            sb.append(dfs(N,0)).append('\n');
        }
        System.out.println(sb);
    }

    //dp[w][h] = dp[w-1][h+1] + dp[w][h-1]
    public static long dfs(int w, int h) {
        if(w<0 || h<0) return 0; //불가능 조건
        if(w==0 && h==0) return 1; //종료조건
        if(dp[w][h]!=0) return dp[w][h]; //이미 값이 있으면 출력

        dp[w][h] = dfs(w-1, h+1)+dfs(w,h-1);
        return dp[w][h];
    }
}