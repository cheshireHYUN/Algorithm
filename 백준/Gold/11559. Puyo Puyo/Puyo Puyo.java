import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 뿌요뿌요
 * 1. 필드에 여러가지 색깔의 뿌요를 놓는다. 중력의 영향을 받아 아래 바닥이나 다른뿌요가 나올때까지 떨어진다.
 * 2. 뿌요를 놓고난 후, 같은색 뿌요가 4개이상 상하좌우로 연결되어있으면 연결된 같은색 뿌요들이 한번에 없어진다(1연쇄)
 * 3. 뿌요들이 없어지고나서 위에 다른 뿌요들이 있다면 중력의 영향을 받아 차례로 아래로 떨어진다.
 * 4. 아래로 떨어지고나서 다시 같은색 뿌요들이 4개이상 모이면 또 터지는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할떄 +1연쇄
 * 5. 터질수있는 뿌요가 여러그룹이라면 동시에 터져야하고 여러그룹이 터져도 한번의 연쇄만 추가된다.
 *
 * 상태방의 필드가 주어졌을때, 연쇄가 몇번 연속으로 일어날지 계산하라.
 *
 * 풀이
 * 1. 터질수있는 뿌요그룹을 여러개 찾는 dfs 메소드 호출
 * visited배열을 초기화해 모든 노드에 대해 dfs 탐색 시도
 * 만약 해당 시작점에 대한 List.size()가 4이상이면 List값을 결과에 누적, 아니면 List값 버림
 *
 * 2. 연결된 갯수를 찾는 dfs 메소드
 * visited를 받아 dfs를 돌며 true처리 및 List<int[]> 추가
 *
 * 3. List에 저장된 좌표를 터트리는 메소드
 * 터트려야하는 좌표를 '.'로 바꾼다
 *
 * 4. 중력 메소드
 * 맨밑이 .이 아니게 되도록 모든 뿌요들을 아래로 내린다.
 *
 * 5. 위를 반복하며 더이상 터질 뿌요가 없을때 종료
 */
public class Main {
    static char[][] map = new char[12][6];
    static int[] dx = new int[]{0,1,-1,0};
    static int[] dy = new int[]{1,0,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<12; i++) map[i] = br.readLine().toCharArray();

        int sum = 0;
        while(true) {
            List<int[]> bombList = find();
            if(bombList.isEmpty()) break;
            bomb(bombList);
            gravity();
            sum++;
        }

        System.out.println(sum);
    }

    private static void bomb(List<int[]> bombList) {
        for(int[] arr : bombList) {
            map[arr[0]][arr[1]] = '.';
        }
    }

    //어떤 행이 블록이면서, 그 아래행은 .일때만 중력처리를 해줌. 그리고 또 돌면서 중력처리 해주고...
    private static void gravity() {
        for(int j=0; j<6; j++) {
            for(int i=10; i>=0; i--) {
                if(map[i][j] != '.' && map[i+1][j]=='.') {
                    int x = i;
                    while(x+1 < 12 && map[x+1][j] == '.') {
                        map[x+1][j] = map[x][j];
                        map[x][j] = '.';
                        x++;
                    }
                }
            }
        }

    }

    private static List<int[]> find() {
        boolean[][] visited = new boolean[12][6];
        List<int[]> bombList = new ArrayList<>();
        for(int i=0; i<12; i++) {
            for(int j=0; j<6; j++) {
                if(visited[i][j] || map[i][j]=='.') continue;
                List<int[]> list = new ArrayList<>();
                dfs(list, visited, map[i][j], i, j);
                if(list.size()>=4) bombList.addAll(list);
            }
        }
        return bombList;
    }

    private static void dfs(List<int[]> list, boolean[][] visited, char ch, int x, int y) {
        list.add(new int[]{x,y});
        visited[x][y] = true;

        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx<0 || ny<0 || nx>=12 || ny>=6 || visited[nx][ny]) continue;
            if(ch==map[nx][ny]) dfs(list, visited, ch, nx, ny);
        }
    }


}
