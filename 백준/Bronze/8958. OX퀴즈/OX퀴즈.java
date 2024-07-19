import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** OX퀴즈
 * O의 각 자리에서의 연속된 횟수를 모두 더한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++){
            char[] arr = br.readLine().toCharArray();
            int cnt = 0, sum = 0;
            for(char c : arr){
                if(c=='O'){
                    cnt++;
                    sum += cnt;
                } else cnt = 0;
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}