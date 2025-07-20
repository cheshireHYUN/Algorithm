import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 정수제곱근
 * 정수가 주어지면 그 수의 정수 제곱근을 구하는 프로그램을 작성하라
 * 이분탐색으로 풀어야징 ㅋㅋ
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long start=0, end=n, result=0;
        while(start<=end) {
            long mid = (start+end)/2;
            if(n<=Math.pow(mid,2)) {
                result = mid;
                end = mid-1;
            } else start = mid+1;
        }
        System.out.println(result);
    }
}
