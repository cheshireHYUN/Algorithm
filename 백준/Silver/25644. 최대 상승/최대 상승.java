import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 최대상승
 * N일간 주가가 주어지고, 언제사서 언제팔면 최대이득을 얻을지 구해라. (사자마자 팔수있으니 최소는 0)
 * (풀이) 완전탐색하면 시간초과 날거고, 그리디로 푸는 문제..
 * 왼쪽부터 순회하면서 가장 작은값을 찾는 동시에 최솟값 계산도 하면 된다.
 * 즉 3 10 1 5 이런식으로 나오면 i=1일때 최소는 3과 최대차이는 7, i=2일때 최소는 1 최대는 7, i=3일때 최소는 1 최대는 여전히 7!
 * 즉.. 현재 가능한 최솟값에서 현재가격을 뺐을때 최대인것을 각각 남기면 됨.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int minValue = Integer.parseInt(st.nextToken());
        int benefit = 0;
        for(int i=1; i<N; i++){
            int curr = Integer.parseInt(st.nextToken());
            benefit = Math.max(benefit, curr-minValue);
            minValue = Math.min(minValue, curr);
        }
        System.out.println(benefit);
    }
}