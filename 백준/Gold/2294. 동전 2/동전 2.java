import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 동전2
 * n가지 종류의 동전이 있고, 동전을 최소로 쓰면서 가치가 k가 되도록 하고싶다.
 *
 * 제한
 * 1<=n(동전 종류)<=100
 * 1<=k(동전 가치)<=1만
 *
 * 풀이
 * 동전이 배수관계가 아니므로 그리디가 아님, 즉 DP로 풀어야한다.
 * 같은 가치의 동전이 주어질 수 있으므로 Set으로 입력받고,각 가치마다 점화식으로 구해주면 됨.
 * 예를들어 5원을 만들때 1원이 5개 필요했다고 치고, 5원에 대해 고려하는방법은
 * Math.min(5원을 사용하지않을때 = 5, 5원을사용할때=Dp[5-5]+1=1) 이런 방식!
 * (1) Set을 정렬, 0원일때를 포함하여 DP배열만들고
 * (2) 점화식을 활용해 바텀업, 현재 고려중인 coin에 대해 DP[i]=Math.min(DP[i], DP[i-coin]+1)
 * 주의할점은 초기 0이 당연히 제일 min값일테니 Arrays를 i=0일때만 0으로 하고 나머지는 Integer.MAXVALUE로 만든다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //동전의 갯수
        int k = Integer.parseInt(st.nextToken()); //목표 가치

        Set<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++) set.add(Integer.parseInt(br.readLine()));
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        int[] dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE-1); //+1을 하는경우가 있을수 있으니까
        dp[0] = 0;
        for(int coin : list) {
            for(int i=coin; i<k+1; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin]+1);
            }
        }
        System.out.println(dp[k]==Integer.MAX_VALUE-1?-1:dp[k]);
    }
}