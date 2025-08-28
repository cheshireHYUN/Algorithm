import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/** 케익배달
 * N명의 고객에게 주문순서대로 배달하며, 받을 수 있을만큼 충분히 가까이까지 배달
 * N명 고객은 10만x10만 배열좌표에 있고, 출발지의 위치도 주어진다.
 * 상하좌우로만 움직일 수 있으며 고객위치까지 가거나 고객의 상하좌우 인접점에 가야함.
 * 선아가 최단거리를 이동하여 입력순대로 배달을 끝낼 수 있는 거리를 계산하는 프로그램을 작성하라.
 *
 * 풀이
 * 고객의 상하좌우 인접점도 가능하니까 어디서 전달해주느냐에 따라 최단경로길이가 바뀜..
 * 이걸 완전탐색 하려고 하면 10만x10만이라 무조건 터짐 => DP 활용한다.
 * DP[2번고객][상] = Math.min(DP[1번고객][상]+거리,DP[1번고객][하]+거리,DP[1번고객][좌]+거리,DP[1번고객][우]+거리) 이런느낌
 *
 * 정해진 순서로만 케익을 전달하고, 중앙/상/하/좌/우 위치만 알면 됨
 * Point)) 장애물이 없으므로 맨해튼거리 공식 사용해서 바로 계산 떄림(그냥 |x1-x2|+|y1-y2|)
 * dp[i][j] = i번째 고객위치의 중앙/상/하/좌/우(j) 위치까지 가는데 걸린 최단거리
 * dp[i][j] = dp[i-1][k]+calDis(i-1 ~ i) :  이전고객위치의 중앙/상/하/좌/우 위치에서 다음위치까지의 거리
 */
public class Main {
    private static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static List<Node> list = new ArrayList<>();
    static Node bakery;
    static long[][] dp;
    static int[] dx = new int[]{0,1,-1,0,0};
    static int[] dy = new int[]{0,0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new long[N][5]; //중앙:0, 상:1, 하:2, 좌:3, 우:4
        StringTokenizer st = new StringTokenizer(br.readLine());;
        bakery = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(
                    new Node(
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    )
            );
        }

        //(1) dp배열 두번째 이상 고객들을 MAX로 초기화
        for(int i=1; i<N; i++) {
            for(int j=0; j<5; j++) dp[i][j] = Long.MAX_VALUE;
        }

        //(2) 첫번째 고객의 5방향에 대한 dp값 셋팅
        for(int i=0; i<5; i++) {
            Node node = list.get(0);
            int nx = node.x+dx[i];
            int ny = node.y+dy[i];
            
            if(outOfRange(nx, ny)) continue;
            dp[0][i] = calDis(bakery.x, bakery.y, nx, ny);
        }

        //(3) 두번째(index=1)고객 이후의 dp값 구하기
        for(int i=1; i<N; i++) {
            Node curr = list.get(i);

            //목표고객(curr)의 각 방향에 대해 맨해튼 범위로 DP채우기
            for(int j=0; j<5; j++) {
                int nx = curr.x + dx[j];
                int ny = curr.y + dy[j];
                if(outOfRange(nx, ny)) continue;

                //이전노드의 5방향중에서 가장 맨해튼거리가 작은 값으로 DP업데이트
                long min = Long.MAX_VALUE;
                for(int k=0; k<5; k++) {
                    if(dp[i-1][k]==Long.MAX_VALUE) continue; //해당지점에 도착X일경우
                    Node preNode = list.get(i-1);
                    int preX = preNode.x + dx[k];
                    int preY = preNode.y + dy[k];
                    if(outOfRange(preX, preY)) continue;
                    min = Math.min(min, dp[i-1][k]+calDis(preX, preY, nx, ny));
                }
                dp[i][j] = min;
            }
        }

        //(4) 가장 최단거리
        long result = dp[N-1][0];
        for(int i=1; i<5; i++) {
            result = Math.min(result, dp[N-1][i]);
        }
        System.out.println(result);
    }

    private static boolean outOfRange(int x, int y) {
        if(x<0 || x>100_000 || y<0 || y>100_000) return true;
        return false;
    }

    //맨해튼거리 공식 사용
    private static long calDis(int x, int y, int nx, int ny) {
        return Math.abs(x-nx)+Math.abs(y-ny);
    }
}
