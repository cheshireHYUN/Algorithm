import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** N과 M
 * 1~N까지 자연수 중 중복없이 M개를 고른 수열
 */
public class Main {
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        selected = new int[M];
        check = new boolean[N+1];
        solution(0);
        System.out.println(sb);
    }
    static StringBuilder sb = new StringBuilder();
    static int[] selected;
    static boolean[] check;
    static void solution(int cnt){
        if(cnt == M){
            for(int i: selected) sb.append(i).append(" ");
            sb.append("\n");
            return;
        }
        for(int i=1; i<=N; i++){
            if(check[i]) continue;
            check[i] = true;
            selected[cnt] = i;
            solution(cnt+1);
            check[i] = false;
        }
    }
}