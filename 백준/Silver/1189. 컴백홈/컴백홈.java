import java.util.*;

public class Main {

    static int R,C,K,cnt=0;
    static int[][] checkedArr;
    static char[][] map;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    static void dfs(int x, int y, int len) {
        // stop : 도착점(0,C-1)에 도착할경우
        if(x==0 && y==C-1){
            if(len == K) cnt++;
            return;
        }

        // 상하좌우 검사하며 재귀 호출한다
        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            // map 범위안에 있고 / T가 아니면서 / 방문한적 없을때만 재귀
            if(nx>=0 && nx<R && ny>=0 && ny<C
                    && map[nx][ny] != 'T'
                    && checkedArr[nx][ny] == 0){
                // 체크배열 1로 바꾼뒤 재귀
                checkedArr[nx][ny] = 1;
                dfs(nx, ny, len+1);
                // 백트래킹(끝난경로 직전으로 돌아가서 다른경로 탐색)
                checkedArr[nx][ny] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt(); //열
        C = sc.nextInt(); //행
        K = sc.nextInt(); //길이
        checkedArr = new int[R][C];
        map = new char[R][C];

        for(int i=0; i<R; i++) {
            String tmp = sc.next();
            for(int j=0; j<C; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        // 시작점(C-1,0) 셋팅
        checkedArr[R-1][0] = 1;
        dfs(R-1,0,1);
        System.out.println(cnt);
    }


}