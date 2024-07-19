import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** N과 M(1)
 * 자연수 N과 M이 주어졌을때 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하라.
 * (1부터 N까지 자연수 중에서 중복없이 M개를 고른 수열)
 * 풀이 : 그냥 1부터N까지 수중에서 M개를 뽑는 수열(중복X)을 만들자. => 중복 확인 배열
 */
public class Main {
    static int N,M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        select = new int[M];
        check = new boolean[N+1];
        permutation(0);
        System.out.println(sb);
    }

    static int[] select;
    static boolean[] check;
    private static void permutation(int cnt) {
        if(cnt == M){
            for(int i : select) sb.append(i).append(" ");
            sb.append("\n");
            return;
        }
        for(int i=1; i<=N; i++){
            if(check[i]) continue;

            select[cnt] = i;
            check[i] = true;
            permutation(cnt+1);
            check[i] = false;
        }
    }
}