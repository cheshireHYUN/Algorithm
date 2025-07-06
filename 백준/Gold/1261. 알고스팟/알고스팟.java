import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 알고스팟
 * 미로는 N*M크기 반방또는 벽으로 이루어져있고, 벽은 부수지않으면 이동 불가함
 * 운영진은 여러명이지만 항상 모두 같은방에 있어야함.
 * 이동은 상하좌우에 있는 빙방으로 가능하고 미로 밖으론 이동 불가
 *
 * (1,1)에 있을때 운영진이 N,M으로 이동하려면 최소 몇개의 벽을 부숴야 하는지 구하라.
 *
 * 다시말해, 도착지까지 가능 모든 경우의 수 중에서 벽을 가장 적게 부술 수 있는 방법을 찾는것.
 * DFS를 활용해서 모든 경로를 찾고, 그중에서 가장 적게 부수는 경우의수를 리턴 -> 시간복잡도 터짐 O(4^(N*M)).. DP가 필수
 *
 * 따라서 중요한건 도착점으로 가는 경로를 찾되,최대한 벽을 피해서 가는것
 * BFS를 통해 경로를 탐색하면서 (1)현재 벽을 가장 적게부순 경로부터 탐색 (2)이미 지나간곳은 진행X
 *
 */
public class Main {
    static int[][] map;
    static int N,M;
    static int[] dx = new int[]{0,1,-1,0};
    static int[] dy = new int[]{1,0,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            String str = br.readLine();
            for(int j=1; j<=M; j++) map[i][j] = Character.getNumericValue(str.charAt(j - 1));
        }

        System.out.println(bfs(1,1));
    }

    private static class Point implements Comparable<Point>{
        int x;
        int y;
        int cnt;

        Point(int x, int y, int cnt) {
            this.x =x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }
    }

    public static int bfs(int x, int y) {
        //벽을 부순 갯수를 오름차순으로 꺼낸다.
        PriorityQueue<Point> q = new PriorityQueue<Point>();
        q.offer(new Point(x,y,0)); //시작점 셋팅
        boolean[][] visited = new boolean[N+1][M+1];
        visited[x][y] = true;

        while(!q.isEmpty()) {
            Point curr = q.poll();
            if(curr.x==N && curr.y == M) return curr.cnt;

            for(int i=0; i<4; i++) {
                int nextX = curr.x+dx[i];
                int nextY = curr.y+dy[i];

                if(nextX<1 || nextY<1 || nextX>N || nextY>M || visited[nextX][nextY]) continue;

                if(map[nextX][nextY]==0) {
                    visited[nextX][nextY] = true;
                    q.offer(new Point(nextX, nextY, curr.cnt));
                } else {
                    visited[nextX][nextY] = true;
                    q.offer(new Point(nextX, nextY, curr.cnt+1));
                }
            }
        }

        return 0;
    }
}
