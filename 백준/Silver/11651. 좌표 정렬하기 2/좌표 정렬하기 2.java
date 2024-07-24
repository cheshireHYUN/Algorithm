import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 좌표 정렬하기 2
 * 2차원 평면 위의 점 N개가 있을때 y좌표증가 -> 같으면 x좌표 증가하도록 출력
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(arr, (a,b) ->{
            if(a[1] == b[1]) return a[0]-b[0];
            else return a[1]-b[1];
        });

        StringBuilder sb = new StringBuilder();
        for(int[] i : arr) sb.append(i[0]).append(" ").append(i[1]).append("\n");
        System.out.println(sb);
    }
}