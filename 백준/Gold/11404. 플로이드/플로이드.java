import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 플로이드
 * n개의 도시가 있고, 한도시에서 출발해 다른도시에 도착하는 m개의 버스가 있다
 * 각 버스는 한번 사용할 때 필요한 비용이 있다.
 * 모든 도시의 쌍(A,B)에 대해 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램
 *
 * 제한
 * 2<=n<=100
 * 1<=m<=10만
 *
 * 풀이
 * 모든점에서 모든점까지의 최단거리 구하는 알고리즘이므로 플로이드 워셜임.
 * 플로이드 워셜의 시간복잡도는 N^3 = 1000000, 즉 100만이므로 안정적!
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        //시작도시, 도착도시, 비용순
        int[][] map = new int[n+1][n+1];
        for(int[] ma : map) Arrays.fill(ma, Integer.MAX_VALUE);
        for(int i=1; i<n+1; i++) map[i][i] = 0;

        StringTokenizer st;
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[start][end] = Math.min(map[start][end], cost);
        }

        //플로이드 워샬
        for(int mid = 1; mid<n+1; mid++) {
            for(int start = 1; start<n+1; start++) {
                for (int end = 1; end < n + 1; end++) {
                    if(map[start][mid] == Integer.MAX_VALUE || map[mid][end] == Integer.MAX_VALUE) continue;
                    if(map[start][end] > map[start][mid]+map[mid][end]) {
                        map[start][end] = map[start][mid]+map[mid][end];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(map[i][j] == Integer.MAX_VALUE) sb.append(0).append(' ');
                else sb.append(map[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}