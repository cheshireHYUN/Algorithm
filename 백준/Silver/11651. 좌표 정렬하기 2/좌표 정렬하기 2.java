import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 좌표 정렬하기
 * 2차원 평면위에 점N개가 있고, y가 증가하는 순으로, y가 같으면 x 증가하는 순으로 정렬하라
 * 풀이 : Comparable의 compareTo를 구현하여 정렬한다. 이번엔 람다를 이용해볼까?
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] arr = new int[N][2];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (a1,a2) -> {
            if(a1[1] == a2[1]) return a1[0]-a2[0];
            else return a1[1]-a2[1];
        });

        StringBuilder sb = new StringBuilder();
        for(int[] a : arr){
            for(int b : a){
                sb.append(b).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}