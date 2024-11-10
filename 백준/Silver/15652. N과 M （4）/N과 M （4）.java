import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** N과 M
 * N과 M이 주어졌을때 길이가 M인 수열을 구해라
 * (1) 1부터 N까지중에 M개를 고른다
 * (2) 같은수를 여러번 골라도 된다(중복수열)
 * (3) 고른 수열은 비내림차순, 즉 오름차순 이어야 한다.
 */
public class Main {
    static int N,M,numbers[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        getNum(0,1);
        System.out.println(sb);
    }

    private static void getNum(int idx, int start) {
        if(idx == M) {
            for(int number : numbers) sb.append(number).append(' ');
            sb.append('\n');
            return;
        }

        for(int i=start; i<N+1; i++) {
            numbers[idx] = i;
            getNum(idx+1, i);
        }
    }
}