import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/** 토마토
 * 사방으로 토마토가 익음. 익은거1 안익은거0 빈칸-1
 * 풀이 : 안익은 토마토 갯수가 1이상이면 시뮬레이션 진행
 * 사방탐색하며 체크배열 만들어서 안익은거 0이면 종료
 * BFS하면서 출발점 큐를 돌리면서 몇레벨인지 탐색
 */
public class Main {
    static int[][] map;
    static boolean[][] check;
    static int N,M,green,red;
    static Queue<int[]> q = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); //가로칸 수 = 열
        N = Integer.parseInt(st.nextToken()); //세로칸 수 = 행
        map = new int[N][M];
        check = new boolean[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    q.offer(new int[]{i,j});
                    check[i][j] = true;
                }
                else if(map[i][j] == 0) green++;
            }
        }
        if(green == 0) System.out.println(0);
        else  System.out.println(solution());
    }

    private static int solution(){
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        int day = 0;
        while(!q.isEmpty()){
            day++;
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] curr = q.poll();
                for(int j=0; j<4; j++){
                    int x = curr[0]+dx[j];
                    int y = curr[1]+dy[j];
                    if(x<0 || y<0 || x>=N || y>=M || check[x][y]) continue;
                    if(map[x][y] == 0){
                        green --;
                        q.offer(new int[]{x,y});
                        check[x][y] = true;
                    }
                }
            }
            if(green == 0) return day;
        }
        return -1;
    }
}