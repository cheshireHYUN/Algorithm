import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 수빈이는 크기가 무한대인 격자에 살고있으며, 두가지 방법으로 이동 가능하다.
 * (1) 점프 : (x,y) -> (x+1,y),(x-1,y),(x,y+1),(x,y-1) 상하좌우 중 하나로 이동하며 1초 소요
 * (2) 텔레포트 : 미리정해진위치, (x1,y1)<->(x2,y2)로 이동하며 10초 소요
 * 수빈이의 위치와 집의 위치가 주어졌을때 집에 가는 가장 빠른 시간?
 *
 * 풀이
 * 맵을 완전탐색 형태로 채우는 방법이 있을텐데 그럼 10억x10억 맵에서 너무 큰 시간복잡도일것임
 * 이때 반대로, 10억x10억이면 점프로 가는것보다는 텔레포트를 통한 이동이 빠를것.
 * 그런데 텔레포트가 점프수준의 거리차라면?... => 결론적으로 텔레포트를 간선 후보로만 넣으면 어떨까?
 * 즉 너무 큰 이차원 배열이기 떄문에, 그래프로 만들면서 '의미있는 거리'값만 남기는것
 *
 * (1) 시작점, 도착점, 텔레포트 점들에 대해 정점 만들기
 * (2) 텔레포트 간선(cost=10)만들기
 * (3) 완성된 그래프에 대해 플로이드워셜(정점이 8개뿐)
 *      이때 각 정점의 최대비용을 점프(맨해튼거리) 및 텔레포트로 초기화해두고
 *      플로이드워셜로 중점기준으로 탐색하며 특정 텔레포트를 거쳤을때 더 짧아진다면 업데이트
 */
public class Main {// 클래스명 Main으로 수정
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static long getManhattan(Point p1, Point p2) {
        return (long)Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        ArrayList<Point> points = new ArrayList<>();
        points.add(start);

        // 텔레포트 정보 입력
        int[][] teleport = new int[3][4];
        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) teleport[i][j] = Integer.parseInt(st.nextToken());
            points.add(new Point(teleport[i][0], teleport[i][1]));
            points.add(new Point(teleport[i][2], teleport[i][3]));
        }
        points.add(end);
        int V = points.size(); // 총 8개

        // 모든 정점 사이의 거리를 맨해튼 거리(점프)로 초기화
        long[][] dist = new long[V][V];
        for(int i=0; i<V; i++) {
            for(int j=0; j<V; j++) {
                dist[i][j] = getManhattan(points.get(i), points.get(j));
            }
        }

        // 텔레포트 간선 추가, 점프보다 텔레포트가 빠른경우 업데이트
        for(int i=1; i<=5; i+=2) { //(1,2), (3,4), (5,6)이 텔레포트 쌍
            long tCost = 10;
            if(dist[i][i+1] > tCost) {
                dist[i][i+1] = dist[i+1][i] = tCost;
            }
        }

        // 플로이드워셜 (거쳐가는 점 k, 시작점 i, 끝점 j)
        for(int k=0; k<V; k++) {
            for(int i=0; i<V; i++) {
                for(int j=0; j<V; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        System.out.println(dist[0][V-1]);
    }

}