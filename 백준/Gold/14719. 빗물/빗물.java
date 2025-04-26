import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 빗물
 * 2차원 세계에 블록이 있고, 비가오면 블록사이 빗물이 고인다.
 * 빗물의 총량은?
 *
 * 제한
 * 1<= 2차원 가로,세로 <=500
 * 바닥은 항상 막혀있다.
 *
 * 풀이
 * 행별로 탐색하면서 1이 두개 나오면 그사이는 채운다고 보면됨..
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken()); //세로 = 행
        int w = Integer.parseInt(st.nextToken()); //가로 = 열
        int[][] map = new int[h][w];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<w; i++) { //열의 갯수만큼 반복
            //[0,1,2,3]일때 첫번째 열의높이 len=3이면, 3,2,1을 채운다.
            int len = Integer.parseInt(st.nextToken());
            for(int j=h-1; j>=h-len; j--) map[j][i] = 1;
        }

        int sum = 0;
        for(int i=0; i<h; i++) {
            int block = 0, preLocation = 0, water=0;
            for(int j=0; j<w; j++) {
                if(map[i][j] == 1) {
                    block++;
                    if(block == 2) {
                        water += (j-preLocation-1);
                        block--;
                    }
                    preLocation = j;
                }
            }
            sum += water;
        }

        System.out.println(sum);

    }
}
