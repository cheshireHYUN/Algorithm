import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/** 숫자카드
 * 상근이가 N개의 숫자카드를 가지고 있고 정수M개가 주어졌을때
 * 이 수가 적힌 숫자카드를 상근이도 갖고있는지 구하자
 * 풀이 : HashSet을 이용해서 contains()하나 보면 될듯 nlogn이라 3000만?
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<N; i++) set.add(Integer.parseInt(st.nextToken()));
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            if(set.contains(Integer.parseInt(st.nextToken()))) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.println(sb);

    }
}