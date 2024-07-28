import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** 지름길
 * 세준이는 차를 타고 D킬로미터 고속도로를 지난다.
 * 지름길이 있고, 모든 지름길은 일방통행이다. 역주행은 불가
 * 운전해야하는 거리의 최솟값을 구하라.
 * (풀이) 
 * 가중치 없는 최단경로 => BFS, 가중치있는 여러노드기준 최단경로 => 플로이드워샬
 * 가중치가 있는 한 노드 기준 최단경로문제 => 다익스트라!!!!
 * 다익스트라 : 모든정점을 MAX로 두고, 출발정점의 누적가중치를 0으로 두고 시작한다.
 * 출발정점의 연결정점에 각 비용을 누적가중치에 저장하고, 연결정점을 PQ에 추가한다.
 * 그리고 PQ에서 하나 꺼내서 체크하고 반복.... 만약 체크정점은 아니지만
 * 크기가 현재크기보다 크다면 바꿔치기도 해준다.
 *
 * 즉, 현재 문제가 가중치가 다른 그래프로 표현이 된다.
 * 또한 지름길만 연결해선 도착할 수 없으므로, 1씩 증가하는 그래프라고 보면 된다.
 * 1씩 증가시키다가 지름길이 있으면 그때 다익스트라를 진행!
 */
public class Main {
    private static class Node {
        int node;
        int weight;
        public Node(int n, int w){
            this.node = n;
            this.weight = w;
        }
    }
    static int N,D, disatance[];
    static List<List<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //지름길의 갯수
        D = Integer.parseInt(st.nextToken()); //고속도로의 길이
        for(int i=0; i<=10001; i++) graph.add(new ArrayList<>());
        disatance = new int[10001];
        for(int i=0; i<disatance.length; i++) disatance[i] = i; //각 거리는 1씩 증가. 즉 정석루트일때 길이를 미리 셋팅

        //지름길 연결리스트
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(new Node(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        dijkstra(0);
        System.out.println(disatance[D]);
    }

    //연결리스트를 통해 현재 점과 연결된 부분들의 최단거리를 업데이트 해놓고, 인덱스 자체는 순서대로 전부 순회한다.
    //그러다보면 1 2 3 4 5 6 1 8 9 10 5 12 13... 이런식으로 중간이 바뀔거고, 그부분에 대해 정석대로 또 +1한값을 같이 업데이트 해준다.
    private static void dijkstra(int start){
        if(start>D) return; //현재위치가 목표길이보다 크면 종료
        
        //지름길을 사용했을경우, 더 짧아진 길이 업데이트 되도록 함
        if(disatance[start+1] > disatance[start]+1) disatance[start+1] = disatance[start]+1;

        //현재 위치의 지름길을 사용해 도착했을때, 
        //즉 현재길이+지름길 가중치를 통해 도착한 길이가 원래도착점의 길이보다 작으면 맞는 지름길인거지
        for(int i=0; i<graph.get(start).size(); i++){
            if(disatance[start]+graph.get(start).get(i).weight
                 < disatance[graph.get(start).get(i).node]){
                disatance[graph.get(start).get(i).node] = disatance[start]+graph.get(start).get(i).weight;
            }
        }
        dijkstra(start+1);
    }
}