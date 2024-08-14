import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/** 연결요소의 갯수
 * 방향없는 그래프에서 연결요소의 갯수를 찾는 프로그램을 작성하라.
 * DFS나 BFS로 각자 세도 되고, 유니온앤파인드를 해도 되겠는걸
 */
public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //정점
        int M = Integer.parseInt(st.nextToken()); //간선
        parents = new int[N+1];
        for(int i=1; i<N+1; i++) parents[i] = i;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }

        HashSet<Integer> set = new HashSet<>();
        for(int i=1; i<N+1; i++) set.add(find(parents[i]));
        System.out.println(set.size());
    }

    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;
        else parents[rootB] = rootA;
        return true;
    }

    static int find(int v){
        if(parents[v] == v) return v;
        else return parents[v] = find(parents[v]);
    }
}