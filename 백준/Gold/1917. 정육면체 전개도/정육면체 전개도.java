import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

/** 정육면체 전개도
 * 풀이
 * 마주보는 면의 숫자합은 항상 7이 되어야하는 특성을 이용해서 풀이
 *
 * 1.int[] dice = new int[6]으로 설정하고 각 면에 적힌 숫자를 저장한다. (index -> //0:위, 1:오른, 2:밑, 3:왼, 4:앞, 5:뒤)
 * 2.도면의 임의의 지점에서 DFS를 시작한다. visited[]를 통해 이미 주사위에서 쓴 숫자인지 체크
 * 3.상하좌우 4방향으로 DFS를 수행한다.
 *  3-1. 각 방향은 turn()함수로 구현해 면 위치를 변경해준다.
 *  3-2. turn()한 후 이미 숫자가 들어있는 면(visited[dice[i]]=true)이라면 실패
 *  3-3. 백트래킹 할때 turn((i+2)%4)로 반대방향으로 되돌린다(위(0)로 돌렸으면 아래(3)로, 왼쪽(1)이면 오른쪽(2)으로)
 * 4. 모든 주사위 면이 6면 모두 채워졌으면 yes, 아니면 no 누적
 */
public class Main {
    static int[][] map;
    static int[] dice;
    static boolean[] visited;
    static boolean result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = 3;
        while(t-->0) {
            result = true;
            dice = new int[]{2,3,4,5,1,6}; //0:위, 1:오른, 2:밑, 3:왼, 4:앞, 5:뒤

            map = new int[6][6];
            for(int i=0; i<6; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<6; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            out:for(int i=0; i<6; i++) {
                for(int j=0; j<6; j++) {
                    if(map[i][j]==1) {
                        map[i][j] = 0;
                        visited = new boolean[7];
                        visited[1] = true;
                        dfs(i,j);
                        break out;
                    }
                }
            }

            sb.append(result ? "yes":"no").append('\n');
        }
        System.out.println(sb);
    }

    //0:위, 1:아래, 2:앞, 3:뒤, 4:왼쪽, 5:오른쪽
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,1,0,-1};
    private static void dfs(int x, int y) {
        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx<0 || nx>=6 || ny<0 || ny>=6 || map[nx][ny]!=1) continue;

            if(visited[dice[i]]) {
                result = false;
                return;
            }

            map[nx][ny] = 0;
            visited[dice[i]] = true;
            turn(i);
            dfs(nx,ny);
            turn((i+2)%4);
        }
    }

    private static void turn(int d) {
        switch(d) {
            case 0 : //위
                dice = new int[]{dice[5],dice[1],dice[4],dice[3],dice[0],dice[2]};
                break;

            case 1 : //오른
                dice = new int[]{dice[0],dice[5],dice[2],dice[4],dice[1],dice[3]};
                break;

            case 2 : //아래
                dice = new int[]{dice[4],dice[1],dice[5],dice[3],dice[2],dice[0]};
                break;

            case 3 : //왼
                dice = new int[]{dice[0],dice[4],dice[2],dice[5],dice[3],dice[1]};
                break;
        }
    }
}
