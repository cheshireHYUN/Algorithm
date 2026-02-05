import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** 풀이
 * static new List (clouds)
 * static new int[][] (map) = NxN으버 해서 (0,N-1)까지 범위로 만든다.
 * static new boolean[][] (plusWatermap)
 * 비바라기 시전 메인 메소드(param : N)
 * (1) 처음엔 (N,1)(N,2)(N-1,1)(N-1,2)에 비구름 생성
 *      (N-1,1)(N-1,2)(N-2,1)(N-2,2)로 변경 -> clouds에 넣는다(이 다음부터는 이전cluouds사용)
 *
 * (2) M번의 반복문 시행
 *  2-0. plusWatermap을 초기화하고
 *  2-1. 구름 이동 메소드 시전
 *  2-2. 비내리기 메소드 시전
 *  2-3. 물복사버그 메소드 시전
 * (3) 전체 맵을 순회하며 물양의 합을 계산한다.
 */
public class Main {
    static int N,M;
    static List<int[]> clouds;
    static int[][] map;
    static boolean[][] plusWaterMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //맵크기
        M = Integer.parseInt(st.nextToken()); //명령횟수

        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        clouds = new ArrayList<>();
        clouds.add(new int[]{N-1,0});
        clouds.add(new int[]{N-1,1});
        clouds.add(new int[]{N-2,0});
        clouds.add(new int[]{N-2,1});

        for(int t=0; t<M; t++) {
            plusWaterMap = new boolean[N][N];
            st = new StringTokenizer(br.readLine()," ");
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            moveClouds(d, s); //비구름이동
            raining(); //비내리기
            copyWater(); //물복사버그
        }

        int answer = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                answer += map[i][j];
            }
        }

        System.out.println(answer);
    }

    /* 구름 이동 메소드(param: d, s)
     * (0) direction은 1~8까지로, (0,0)(-1,0)(-1,1)(0,1)(1,1)(1,0)(1,-1)(0,-1)(-1,-1)
     * (1) clouds를 순회하면서 direction[d]*s를 각 좌표에 더해서 set(수정)해준다.
     *      - 이때 경계를넘으면 반대쪽과 연결됨에 유의한다.
     *      - 기존이 0~N-1로 바뀌었으므로, direction[d][0]*(s%N)행 direction[d][1]*(s%N)열이 됨
     *      - 이때 위 a행b열의 a,b에 대해
     *      -   0>k면 N-k값을 써주고
     *      -   N<=k면 k-N을 해준다
     * (2) clouds를 리턴한다.
     */
    static int [] dx = {0,0,-1,-1,-1,0,1,1,1};
    static int [] dy = {0,-1,-1,0,1,1,1,0,-1};
    private static int fixCoordinate(int k) {
        if(k<0) return N+k;
        else if(N<=k) return k-N;
        return k;
    }
    private static void moveClouds(int d, int s){
        for(int i=0; i<clouds.size(); i++) {
            int nx = fixCoordinate(clouds.get(i)[0] + dx[d] * (s % N));
            int ny = fixCoordinate(clouds.get(i)[1] + dy[d] * (s % N));
            clouds.set(i, new int[]{nx, ny});
        }
    }

    /* 비내리기 메소드
     * (1) clouds를 순회
     * (2) 위 좌표에 해당하는 map을 +1;
     * (3) +1을 해준 칸들은 전부 plusWatermap[][]=true
     * (4) 비구름을 모두 없앤다.
     */
    private static void raining() {
        for(int[] cloud : clouds) {
            map[cloud[0]][cloud[1]] += 1;
            plusWaterMap[cloud[0]][cloud[1]]= true;
        }
        clouds = new ArrayList<>();
    }

    /* 물복사 버그 메소드
     * (1) plusWatermap을 순회하면서
     * (2) 대각선 dxdy=((1,1)(1,-1)(-1,1)(-1,-1))에 대해 map[x][y]>0인곳의 갯수 카운트
     *      - 이때 경계선을 넘으면 무시한다
     * (3) 해당 map[x][y] += cnt;
     * (4) 순회 종료후 clouds를 초기화하고, 전체 맵을 탐색하면서
     *  - 물의양이 2이상인 칸이면서 !plusWaterMap인곳을 찾아 clouds에 저장하고, 값을 -2한다.
     */
    static int[] dr = new int[]{1,1,-1,-1};
    static int[] dc = new int[]{1,-1,1,-1};
    private static void copyWater() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int cnt=0;
                if(plusWaterMap[i][j]) {
                    for(int d=0; d<4; d++){
                        int nr = i+dr[d];
                        int nc = j+dc[d];

                        if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
                        if(map[nr][nc]>0) cnt++;
                    }
                }
                map[i][j] += cnt;
            }
        }

        clouds = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2 && !plusWaterMap[i][j])  {
                    map[i][j] -= 2;
                    clouds.add(new int[]{i,j});
                }
            }
        }
    }
}