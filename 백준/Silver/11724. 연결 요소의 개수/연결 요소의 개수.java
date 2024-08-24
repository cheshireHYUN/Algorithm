import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/** 연결요소의 갯수
 * 무방향 그래프 주어졌을때 연결요소의 갯수를 구하라.
 * 풀이 : Union&find 또는 BFS. 나는 유니온앤 파인드 써야지 효율좋우니까
 */
public class Main {
    private static int N,M,parents[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //정점의 갯수
        M = Integer.parseInt(st.nextToken()); //간선의 갯수
        parents = new int[N+1];
        for(int i=1; i<N+1; i++) parents[i] = i;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }

        //혹시 업데이트 안됐을 parents를 위해 find한번 더
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i=1; i<N+1; i++) set.add(find(parents[i]));
        System.out.println(set.size());
    }

    private static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    private static int find(int v){
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }
}