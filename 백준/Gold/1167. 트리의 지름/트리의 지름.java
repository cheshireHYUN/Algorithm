import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** 트리의 지름
 * 트리에서 임의의 두 점 사이 거리중 가장 긴것을 말한다. 지름을 구하자
 * 풀이 : 플루이드워셜? O(V^3) => 1000,0000,0000,0000 => 절대안됨
 * 그럼 DFS로 각 정점으로부터 다른정점까지의 최단거리를 구해보자 => 시간초과
 *
 * 트리는 사이클 없이 연결되어있고, 가장 먼 두 정점의 경로는 유일하다.
 * 그리고 최장거리 말고도 각 노드의에서의 최장거리는 최장거리의 일부가 겹친다.
 * 따라서 가장 먼 정점만 구하면 된다.
 * (1) 임의의 정점 하나에서 가장 먼 정점을 구한다
 * (2) (1)로 구한 정점으로부터 가장 먼 정점을 구한다.
 *
 * 이렇게하면 dfs2번으로 정답이 구해진다.
 */
public class Main {
    static class Node {
        int targetNode, length;
        public Node(int n , int l){
            this.targetNode = n;
            this.length = l;
        }
    }
    static int V, node;
    static boolean[] check;
    static List<ArrayList<Node>> list = new ArrayList<>();
    static int max=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        for(int i=0; i<V+1; i++) list.add(new ArrayList<>());

        StringTokenizer st;
        for(int i=1; i<V+1; i++){
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            while(true){
                int targetNode = Integer.parseInt(st.nextToken());
                if(targetNode == -1) break;
                int length = Integer.parseInt(st.nextToken());
                list.get(node).add(new Node(targetNode, length));
            }
        }

        //임의의 노드(1)에서 가장 먼 노드를 찾는다
        check = new boolean[V+1];
        dfs(1,0);
        
        //node에서 가장 먼 노드까지의 거리를 구한다
        check = new boolean[V+1];
        dfs(node, 0);
        System.out.println(max);
    }

    private static void dfs(int x, int len){
       if(len > max){
           max = len;
           node = x;
       }
       check[x] = true;

       for(int i=0; i<list.get(x).size(); i++){
           Node n = list.get(x).get(i);
           if(!check[n.targetNode]) {
               dfs(n.targetNode, n.length+len);
               check[n.targetNode] = true;
           }
       }
    }
}