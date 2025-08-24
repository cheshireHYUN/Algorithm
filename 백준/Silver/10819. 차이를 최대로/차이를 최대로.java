import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 차이를 최대로
 * 순열을 구하고 최댓값을 만든다.
 */
public class Main {
    static int[] arr, numbers;
    static boolean[] selected;
    static int N, max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        numbers = new int[N];
        selected = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        permutation(0);
        System.out.println(max);
    }

    private static int calculation() {
        int result = 0;
        for(int i=0; i<N-1; i++) {
            result += Math.abs(arr[numbers[i]]-arr[numbers[i+1]]);
        }
        return result;
    }

    private static void permutation(int cnt) {
        if(cnt==N) { //N번째를 채울 차례면 순열 다 채워짐
            max = Math.max(max, calculation());
            return;
        }

        for(int i=0; i<N; i++) {
            if(selected[i]) continue;
            numbers[cnt]=i; //각 순열의 인덱스를 저장
            selected[i] = true;
            permutation(cnt+1);
            selected[i] = false;
        }
    }
}
