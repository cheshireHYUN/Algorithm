import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

/** 행성터널
 * 왕국은 N개의 행성으로 이루어져있고, 행성을 연결하는 터널을 만든다.
 * 행성은 3차원 좌표 위의 한 점이며, 두 행성을 터널로 연결할때 드는 비용은 min(|xA-xB|, |yA-yB|, |zA-zB|)
 * 민력이는 터널을 N-1개 건설해 모두가 연결되게 만든다. 터널의 최소비용??
 *
 * 입력
 * 1<=N(행성의 갯수)<=10만
 * -10억<=좌표<=10억
 *
 * 풀이
 * 완탐의 경우 n!으로 시간복잡도 터진다.
 * 문제는 결국 MST를 구하는거니까 크루스칼을통해 NlogN에서 끝낸다..
 * 근데 문제는 모든 간선을 구하면 메모리든 시간복잡도든 터질 수 있다는것.
 * 따라서 이는 정렬을 통해 해결한다.
 * 1. x/y/z기준으로 각각 정렬해 인접한 점끼리의 차를 구한다(간선의 가중치가 됨)
 * 2. 모든간선을 list에 저장하고, 크루스칼을 실행하여 MST를 구한다
 * (주의) 같은간선이 여러번 선택되진않음 - 크루스칼이니까 당연함..
 *
 */
public class Main {
    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static class Point {
        int number;
        int x;
        int y;
        int z;

        public Point(int n, int x, int y, int z) {
            this.number = n;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static int N;
    static Point[] points;
    static List<Edge> edgeList = new ArrayList<>();
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        StringTokenizer st;
        //행성 좌표정보 저장
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(i,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        //간선 생성
        getEdgeList();

        //크루스칼 알고리즘 : 전체에서 가장 작은 간선 선택, 사이클이 아닐때만!
        parents = new int[N];
        for(int i=0; i<N; i++) parents[i] = i;
        Collections.sort(edgeList);
        int sum = 0;
        for(Edge edge: edgeList) {
            if(union(edge.start, edge.end)) {
                sum += edge.weight;
            }
        }
        System.out.println(sum);
    }

    private static boolean union(int v1, int v2){
        int startRoot = find(v1);
        int endRoot = find(v2);
        if(startRoot == endRoot) return false;
        parents[endRoot] = startRoot;
        return true;
    }

    private static int find(int v){
        if(v==parents[v]) return v;
        return parents[v] = find(parents[v]);
    }

    private static void getEdgeList() {
        Arrays.sort(points, (a1,a2) -> {return a1.x - a2.x;});
        for(int i=0; i<N-1; i++) {
            int weight = Math.abs(points[i].x-points[i+1].x);
            edgeList.add(new Edge(points[i].number, points[i+1].number, weight));
        }
        Arrays.sort(points, (a1,a2) -> {return a1.y - a2.y;});
        for(int i=0; i<N-1; i++) {
            int weight = Math.abs(points[i].y-points[i+1].y);
            edgeList.add(new Edge(points[i].number, points[i+1].number, weight));
        }
        Arrays.sort(points, (a1,a2) -> {return a1.z - a2.z;});
        for(int i=0; i<N-1; i++) {
            int weight = Math.abs(points[i].z-points[i+1].z);
            edgeList.add(new Edge(points[i].number, points[i+1].number, weight));
        }
    }
}