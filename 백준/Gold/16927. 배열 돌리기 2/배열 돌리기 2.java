import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 배열돌리기2
 * NXM배열을 반시계 방향으로 회전한다.
 *
 * 풀이
 * 회전횟수(R)이 최대 10억번이기 때문에 매번 시뮬레이션(NxM) 하면 시간초과 발생
 * 따라서 제자리로 돌아오는 회전수로 나눈 만큼 회전시켜야 한다(깊이마다 다름 - 배열의 갯수만큼 회전)
 */
public class Main {
    static int N,M,R;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
        out();
    }

    private static void solution() {
        int depth = Math.min(N,M)/2;
        //각 depth별로 배열의 길이를 구한뒤 나머지 연산으로 회전수를 최적화한다.
        for(int i=0; i<depth; i++) {
            int length = ((N-1)-(i*2))*2 + ((M-1)-(i*2))*2;
            int r = R%length;
            for(int j=0; j<r; j++) rotate(i);
        }
    }

    static int[] dy = {1, 0, -1, 0};  // 아래, 오른쪽, 위, 왼쪽
    static int[] dx = {0, 1, 0, -1};
    private static void rotate(int i) {
        int currX = i;
        int currY = i;
        int d = 0;
        int before = arr[i][i];

        while(d < 4) {
            int nextX = currX+dx[d];
            int nextY = currY+dy[d];

            if(nextX<i || nextX >=M-i || nextY<i || nextY>=N-i) {
                if(++d>=4) return;
                nextX = currX+dx[d];
                nextY = currY+dy[d];
            }

            int tmp = arr[nextY][nextX];
            arr[nextY][nextX] = before;
            before = tmp;

            currY = nextY;
            currX = nextX;
        }
    }


    private static void out() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) sb.append(arr[i][j]).append(' ');
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
