import java.util.*;

public class Main {
    static int col;
    static int row;
    static int[][] map;
    static int[][] checkArr;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    private static int bfs(int x, int y){
        Queue<int[]> Q = new LinkedList<int[]>();
        Q.offer(new int[] {x,y});
        checkArr[x][y]=1;
        int cnt = 1;
        // 방향벡터 돌면서 다음노드 찾고, 해당되면 큐에 추가해서 큐가 더이상 없을때까지(다음노드 없을때까지) 반복
        while(!Q.isEmpty()){
            int[] data = Q.poll();
            int currX = data[0];
            int currY = data[1];
            for(int i=0; i<4; i++){
                int nx = currX+dx[i];
                int ny = currY+dy[i];
                if(nx>=0 && nx<row && ny>=0 && ny<col
                    && checkArr[nx][ny]==0 && map[nx][ny]==0){
                    Q.offer(new int[] {nx,ny});
                    checkArr[nx][ny]=1;
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        row = sc.nextInt();
        col = sc.nextInt();
        map = new int[row][col];
        checkArr = new int[row][col];
        int k = sc.nextInt();
        for(int i=0; i<k; i++){
            int x1 = sc.nextInt(); //왼쪽아래
            int y1 = sc.nextInt();
            int x2 = sc.nextInt(); //오른쪽위
            int y2 = sc.nextInt();
            //항상 두번째로 입력받은게 더 크다
            for(int x=x1; x<x2; x++){
                for(int y=y1; y<y2; y++){
                    //x는 col, y는 row니까
                    map[y][x] = 1;
                }
            }
        }

        List<Integer> areaList = new ArrayList<Integer>();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if (map[i][j] != 1 && checkArr[i][j] != 1) {
                    areaList.add(bfs(i,j));
                }
            }
        }

        System.out.println(areaList.size());
        Collections.sort(areaList);
        for(int area : areaList){
            System.out.print(area+" ");
        }

    }
}