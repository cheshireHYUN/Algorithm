import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 가장 긴 바이토닉 부분수열
 * 수열S가 어떤 수 Sk를 기준으로  S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN를 만족한다면 그 수열을
 * 바이토닉 부분수열이라고 한다. 수열이 주어졌을때 부분수열중 바이토닉 부분수열이면서 가장 긴 수열의 길이를 구하라.
 *
 * 입력
 * 1<=N(수열의 크기)<=1000, 1<Ai(수열을 이루는 수)<=1000
 *
 * 풀이
 * 가장긴부분수열(LIS)를 활용해서 쉽게 풀 수 있는 문제이다.
 * 바이토닉부분수열이란 오름차순한번, 내림차순한번 있는 수열이다.
 * 그럼 특정 인덱스에 대해 오름차순 최대길이+ 내림차순 최대길이를 해주면 되는것이다.
 * 오름차순 최대길이는 LIS를 그대로 쓰면되고, 내림차순 최대길이는 순서를 거꾸로 탐색하는 LIS이다.
 * 따라서 왼>오로 LIS한번, 오>왼으로 LIS한번씩 한 뒤 두 값을 더해주면 해당 인덱스의 최장 바이토닉 부분수열이 된다.
 * 물론 해당 인덱스값이 중복카운트 되므로 1을 빼주는것을 잊지 말자!
 *
 * LIS : dp[i]값은 i이전의 값중 arr[i]보다 작으면서 최대인 값의 dp값에 1을 더한 수를 넣고 전개해가는 알고리즘.
 */
public class Main {
    static int N, arr[];
    static Integer[] increaseDp,decreaseDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        increaseDp = new Integer[N];
        decreaseDp = new Integer[N];

        for(int i=0; i<N; i++){
            lis(i);
            lds(i);
        }

        int max = 0;
        for(int i=0; i<N; i++) {
            max = Math.max(increaseDp[i]+decreaseDp[i]-1, max);
        }

        System.out.println(max);
    }

    private static int lis(int n) {
        if(increaseDp[n] == null) {
            increaseDp[n] = 1;

            for(int i=n-1; i>=0; i--) {
                if(arr[i] < arr[n]) {
                    increaseDp[n] = Math.max(increaseDp[n], lis(i)+1);
                }
            }
        }
        return increaseDp[n];
    }

    private static int lds(int n) {
        if(decreaseDp[n] == null) {
            decreaseDp[n] = 1;

            for(int i=n+1; i<N; i++) {
                if(arr[i] < arr[n]) {
                    decreaseDp[n] = Math.max(decreaseDp[n], lds(i)+1);
                }
            }
        }
        return decreaseDp[n];
    }
}