import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 통나무를 원형으로 세워두고 뛴다. 인접한 통나무 높이차가 최소가 되게한다
 * 난이도 = 인접한 통나무간의 높이 차의 최댓값
 * 테케마다 통나무로 만들수있는 최소난이도를 만들어라.
 *
 * 풀이
 * 정렬한 상태에서는 첫번째와 마지막 배열값이 최대차를 만들어버린다.
 * 따라서 큰값은 큰값끼리 가까이두고 작은값은 작은값끼리 가까이둬야함
 * 빈 배열의 양쪽끝을 번갈아가며 오름차순수를 채우면 됨
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int[] result = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) arr[j] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr);
            int left = 0;
            int right = N-1;
            for(int j=0; j<N; j++) {
                if(j % 2 == 0) result[left++] = arr[j];
                else result[right--] = arr[j];
            }
            int max = Integer.MIN_VALUE;
            for(int j=0; j<N; j++){
                if(j==N-1) {
                    max = Math.max(max, Math.abs(result[j]-result[0]));
                    break;
                }
                max = Math.max(max, Math.abs(result[j]-result[j+1]));
            }

            sb.append(max).append('\n');
        }
        System.out.println(sb);
    }
}
