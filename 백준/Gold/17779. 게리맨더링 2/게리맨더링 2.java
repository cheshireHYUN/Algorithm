import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 게리멘더링 2
 * NxN크기의 재현시를 다섯개 선거구로 나누고, 각 구역은 다섯 선거구중 하나에 포함되어야 한다.
 * 선거구는 구역을 적어도 하나 포함해야하며, 한 선거구에 포함된 지역은 연결되어있어야 한다.
 * 연결되어있다 = A에서 인접구역을 통해 B로 갈 수 있을때 두 구역은 연결되어있음. 인접구역은 0개 이상이며 같은 선거구임.
 *
 * 선거구를 적당히 나눌떄,
 * 인구가 가장 많은 선거구와 가장 적은 선거구 인구사이의 최솟값을 구하자.
 *
 * [풀이]
 * 우선 선거구를 나누는 각 방법들을 시도해야한다.
 * 필요한건 기준점 (x,y)와 d1,d2인데,
 * 1<=d1,d2 // 1<=x<x+d1+d2<=N // 1<=y-d1<y<y+d2<=N 이라는 조건을 통과해야함.
 * 위 조건을 통과하는 기준점과 d1,d2를 이용해 (완전탐색)
 * 경계선을 그어 선거구를 확정하고, 각 구역별 인구값을 구해 답을 구한다. (구현)
 * 경계구역 밖을 쉽게 탐색하기위해 맵의 가생이부터 탐색하는것이 키포인트였다.
 *
 * [개선]
 * 각 경우마다 최대최소 인구수를 측정할때 배열에 넣은 뒤 sort해버리면 쉽게 구할 수 있다.
 * 또한 5번 선거구의 인구수는 1,2,3,4번 구역의 인구수를 전체인구수에서 빼면 쉽게 구할 수 있다.
 */
public class Main {
    static int[][] map;
    static int N, result=Integer.MAX_VALUE, total=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        for(int x=0; x<N; x++){
            for(int y=0; y<N; y++) {
                for(int d1=1; d1<N; d1++) {
                    for(int d2=1; d2<N; d2++) {
                        if(validLine(x,y,d1,d2)) {
                            //이 기준점을 통해 선거구를 나눔
                            divide(x,y,d1,d2);
                        }
                    }
                }
            }
        }

        System.out.println(result);

    }

    private static boolean validLine(int x, int y, int d1, int d2) {
        if(x+d1+d2<N) {
            if(0<=y-d1 && y+d2<N) return true;
        }
        return false;
    }

    private static void divide(int x, int y, int d1, int d2) {
        //System.out.println("[기준점] : ("+x+","+y+") // d1="+d1+", d2="+d2);
        //경계선을 계산한다 : 경계선은 모두 5번 선거구
        boolean[][] dMap = new boolean[N][N];
        int[] cntArr = new int[5];

        //1번
        for(int i=x, j=y; i<=x+d1 && j>=y-d1; i++, j--) {
            dMap[i][j] = true;
        }
        for(int i=0; i<x+d1; i++) {
            for(int j=0; j<=y; j++) {
                if(dMap[i][j]) break;
                cntArr[0] += map[i][j];
            }
        }

        //2번
        for(int i=x, j=y; i<=x+d2 && j<=y+d2; i++, j++) {
            dMap[i][j] = true;
        }
        for(int i=0; i<=x+d2; i++) {
            for(int j=N-1; j>=y+1; j--) {
                if(dMap[i][j]) break;
                cntArr[1] += map[i][j];
            }
        }

        //3번
        for(int i=x+d1, j=y-d1; i<=x+d1+d2 && j<=y-d1+d2; i++,j++) {
            dMap[i][j] = true;
        }
        for(int i=x+d1; i<N; i++) {
            for(int j=0; j<y-d1+d2; j++) {
                if(dMap[i][j]) break;
                cntArr[2] += map[i][j];
            }
        }

        //4번
        for(int i=x+d2, j=y+d2; i<=x+d2+d1 && j>=y+d2-d1; i++,j--) {
            dMap[i][j] = true;
        }
        for(int i=x+d2+1; i<N; i++) {
            for(int j=N-1; j>=y-d1+d2; j--) {
                if(dMap[i][j]) break;
                cntArr[3] += map[i][j];
            }
        }

        //5번
        cntArr[4] = total-(cntArr[0]+cntArr[1]+cntArr[2]+cntArr[3]);

        Arrays.sort(cntArr);
        int min = cntArr[0];
        int max = cntArr[4];

        //print(dMap);
        result = Math.min(result, max-min);
    }

    private static void print(int[][] dMap) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(dMap[i][j]);
            }
            System.out.println();
        }
    }
}