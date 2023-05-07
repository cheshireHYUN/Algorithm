import java.util.*;

public class Main {
    static int[] dx = {1,0,-1,0,1,1,-1,-1};
    static int[] dy = {0,1,0,-1,-1,1,-1,1};
    static int[][] check;
    static int[][] map;
    static int h,w;

    public static class Node {
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        int x,y;
    }

    private static void bfs(Node node) {
        Queue<Node> Q = new LinkedList<Node>();
        Q.offer(node);
        while(!Q.isEmpty()){
            Node nn = Q.poll();
            for(int i=0; i<8; i++){
                int nx = nn.x+dx[i];
                int ny = nn.y+dy[i];
                if(nx>=0 && ny>=0 && nx<h && ny<w &&
                        map[nx][ny]==1 && check[nx][ny]==0){
                    check[nx][ny] = 1;
                    Q.offer(new Node(nx,ny));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> answerList = new ArrayList<Integer>();
        while(true){
            w = sc.nextInt();
            h = sc.nextInt();
            if(w==0 && h==0) break;

            map = new int[h][w];
            check = new int[h][w];
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    map[i][j] = sc.nextInt();
                }
            }
            int cnt = 0;
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    if(map[i][j]==1 && check[i][j]==0) {
                        bfs(new Node(i, j));
                        cnt++;
                    }
                }
            }
            answerList.add(cnt);
        }
        for(int answer : answerList){
            System.out.println(answer);
        }
    }


}