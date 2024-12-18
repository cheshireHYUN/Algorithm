import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 좌표정렬하기
 * 1차원 평면 위의 점 N개가 주어질때 좌표를 x가 증가하는 순으로, 같으면 y증가순으로 정렬후 출력해라
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(arr, (a1,a2) -> {
            if(a1[0] == a2[0]) return a1[1]-a2[1];
            else return a1[0]-a2[0];
        });
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) sb.append(arr[i][0]).append(' ').append(arr[i][1]).append('\n');
        System.out.println(sb);
    }
}