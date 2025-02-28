import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 1로 2만들기
 * 정수 X에 사용할 수 있는 연산은 세가지다.
 * (1) X가 3으로 나누어 떨어지면 3으로 나눈다
 * (2) X가 2로 나누어 떨어지면 2로 나눈다
 * (3) 1을 뺀다
 * 연산을 적절히 사용해서 1을 만들려고 할때, 최소가 되는 연산횟수를 출력하시오.
 *
 * 풀이
 * 1<=N<=100만, 0.5초
 * 그럼 N^2만되어도 시간제한에 걸림.. 따라서 logN이나 N, NlogN안에서 끊어야됨
 * DP필이 오는데?? N=10이면
 * dp[1] = 0 (1)
 * dp[2] = 1 (2 1)
 * dp[3] = 1 (3 1)
 * dp[4] = 2 (4 2 1)
 * dp[5] = 4 (5 4 2 1)
 * dp[6] = 3 (6 3 1)
 * dp[7] = 3 (7 6 3 1)
 * dp[8] = 3 (8 4 2 1)
 * dp[9] = 2 (9 3 1) or (9 8 4 2 1)
 * dp[10]= 3 (10 9 3 1) or (10 5 4 2 1)
 * dp[n]이란건
 * n이 3으로 나누어 떨어지면 3으로 나누는 연산을 추가 => dp[n/3]+1
 * n이 2로 나누어 떨어지면 2로 나누는 연산을 추가 => dp[n/2]+1
 * 무조건 이전값에 -1연산 추가 하는거도 있고 => dp[n-1]+1
 * 따라서 dp[n] = Math.min(dp[n-1]+1, (n이 2의배수면)dp[n/2]+1, (n이 3의배수면)dp[n/3]+1)
 *
 * 바텀업으로 간단히 풀어보면,
 * dp[1]=0과 dp[2]=1, dp[3]=1까지 셋팅해두고(수열까지 다른곳에 함께 저장 List배열로)
 * 그리고 n 점화식에따라 dp값을 저장하고, list배열에도 함께 저장(맨앞에 n을 추가하면 됨)
 * 어차피 수열을 저장할거라 길이가 따로 저장이 필요없어 보이긴 한데...
 * list가 크기가 넘 크니까 수열을 차라리 문자열로 저장하면 어떨까
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        //초기화
        int[] dp = new int[1000001];
        String[] values = new String[1000001];
        dp[1] = 0;
        values[1] = "1";
        dp[2] = 1;
        values[2] = "2 1";
        dp[3] = 1;
        values[3] = "3 1";

        //바텀업
        int curr = 4;
        while(true) {
            if(curr > N) break;

            int min = dp[curr-1];
            String str = values[curr-1];
            if(curr%2 == 0 && dp[curr/2] < min) {
                min = dp[curr/2];
                str = values[curr/2];
            }
            if(curr%3 == 0 && dp[curr/3] < min) {
                min = dp[curr/3];
                str = values[curr/3];
            }

            dp[curr] = min+1;
            values[curr] = curr+" "+str;
            curr++;
        }

        System.out.println(dp[N]);
        System.out.println(values[N]);

    }
}