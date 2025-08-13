import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 파일정리
 * 1. 파일을 확장자별로 정리해서 몇개인지 -> Map을 활용해서 cnt
 * 2. 보기 편하게 확장자들을 사전순으로 정렬 -> Key만 정렬해서 출력
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new TreeMap<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(),".");
            String title = st.nextToken();
            String extension = st.nextToken();
            map.put(extension, map.getOrDefault(extension, 0)+1);
        }
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> e : map.entrySet()) {
            sb.append(e.getKey()).append(' ').append(e.getValue()).append('\n');
        }
        System.out.println(sb);
    }
}
