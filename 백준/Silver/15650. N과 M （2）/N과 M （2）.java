import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** N과 M(2)
 * 1~N까지 자연수중에서 중복없이 M개 고른다
 * 고른 수열은 오름차순이어야한다
 * N개중에서 M개를 뽑는 조합을 구하고 오름차순 정렬하면 되지 않을까>
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        solution(0,1);
        System.out.println(sb);
    }

    private static int arr[], N, M;
    private static StringBuilder sb = new StringBuilder();
    private static void solution(int cnt, int start) {
        if(cnt == M) {
            for(int i : arr) sb.append(i).append(' ');
            sb.append('\n');
            return;
        }

        for(int i=start; i<N+1; i++){
            arr[cnt] = i;
            solution(cnt+1, i+1);
        }
    }
}