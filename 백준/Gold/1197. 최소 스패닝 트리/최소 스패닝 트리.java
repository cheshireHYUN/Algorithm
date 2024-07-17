import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/** 최소 스패닝 트리
 * 최소스패닝트리 : 주어진 그래프의 모든 정점을 연결하는 부분그래프 중 가중치 합이 최소인 트리
 * 풀이 : MST의 대표적인 풀이두개는 (1) 크루스칼 (2) 프림
 * (1) 크루스칼 : 간선중심! 즉 간선을 정렬해두고 작은것부터 선택하면서 사이클이 아닌 MST가 되도록함
 * (2) 프림 : 정점중심! 임의의 정점을 선택하고 연결간선중 가장 작은걸 선택해 전개 (주의 : 고려할 간선은 모든 선택정점의 간선들임)
 * 둘다 풀어보면 좋겠지만 오늘은 우선 크루스칼로 먼저 풀어보자!
 * Union&Find를 사용한다!
 */
public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int from;
        int cost;
        public Node(int to, int from, int cost) {
            this.to = to;
            this.from = from;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
    static ArrayList<Node> list = new ArrayList<>();
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); //정점갯수
        int E = Integer.parseInt(st.nextToken()); //간선갯수

        //각 간선정보를 list에 저장
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Node(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        //list를 간선의 cost가 적은순으로 정렬
        Collections.sort(list);

        //부모(root)정보를 저장하는 배열을 셋팅
        // 1부터 시작함, 처음엔 연결X이므로 자기자신이 루트
        parents = new int[V+1];
        for(int i=1; i<V+1; i++) parents[i] = i;

        //간선을 하나씩 선택한다.
        int sum = 0, cnt = 0;
        for(Node n : list){
            if(union(n.from, n.to)){
                sum += n.cost;
                cnt++;

                if(cnt == V-1) break;
            }
        }
        System.out.println(sum);
    }

    //유니온 : 같은 사이클이 아닌 노드라면 연결한다
    public static boolean union(int from, int to){
        int fromRoot = find(from);
        int toRoot = find(to);

        if(fromRoot == toRoot) return false;
        else parents[toRoot] = fromRoot;
        return true;
    }
    
    //파인드 : 같은사이클인지 검사한다. 즉 부모를 찾는다
    public static int find(int v){
        if(parents[v] == v) return v;
        else return parents[v] = find(parents[v]);
    }
}