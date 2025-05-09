import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 다각형의 면적
 * 2차원 평면상에 N개의 점으로 이뤄진 다각형이 있다.
 * 다각형의면적을 구하라.
 * 풀이
 * 어쩐지 어렵더라 기하학이였음.. 외적을 이용한 풀이
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] edge = new int[N][2];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            edge[i][0] = x;
            edge[i][1] = y;
        }

        long sum = 0;
        for(int i=0; i<N; i++) {
            sum += (long)edge[i][0] * edge[(i+1)%N][1];
            sum -= (long)edge[i][1] * edge[(i+1)%N][0];
        }

        System.out.printf("%.1f", Math.abs((double)sum/2));
    }
}
