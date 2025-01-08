import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/** 파이프 옮기기 1
 * 새 집의 크기는 NxN이다. 행렬번호는 1부터 시작한다.
 * 집수리를 위해 파이프 하나를 옮긴다. 2개 칸을 차지하며, 3방향으로 회전 가능하다.
 * 파이프가 벽을 긁지않고 이동시킨다. 45도씩 회전 시킬수있으며, 방향은 오/아래/오아래
 * 파이프가 가로로 놓여진 경우 => 2가지(가로 그대로, 대각선으로)
 * 파이프가 세로로 놓여진 경우 => 2가지(세로 그대로, 대각선으로)
 * 파이프가 대각선으로 놓여진 경우 => 3가지(가로로, 세로로, 대각선 그대로)
 *
 * 가장 처음에 파이프는 (1,1)과 (1,2)를 차지하고있고, 방향은 가로이다.
 * (N,N)으로 이동시키는 방법의 갯수를 구해보자.
 *
 * 풀이
 * 방법의 갯수를 구해야 하므로 DFS를 통해서 모든 경로를 탐색한다.
 * 벽은 1, 빈칸은 0을 셋팅한다.
 * Pipe객체는 방향, 좌표1, 좌표2를 갖는다.
 * 오직 전진만 있기 떄문에 visited는 필요 없어보이는데..
 * 파이프 방향에따라 회전하는 경우의수를 돌며, 각 회전결과가 벽에 부딪히지 않을경우 재귀
 * 
 * (주의) 문제에서 꼭 빈칸이어야 하는 경우는 색으로 칠해져있음
 * 시간초과를 해결하는 방법으로는.. 각 위치에서 가지는 각 방향값을 미리 저장하는 방법이 있을듯
 * location1과 direction을 기준으로 저장하는 방식을 선택한다. 즉 3차원 배열에 List를 저장한다
 * List<Pipe>[location1_X][location1_Y][direction]
 */
public class Main {
    static int[][] map;
    static int N,cnt;
    static class Pipe {
        public int direction; //0:가로, 1:세로, 2:대각선
        public int[] location1;
        public int[] location2;
        public Pipe(int d, int[] l1, int[] l2) {
            this.direction = d;
            this.location1 = l1;
            this.location2 = l2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N]; //목표위치는 (N-1,N-1)
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        dp = new List[N][N][3];
        dfs(new Pipe(0,new int[]{0,0},new int[]{0,1}));
        System.out.println(cnt);
    }

    private static void dfs(Pipe pipe) {
        //(N-1,N-1)에 도착하면 종료한다.
        if(pipe.location2[0] == N-1 && pipe.location2[1] == N-1) {
            cnt++;
            return;
        }

        //현재 파이프가 갈 수 있는 후보군을 찾아 재귀
        List<Pipe> nextPipeList;
        if(dp[pipe.location1[0]][pipe.location1[1]][pipe.direction] == null) {
            dp[pipe.location1[0]][pipe.location1[1]][pipe.direction] = getTurnPipeList(pipe);
        }
        nextPipeList = dp[pipe.location1[0]][pipe.location1[1]][pipe.direction];
        for(Pipe nextPipe : nextPipeList) {
            dfs(nextPipe);
        }
    }

    //현재 파이프가 회전했을때의 후보군을 리턴하는 메소드
    private static List<Pipe> getTurnPipeList(Pipe pipe){
        List<Pipe> list = new ArrayList<>();
        Pipe newPipe;
        if(pipe.direction == 0) {
            if((newPipe = turn(pipe, 0))!=null) list.add(newPipe);
            if((newPipe = turn(pipe, 2))!=null) list.add(newPipe);
        } else if(pipe.direction == 1) {
            if((newPipe = turn(pipe, 1))!=null) list.add(newPipe);
            if((newPipe = turn(pipe, 2))!=null) list.add(newPipe);
        } else {
            if((newPipe = turn(pipe, 0))!=null) list.add(newPipe);
            if((newPipe = turn(pipe, 1))!=null) list.add(newPipe);
            if((newPipe = turn(pipe, 2))!=null) list.add(newPipe);
        }

        return list;
    }

    private static int[][] dxdy = new int[][]{{0,1},{1,0},{1,1}};
    private static List<Pipe>[][][] dp;
    private static Pipe turn(Pipe pipe, int direction) {
        int[] dy = dxdy[direction];
        int nextLocation2Dx = pipe.location2[0]+dy[0];
        int nextLocation2Dy = pipe.location2[1]+dy[1];

        if(nextLocation2Dx < 0 || nextLocation2Dy < 0 || nextLocation2Dx>=N || nextLocation2Dy>=N
            || map[nextLocation2Dx][nextLocation2Dy] == 1) return null;
        
        //대각선일경우 양옆도 Location2 기준으로 상,좌도 비어있어야함.
        if(direction == 2) {
            if((nextLocation2Dx-1>=0 && map[nextLocation2Dx-1][nextLocation2Dy] == 1) ||
                    (nextLocation2Dy-1>=0 && map[nextLocation2Dx][nextLocation2Dy-1] == 1)) return null;
        }


        return new Pipe(direction, pipe.location2.clone(), new int[]{nextLocation2Dx,nextLocation2Dy});
    }
}