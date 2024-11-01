import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 평범한 배낭
 * 물건 N개가 있고, 각각 무게W와 가치V를 가진다. 해당 물건을 가져가면 V만큼 즐길 수 있다.
 * 준서는 K만큼의 무게만을 넣을 수 있는 배낭을 가져간다. 최대가치를 갖도록 가방을 싸보자!
 *
 * 제한
 * 1<=N(물건의수)<=100
 * 1<=K(가져갈 무게)<=10만
 * 1<=W(각 물건 무게)<=10만
 * 0<=V(각 물건 가치)<=1000
 *
 * 풀이
 * 대표적인 냅색문제, dp[i]배열에 i kg에서 가질 수 있는 최대 가치를 저장한다.
 * 일차원배열로 만들거고, 주어진 모든 물건에 대해 반복하며 업데이트를 실행(바텀업 방식으로)
 * 점화식은 dp[i] = Math.max(dp[i], dp[i-W]+V)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //물건 수
        int K = Integer.parseInt(st.nextToken()); //최대 무게
        int[] dp = new int[K+1];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            if(weight > K) continue;
            for(int j=K; j>=weight; j--) {
                dp[j] = Math.max(dp[j], dp[j-weight]+value);
            }
        }
        System.out.println(dp[K]);
    }
}