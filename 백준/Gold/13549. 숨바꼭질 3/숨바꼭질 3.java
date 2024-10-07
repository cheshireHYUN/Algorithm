import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/** 숨바꼭질 3
 * 수빈이는 점 N, 동생은 점 K에 있다.
 * 수빈이의 위치가 X일때 걸으면 1초후 X+-1, 순간이동을 한 경우엔 0초후에 2*X로 이동
 * 수빈이와 동생의 위치가 주어졌을때 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇초후인지 구하라.
 *
 * 제한
 * 0<=N<=10만, 0<=K<=10만
 *
 * 풀이
 * BFS를 통해 해결한다. 다만 가중치가 있으므로 시간을 포함해서 돌린다. 완전탐색,,
 * 이렇게하면 첫판에 (N+1,1) (N-1,1) (2*N,0)이 큐안에 들어갈것이고,
 * 그리고 두번째판에 세가지에 대해 세가지 경로가 또 생길것임.
 * 최대가 10만이니까 이 아래로 돌리면 된다. 모든경로 찾아야하므로 동생위치값을 계속 최소로 갱신해줌
 */
public class Main {
    static int minTime = Integer.MAX_VALUE;
    static int n,k;
    static boolean[] check;
    static int max = 100000; //문제 조건이 최대 10만
    static class Node {
        public int num;
        public int time;
        public Node(int n, int t){
            num = n;
            time = t;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        check = new boolean[max+1];
        bfs();
        System.out.println(minTime);

    }
    private static void bfs(){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(n,0));
        while(!q.isEmpty()) {
            Node node = q.poll();
            check[node.num] = true;
            if(node.num == k) minTime = Math.min(minTime, node.time);

            //순간이동을 먼저 처리함으로써 정상적 방문체크
            if(node.num*2 <= max && check[node.num*2]==false) q.offer(new Node(node.num*2, node.time));
            if(node.num+1 <= max && check[node.num+1]==false) q.offer(new Node(node.num+1, node.time+1));
            if(node.num-1 >= 0 && check[node.num-1]==false) q.offer(new Node(node.num-1, node.time+1));

        }
    }
}