import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** CCW
 * 2차원 좌표 평면 위에 있는 점 3개 P1, P2, P3가 주어진다.
 * 세 점을 순서대로 이은 선분이 어떤 방향을 이루고 있는지 구하라.
 * 반시계 방향이면 1, 시계방향이면 -1, 일직선이면 0을 출력한다
 *
 * 풀이
 * 기하학 기본문제로, 신발끈 공식을 이용해 풀이 할 수 있다.
 * x1 x2 x3 x1    p[0][0] p[1][0] p[2][0] p[0][0]
 * y1 y2 y3 y1    p[0][1] p[1][1] p[2][1] p[0][1]
 * 위와같이 쓰고, x1,y2 // x2,y3 // x3,y1끼리 빗금을 곱해서 더한다.
 * 또한 반대로 x2,y1 // x3,y2 // x1,y3끼리 빗금을 곱해서 더한다
 * 즉, (x1y2+x2y3+x3y1) - (x2y1+x3y2+x1y3)를 구한다.
 * 이 결과가 CCW, 즉 0보다 크면 반시계, 0이면 일직선, 0보다 작으면 시계방향인 것이다.
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] p = new int[3][2];
        StringTokenizer st;
        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++) p[i][j] = Integer.parseInt(st.nextToken());
        }

        //ccw
        int result = ((p[0][0]*p[1][1])+(p[1][0]*p[2][1])+(p[2][0]*p[0][1])) - ((p[1][0]*p[0][1])+(p[2][0]*p[1][1])+(p[0][0]*p[2][1]));
        if(result == 0) System.out.println(0);
        else if (result > 0) System.out.println(1);
        else System.out.println(-1);
    }
}