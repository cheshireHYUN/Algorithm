import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 백설공주와 일곱난쟁이
 * 9개중 7를 뽑는 조합을 구해서 합이 100인것을 출력
 */
public class Main {
    static int[] arr, selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[9];
        selected = new int[7];
        for(int i=0; i<9; i++) arr[i] = Integer.parseInt(br.readLine());

        solution(0,0);
        System.out.println(sb);
    }

    static StringBuilder sb = new StringBuilder();
    private static void solution(int cnt, int start){
        if(cnt == 7){
            int sum = 0;
            for(int i : selected) sum += i;
            if(sum == 100) {
                for(int i : selected) sb.append(i).append("\n");
            }
            return;
        }
        for(int i=start; i<9; i++){
            selected[cnt] = arr[i];
            solution(cnt+1, i+1);
        }
    }
}