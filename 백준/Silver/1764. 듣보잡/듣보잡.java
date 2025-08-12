import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 듣보잡
 * 김진영이 듣도보도 못한 사람의 명단과 보도못한 사람의 명단이 주어질때 듣보명단을 구하라.
 *
 * 풀이
 * 해시셋에 듣도못한 사람을 넣고
 * 보도못한 사람을 순회하면서 해시셋에 있으면 정답에 추가
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        List<String> result = new ArrayList<>();
        for(int i=0; i<N; i++) set.add(br.readLine());
        for(int i=0; i<N; i++) {
            String str =br.readLine();
            if(set.contains(str)) result.add(str);
        }

        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append('\n');
        for(String s : result) sb.append(s).append('\n');
        System.out.println(sb);

    }
}
