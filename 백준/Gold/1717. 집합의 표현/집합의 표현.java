import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 집합의 표현
 * N+1개 집합이 있을때, 두 연산을 수행한다.
 * 1) 합집합 연산
 * 2) 두 원소가 같은 집합에 포함되는지 확인
 *
 * 제한
 * 1<=N<=100만
 * 1<=m<=10만
 *
 * 풀이
 * 유니온앤파인드도 될거같은데요
 */
public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n+1];
        for(int i=0; i<n+1; i++) parents[i] = i;

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 0) union(b,c);
            else if(a==1) {
                if(find(b) == find(c)) sb.append("YES").append('\n');
                else sb.append("NO").append('\n');
            }
        }
        System.out.println(sb);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    private static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}
