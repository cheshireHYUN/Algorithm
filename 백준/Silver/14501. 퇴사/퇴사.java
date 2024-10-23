import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 퇴사
 * 상담원으로 일하는 백준이가 퇴사를 하려고 한다,
 *  N+1일째 되는날 퇴사를 하기 위해 N일동안 최대한 많은 상담을 하려고 한다.
 *  하루에 하나씩 서로 다른 사람의 상담을 잡아놓았다.
 *  상담을 적절히 했을때 백준이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하라.
 *
 *  제한
 *  1<=N<=15
 *  1<=T<=5, 1<=P<=1000
 *
 *  풀이
 *  DP를 활용한다.
 *  (1) DP배열은 모두 0으로 초기화
 *  (2) DP[1]=10이라고 했을때, 2~4일(30원)이라면 DP[2]=DP[3]=10. DP[4]=10+30인것
 *      즉, 매 순회마다 일단 DP값을 직전값으로 바꿔치기 해준다.
 *  (3) 도착점 DP[i] = i번째날에 가질수있는 최대이익 = Math.max(DP[i], 시작점+Pi)
 *  
 *  즉 각 순회를 돌때 DP[i]는 이전의 최대이익으로 대체한 뒤 업데이트를 진행한다. 
 *
 *  이렇게하면 O(N)으로 최대 이익을 찾을 수 있다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int max = 0;
        int[] DP = new int[N+1];
        StringTokenizer st;
        for(int start=0; start<N; start++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            DP[start+1] = Math.max(DP[start+1], DP[start]);

            if(start+t > N) continue;
            DP[start+t] = Math.max(DP[start+t], DP[start]+p);
            max = Math.max(max, DP[start+t]);
        }
        System.out.println(max);
    }
}