import java.util.*;

public class Main {
    static int row;
    static int col;
    static int k;
    static char[][] map;
    static int[][] check;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int cnt = 0;

    public static void dfs(int x, int y, int len){
        if(x==0 && y==col-1){
            if(len == k) cnt++;
            return;
        }

        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx>=0 && ny>=0 && nx<row && ny<col && check[nx][ny]==0 && map[nx][ny]!='T'){
                check[nx][ny] = 1;
                dfs(nx,ny,len+1);
                check[nx][ny] = 0;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        row = sc.nextInt();
        col = sc.nextInt();
        k = sc.nextInt();
        map = new char[row][col];
        check = new int[row][col];
        for(int i=0; i<row; i++){
            String str = sc.next();
            for(int j=0; j<col; j++){
                map[i][j] = str.charAt(j);
            }
        }

        //경로의 가짓수를 찾으므로 dfs한다.
        check[row-1][0] = 1;
        dfs(row-1,0,1);
        System.out.println(cnt);

    }
}