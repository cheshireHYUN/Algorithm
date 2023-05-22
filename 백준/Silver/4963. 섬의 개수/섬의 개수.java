import java.util.*;

public class Main {

    public static int row;
    public static int col;
    public static int[][] map;
    public static int[][] check;
    public static int[] dx = {0,0,1,-1, -1,-1,1,1};
    public static int[] dy = {1,-1,0,0, 1,-1,1,-1};

    private static class Node {
        public Node(){}
        public Node(int i, int j){
            x = i;
            y = j;
        }
        int x,y;
    }

    private static void bfs(Node node) {
        Queue<Node> Q = new LinkedList<>();
        Q.offer(node);

        while(!Q.isEmpty()){
            Node newNode = Q.poll();
            for(int i=0; i<8; i++){
                int nx = newNode.x+dx[i];
                int ny = newNode.y+dy[i];
                if(nx>=0 && nx<row && ny>=0 && ny<col && map[nx][ny]==1 && check[nx][ny]==0){
                    check[nx][ny] = 1;
                    Q.offer(new Node(nx,ny));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //1은 섬, 0은 바다일때 바다경로의 갯수 가하면됨 -> 최단경로이므로 bfs 쓰면될듯
        List<Integer> list  = new ArrayList<Integer>();
        while(true){
            col = sc.nextInt();
            row = sc.nextInt();
            if(col == 0 && row == 0) break;
            map = new int[row][col];
            check = new int[row][col];
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    map[i][j] = sc.nextInt();
                }
            }
            int cnt = 0;
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    if(map[i][j] == 1 && check[i][j] == 0){
                        check[i][j] = 1;
                        bfs(new Node(i,j));
                        cnt++;
                    }
                }
            }
            list.add(cnt);
        }

        for(int i : list) System.out.println(i);
    }


}