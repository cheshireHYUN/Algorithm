import java.util.*;

public class Main {
    static int col;
    static int row;
    static int[][] map;
    static int[][] check;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static class Node{
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        int x,y;
    }
    private static int BFS(Node node){
        Queue<Node> Q = new LinkedList<Node>();
        Q.offer(node);
        int cnt = 1;
        while(!Q.isEmpty()){
            Node data = Q.poll();
            for(int i=0; i<4; i++){
                int nx = data.x+dx[i];
                int ny = data.y+dy[i];
                if(nx>=0 && ny>=0 && nx<row && ny<col &&
                        check[nx][ny]==0 && map[nx][ny]==0){
                    check[nx][ny] =1;
                    cnt++;
                    Q.offer(new Node(nx, ny));
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        row = sc.nextInt();
        col = sc.nextInt();
        int k = sc.nextInt();
        map = new int[row][col];
        check = new int[row][col];
        for(int i=0; i<k; i++){
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt(); //두번째로 입력한게 반드시 더 크다
            int y2 = sc.nextInt();
            for(int x=x1; x<x2; x++){
                for(int y=y1; y<y2; y++){
                    map[y][x]= 1; //x값은 열값, y값은 행값이므로 순서가 반대
                }
            }
        }
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(map[i][j]==0 && check[i][j]==0){
                    check[i][j] = 1;
                    list.add(BFS(new Node(i,j)));
                }
            }
        }

        System.out.println(list.size());
        Collections.sort(list);
        for(int li : list){
            System.out.print(li+" ");
        }

    }
}