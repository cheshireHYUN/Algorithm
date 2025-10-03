import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** 트리색칠하기
 * 정점이 1~N까지 있고, 모두 흰색(0)이다.
 * 하나의 정점에 색을 칠하면 그 아래 모든 정점이 같은색으로 칠해진다.
 * 모든 정점을 주어진 색으로 칠하고 싶을때 최소 몇번 칠해야 되는지 구해보자.
 *
 * 풀이
 * 모두 흰색인 상태에서 두번째줄에 주어진 색으로 칠하는 최소 시도 갯수를 찾는다.
 * 색칠 자체는 트리를 양방향 그래프로 선언해둔 뒤 DFS/BFS를 사용해서 아래쪽을 칠하면 될듯
 * 최소시도 갯수는? -> 트리를 맨 위부터 탐색하면서 답안과 동일색이면 pass, 다르면 부모노드 색으로 색칠...
 */
public class Main {
    static List<List<Integer>> graph = new ArrayList<>();
    static int N,answer;
    static int[] color;
    static boolean[] isVisited, temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //정점의 갯수
        color = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) color[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<N+1; i++) graph.add(new ArrayList<>());
        //양방향 그래프로 트리 생성
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        answer = color[1]==0 ? 0:1; //1번을 색칠 할 경우 cnt=1로 시작
        isVisited = new boolean[N+1];
        isVisited[1] = true;
        dfs(1);
        System.out.println(answer);
    }

    //부모의 색과 자식의 색이 다르면 칠한다.
    private static void dfs(int parent) {
        for(int i=0; i<graph.get(parent).size(); i++) {
            int child = graph.get(parent).get(i);
            if(!isVisited[child]) {
                isVisited[child] = true;
                if(color[child] != color[parent]) answer++;
                dfs(child);
            }
        }

    }
}
