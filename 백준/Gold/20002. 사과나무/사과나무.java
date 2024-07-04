import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 사과나무
 * NxN 과수원에서 정사각형(KxK) 모양으로만 수확할 수 있다.
 * 하나의 사과나무를 수확할 때 사과를 통해 얻을수 있는 이익과 노동비로 빠져나가는 손해를 더한것을 이차원배열로 표현
 * 최대이익을 얻는 정사각형을 찾자.
 *
 * 과수원의 크기 N은 1보타 크고 300보다 작음
 * 총이익은 -1000이상 1000이하
 *
 * (풀이) 결국 덧셈은 필수적이다. 그런데 이 덧셈을 효율적으로 하는법은 없을까?
 * 누적합을 통해 해결할 수 있을것이다. 미리 더해놓는다면 상당히 편하겠쬬!
 * 누적합의 경우 그림을 그리면 명확하게 공식이 나온다.
 * sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i][j]
 */
public class Main {
    public static int[][] map, preSum;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        preSum = new int[N+1][N+1];
        StringTokenizer st;
        
        //(1) 원래배열과 누적합을 셋팅
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + map[i][j];
            }
        }

        //(2) 가장 이익이 큰 정사각형 영역을 완전탐색
        //특정 정사각형 영역만 구하려면 출발점이 (i,j)이고 끝점이 (i+k, j+k)일때
        //area[i+k][j+k] = preSum[i+k][j+k] - preSum[i-1][j+k] - preSum[i+k][j-1] + preSum[i-1][j-1]
        int maxValue = Integer.MIN_VALUE;
        for(int k=0; k<N; k++){
            for(int i=1; i<=N-k; i++){
                for(int j=1; j<=N-k; j++){
                    int tmp = preSum[i+k][j+k] - preSum[i-1][j+k] - preSum[i+k][j-1] + preSum[i-1][j-1];
                    maxValue = Math.max(tmp, maxValue);
                }
            }
        }

        System.out.println(maxValue);

    }

}