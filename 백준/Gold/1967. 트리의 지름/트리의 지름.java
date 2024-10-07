import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/** 트리의 지름
 * 트리는 사이클이 없는 무방향 그래프이다.
 * 어떤 두 노드를 선택해도 둘 사이에 경로가 항상 하나임
 * 트리에서 어떤 두 노드를 선택해서 양쪽으로 쫙 당길때, 가장 길게 늘어나는 경우가 있다.
 * 이럴때 트리의 모든 노드들은 이 두 노드를 지름의 끝점으로 하는 원 안에 들어간다.
 * 이 두 노드 사이 경로의 길이를 트리의 지름이라고 하자.(= 경로중 가장 긴 것)
 * 입력으로 루트가 있는 트리를 가중치 있는 간선으로 줄때, 트리 지름을 출력해서 구하는 프로그램.
 *
 * 제한
 * 1<=n<=1만
 * 가중치는 100보다 크지않은 양의정수
 *
 * 풀이
 * 비슷한 문제 풀었을땐 dfs 두번 돌려서 가장 먼 노드를 두번 구하면 됐음 그렇게 하면 될듯?
 * 루트노드를 기준으로 가장 먼 노드 구하고, 이 노드에서 가장 먼 노드 까지의 길이를 구한다.
 */
public class Main {
    static int n;
    static List<ArrayList<int[]>> graph = new ArrayList<>();
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        check = new boolean[n+1];
        StringTokenizer st;
        for(int i=0; i<n+1; i++) graph.add(new ArrayList<>());
        while(n-->1) {
            //부모노드, 자식노드, 가중치
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(node1).add(new int[]{node2,cost});
            graph.get(node2).add(new int[]{node1,cost});
        }


        check[1] = true;
        dfs(1, 0);
        
        Arrays.fill(check, false);
        check[farNode] = true;
        dfs(farNode, 0);
        System.out.println(farCost);
    }

    static int farNode, farCost=-1;
    private static void dfs(int v, int sumCost){
        if(farCost < sumCost) {
            farNode = v;
            farCost = sumCost;
        }

        List<int[]> nodeList = graph.get(v);
        for(int[] nl : nodeList) {
            int nodeNum = nl[0];
            int cost = nl[1];
            if(check[nodeNum]) continue;
            check[nodeNum] = true;
            dfs(nodeNum, sumCost+cost);
            check[nodeNum] = false;
        }
    }
}