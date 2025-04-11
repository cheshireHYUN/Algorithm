import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 소수의 연속합
 * 하나 이상의 연속된 소수의합으로 나타낼 수 있는 자연수가 있다.
 * 연속되지 않거나, 한 소수를 여러번 사용했다면 해당하지 않는다.
 * 자연수가 주어졌을때 이 자연수를 연속된 소수합으로 나타낼 수 있는 경우의수를 구하는 프로그램을 작성하라.
 *
 * 제한
 * 1<=N<=400만
 * 2초
 *
 * 풀이
 * 소수 => 2 3 5 7 11 13 17 19 23 29 31 ...
 * 1. 일단 에라토스테네스의 체로 소수를 구해 저장한다.
 * 2. 투포인터를 통해 n과 일치하는 연속된 부분합의 갯수를 구한다.
 *    - 이방법이 고민이 많았는데, 수 하나를 고정해놓고 나머지 수를 차례로 더해보면됨. N을 넘으면 고정수를 증가시키고..
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        List<Integer> primeList = new ArrayList<>();

        //에라토스테네스의 체
        for(int i=2; i*i<=N; i++) {
            if(isPrime[i]) {
                for(int j=i*i; j<=N; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        for(int i=0; i<N+1; i++) {
            if(isPrime[i]) primeList.add(i);
        }

        //연속합 구하기 - 투포인터(left 고정, right 늘리며 N이하까지)
        int left=0, right=0, sum=0, cnt=0;
        while(left<=right) {
            if(N==sum) cnt++;
            //경우에따라 포인터 옮기기
            if(sum>=N) sum -= primeList.get(left++); //합계가 N이상이면 left를 옮겨주기
            else if(right == primeList.size()) break; //right가 리스트를 넘으면 종료
            else sum += primeList.get(right++); //기본적으로 right 늘려주기
        }

        System.out.println(cnt);


    }
}
