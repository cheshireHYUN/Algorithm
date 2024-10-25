import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 동전2
 * N가지 동전이 있다. 합이 k원이 되도록 하고싶다.
 * 동시에 동전의 갯수가 최소가 되도록 하려고 할때 각 동전은 몇개라도 사용할 수 있다
 *
 * 제한
 * 1<=n<=100
 * 1<=k<=10만
 * 동전의 가치 <= 10만
 *
 * 사용가능한 동전의 최소갯수를 출력한다. 불가능한 경우에는 -1을 출력한다.
 * 우선 모든 동전이 배수의 관계가 아닐 수 있으므로 무조건 큰걸 확보하는 문제는 아니다.
 * (50,200,300,350원이 있을때 500원을 만들기위해 필요한건 200+300원이지 350+50+50+50이 아님)
 * 1,5,12를 통해 15를 만드려면 5+5+5 / 12+1+1+1이니까 답은 3
 *
 * (1) 가치가 같은 동전이 나올 수 있으므로 일단 Set에 넣은 뒤 오름차순 정렬해 리턴
 * (2) DP[i] = 총합이 i일때 최소 동전종류갯수,
 *      값을 채우는 방식은 12원 동전 사용할때 DP[15] = Math.min(DP[15-12]+1, DP[15])
 * 즉 반복문을 돌면서 채우는데, 현재 동전이 5원이면 i=5부터 시작해서 돈다.
 * dp[0]=0으로 초기화 함으로써 dp[5]=1이 되고, dp[6]=dp[1]+dp[5]인데, 만약 1이 없었다고 가정하면
 * Math.min(MAXVALUE+1, MAXVALUE)가 되어버리므로 오버플로우를 막기위해 초기화를 MAXVALUE-1로 한다
 * 즉, Math.min((MAXVALUE-1)+1, MAXVALUE)이므로 답이없는건 MAXVLAUE-1이 된다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[0] = 0;

        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++) set.add(Integer.parseInt(br.readLine()));
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        for(int coin : list) {
            for(int i=coin; i<=k; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin]+1);
            }
        }

        System.out.println(dp[k]==Integer.MAX_VALUE-1?-1:dp[k]);
    }
}