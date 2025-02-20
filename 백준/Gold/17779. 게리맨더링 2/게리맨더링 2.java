import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * 위 조건을 통과하는 기준점과 d1,d2를 이용해 경계선을 그어 선거구를 확정하고, 각 구역별 인구값을 구해 답을 구한다.
 */
public class Main {
    static int[][] map;
    static int N, result=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
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
        int[][] dMap = new int[N][N];
        //1번
        for(int i=x, j=y; i<=x+d1 && j>=y-d1; i++, j--) {
            dMap[i][j] = 5;
        }
        for(int i=0; i<x+d1; i++) {
            for(int j=0; j<=y; j++) {
                if(dMap[i][j] == 5) break;
                dMap[i][j] = 1;
            }
        }

        //2번
        for(int i=x, j=y; i<=x+d2 && j<=y+d2; i++, j++) {
            dMap[i][j] = 5;
        }
        for(int i=0; i<=x+d2; i++) {
            for(int j=N-1; j>=y+1; j--) {
                if(dMap[i][j] == 5) break;
                dMap[i][j] = 2;
            }
        }

        //3번
        for(int i=x+d1, j=y-d1; i<=x+d1+d2 && j<=y-d1+d2; i++,j++) {
            dMap[i][j] = 5;
        }
        for(int i=x+d1; i<N; i++) {
            for(int j=0; j<y-d1+d2; j++) {
                if(dMap[i][j] == 5) break;
                dMap[i][j] = 3;
            }
        }

        //4번
        for(int i=x+d2, j=y+d2; i<=x+d2+d1 && j>=y+d2-d1; i++,j--) {
            dMap[i][j] = 5;
        }
        for(int i=x+d2+1; i<N; i++) {
            for(int j=N-1; j>=y-d1+d2; j--) {
                if(dMap[i][j] == 5) break;
                dMap[i][j] = 4;
            }
        }

        //계산
        int cnt1=0,cnt2=0,cnt3=0,cnt4=0,cnt5=0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++) {
                if(dMap[i][j]==1) cnt1 += map[i][j];
                else if(dMap[i][j]==2) cnt2 += map[i][j];
                else if(dMap[i][j]==3) cnt3 += map[i][j];
                else if(dMap[i][j]==4) cnt4 += map[i][j];
                else if(dMap[i][j]==0 || dMap[i][j]==5) cnt5 += map[i][j];
            }
        }

        int min = getMin(cnt1,cnt2,cnt3,cnt4,cnt5);
        int max = getMax(cnt1,cnt2,cnt3,cnt4,cnt5);

        //print(dMap);
        result = Math.min(result, max-min);
    }

    private static int getMin(int cnt1, int cnt2, int cnt3, int cnt4, int cnt5) {
        int min = Math.min(cnt1, cnt2);
        min = Math.min(min, cnt3);
        min = Math.min(min, cnt4);
        min = Math.min(min, cnt5);
        return min;
    }

    private static int getMax(int cnt1, int cnt2, int cnt3, int cnt4, int cnt5) {
        int max = Math.max(cnt1, cnt2);
        max = Math.max(max, cnt3);
        max = Math.max(max, cnt4);
        max = Math.max(max, cnt5);
        return max;
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