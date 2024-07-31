import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 수들의 합
 * 서로 다른 N개의 자연수 합이 S일때 자연수 N의 최댓값?
 * 풀이 : 합이 S가 되기 직전까지 자연수를 더한뒤 리턴
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long N = Long.parseLong(br.readLine());
        Long sum=0L, max=0L;
        for(Long i=1L; i<=N; i++){
            sum += i;
            if(sum >= N) {
                if(sum == N) max=i;
                else max=i-1;
                break;
            }
        }
        System.out.println(max);
    }
}