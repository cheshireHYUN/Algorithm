import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 숫자카드
 * 정수하나가 적혀진 카드다. 숫자카드 N개를 가지고 있을때 정수 M개가 주어진 상황에서
 * 이 숫자 카드를 상근이가 갖고있을지 구하자.
 * (풀이) contains()를 쓰려고 했는데 이거 시간복잡도 N이여서 MXN으로 시간초과남
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //상근이가 가진 숫자카드 갯수
        StringTokenizer st = new StringTokenizer(br.readLine()); //숫자카드에 적힌 정수
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<N; i++) set.add(Integer.parseInt(st.nextToken()));

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(set.contains(num)) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.println(sb);
    }
}