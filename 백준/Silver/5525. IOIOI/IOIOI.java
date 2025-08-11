import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** IOIOI
 * N+1개의 I와 N개의 o로 이루어져 있으면 I와o가 <교대로> 나오는 문자열을 PN이라고 한다
 * S안에 PN이 몇군데 포함되어 있는지 구하는 프로그램?
 *
 * 풀이
 * "I"와 "OI"의 집합으로 구분해서 본다.
 * I는 N+1개, O는 N개가 필요하므로 OI를 묶어서 N개 찾은 후, I가 앞에있는지 보면 됨.
 *
 * (1) "OI"가 연속으로 N만큼 들어있는지 확니한 후, 맨앞이 "I"가 맞는지 확인.
 * (2) 그게 총 몇개인지 확인..
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int cnt=0, ans=0;
        for(int i=1; i<M-1;) {
            if(str.charAt(i)=='O' && str.charAt(i+1)=='I') {
                cnt++;
                if(cnt == N) {
                    if(str.charAt(i-(cnt*2-1)) == 'I') ans++;
                    cnt--;
                }
                i+=2;
            }else {
                cnt = 0;
                i++;
            }
        }
        System.out.println(ans);
    }
}
