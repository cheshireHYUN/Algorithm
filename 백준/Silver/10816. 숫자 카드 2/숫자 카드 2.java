import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 숫자카드 2
 * 정수하나가 적혀진 카드이다. 상근이가 N개를 가지고 있고 정수 M개가 주어졌을때
 * 이 수가 적혀있는 숫자카드를 상근이가 몇개 가지고 있는지 구하는 프로그램을 작성하라.
 * 풀이 : 아까랑 다르게 이번엔 Map을 사용하면 될듯 시간복잡도 마찬가지로 O(1)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //상근이가 가진 숫자카드 갯수
        StringTokenizer st = new StringTokenizer(br.readLine()); //숫자카드에 적힌 정수
        Map<Integer, Integer> map = new HashMap();
        for(int i=0; i<N; i++) {
            int key = Integer.parseInt(st.nextToken());
            map.put(key, map.getOrDefault(key,0)+1);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(map.get(num) == null? 0 : map.get(num)).append(" ");
        }
        System.out.println(sb);
    }
}