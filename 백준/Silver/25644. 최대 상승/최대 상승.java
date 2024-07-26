import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 최대상승
 * N일간 가장 이득을 얻을수있게 주식 사고파는 날짜 정하기
 * 즉 현재 샀을때 기준으로 가장 높은가격에 팔 수 있는날을 찾자.
 * 순서대로 진행하면서 차액을 MAX로 기록하고, 최솟값도 따로 관리하면서 계산..
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int minValue=Integer.parseInt(st.nextToken()), maxBenefit=0;
        for(int i=1; i<N; i++) {
            int now = Integer.parseInt(st.nextToken());
            minValue = Math.min(now, minValue);
            maxBenefit = Math.max(now-minValue, maxBenefit);
        }
        System.out.println(maxBenefit);
    }
}