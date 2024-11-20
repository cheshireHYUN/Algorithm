import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** N과 M
 * N개의 자연수와 자연수 M이 주어졌을때 길이가 M인 수열을 모두 구하는 프로그램을 작성하라
 * N개의 자연수는 모두 다른수다.
 *
 * 풀이
 * N개의 자연수중 M개를 고른 수열
 */
public class Main {
    static int[] numbers;
    static int n,m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        numbers = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        selected = new int[m];
        getPermutation(0);
        System.out.println(sb);
    }

    static int[] selected;
    static boolean[] check = new boolean[10001];
    static void getPermutation(int index) {
        if(index == m) {
            for(int s : selected) sb.append(s).append(' ');
            sb.append('\n');
            return;
        }
        for(int i=0; i<n; i++) {
            if(check[numbers[i]]) continue;
            check[numbers[i]] = true;
            selected[index] = numbers[i];
            getPermutation(index+1);
            check[numbers[i]] = false;
        }
    }
}