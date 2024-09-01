import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 먹을것인가 먹힐것인가
 * 시작시간 : 12:34
 * A는 B를 먹는다. A는 자기보다 작은 먹이만 먹을 수 있따.
 * 두 생명체 A와 B의 크기가 주어졌을때 A크기가 B보다 큰 쌍이 몇개인지 구하라.
 *
 * 입력값 : N,M은 1이상 20000이하
 * 풀이 : 완전탐색이라고 가정하면 O(NM) 즉 400,000,000 => 4억으로 시간초과
 * 그렇다면 어디서 시간을 줄일 수 있을까? => 일정숫자보다 작은 갯수를 저장하고 그걸 이용하면 됨
 * 1. A와 B를 오름차순 정렬한다. dp[N+1], result=0
 * 2. A에서 하나 꺼낸 뒤 기준수로 두고, B에서 A보다 작은 수 만큼 index++ 한다,
 * 3. 반복문이 다 돌면 dp배열에 해당 dp[i+1] = dp[i]+index 를 넣는다. (현재 i번째를 dp[i+1]에 저장)
 * 4. 즉, 이전에 셌던 작은 수는 다시 세지 않는다.
 * 이렇게하면 시간복잡도가 줄텐데 계산은 어케하지...
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            int aCnt = Integer.parseInt(st.nextToken());
            int bCnt = Integer.parseInt(st.nextToken());
            int a[]=new int[aCnt], b[]=new int[bCnt],
                    dp[]=new int[aCnt+1], result=0;
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<aCnt; i++) a[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<bCnt; i++) b[i] = Integer.parseInt(st.nextToken());
            //a,b 오름차순 정렬
            Arrays.sort(a);
            Arrays.sort(b);
            for(int i=0; i<aCnt; i++){
                int index = 0;
                for(int j=dp[i]; j<bCnt; j++){
                    if(a[i] > b[j]) index++;
                    else break;
                }
                dp[i+1] = index+dp[i];
                result += dp[i+1];
            }
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }
}