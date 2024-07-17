import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** OX퀴즈
 * OOXXOXXOOO의 경우 연속된 O의 갯수를 그자리에 넣어 계산
 * 즉 1+2+1+1+2+3 = 10점임
 * 풀이 : 반복문 돌면서 누적O갯수를 변수에 따로 저장하면서 sum을 동시에 계산
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++){
            int sum = 0;
            int cnt = 0;
            char[] arr = br.readLine().toCharArray();
            for(char c : arr){
                if(c=='O') {
                    cnt += 1;
                    sum += cnt;
                }else cnt = 0;
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}