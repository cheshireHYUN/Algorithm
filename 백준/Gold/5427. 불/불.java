import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 불
 * 매초마다 불이 동서남북으로 퍼져나간다(벽엔 불X)
 * 상근이는 동서남북으로 1초마다 이동가능하며, 벽통과X, 불붙으려는 칸이나 불칸은 못감
 * 얼마나 빨리 빌딩을 통과할 수 있을까?
 *
 * 풀이
 * 불을 퍼트리며 BFS
 */
public class Main {
    static char[][] map;
    static Queue<int[]> fires;
    static Queue<int[]> q;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while(tc-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); //지도너비
            int h = Integer.parseInt(st.nextToken()); //지도높이
            map = new char[h][w];
            fires = new ArrayDeque<>(); //불 위치 저장
            q = new ArrayDeque<>(); //시작위치 저장

            for(int i=0; i<h; i++) {
                map[i] = br.readLine().toCharArray();
                for(int j=0; j<w; j++) {
                    if(map[i][j]=='*') fires.add(new int[]{i,j});
                    else if(map[i][j]=='@') q.add(new int[]{i,j});
                }
            }
            String result = simulation();
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }

    // .:빈공간 #:벽 @:상근이 *:불
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    public static String simulation() {
        boolean[][] isVisited = new boolean[map.length][map[0].length];
        int[] p = q.peek();
        isVisited[p[0]][p[1]] = true;

        int level=0;
        while(!q.isEmpty()) {
            level++;
            spreadFire(); //불 퍼트리기
            int size = q.size();
            for(int s=0; s<size; s++) {
                int[] curr = q.poll(); //현재 상근이 위치

                for(int i=0; i<4; i++) {
                    int nx = curr[0]+dx[i];
                    int ny = curr[1]+dy[i];

                    if(nx<0 || ny<0 || nx>=map.length || ny>=map[0].length) return String.valueOf(level);

                    if(isVisited[nx][ny] || map[nx][ny]!='.') continue;
                    isVisited[nx][ny] = true;
                    q.add(new int[]{nx,ny});
                }
            }
        }
        return "IMPOSSIBLE";
    }

    //불 퍼트리기
    public static void spreadFire() {
        int size = fires.size();
        for(int s=0; s<size; s++) {
            int[] fire = fires.poll();
            for(int i=0; i<4; i++) {
                int nx = fire[0]+dx[i];
                int ny = fire[1]+dy[i];
                if(nx<0 || ny<0 || nx>=map.length || ny>=map[0].length) continue;
                if(map[nx][ny] == '.' || map[nx][ny] == '@') {
                    map[nx][ny] = '*';
                    fires.add(new int[]{nx,ny});
                }
            }
        }
    }
}
