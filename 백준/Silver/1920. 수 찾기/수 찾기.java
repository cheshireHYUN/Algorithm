import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/** 수찾기
 * N개의 정수가 주어졌을때 이안에 X가 존재하는지 알아내는 프로그램을 작성하라
 *
 * 제한
 * 1<=N<=10만
 * 1<=M<=10만
 *
 * 풀이
 * 각 숫자가 2^23보다 작으므로 int로 커버 가능
 * 풀이법은 두가지 (1)Set에 저장해서 contains() / (2)이분탐색으로 찾기
 * 우선 1로 풀어보면 Set에 다 저장하고 contains()하며 있으면 1 없으면 0 누적
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) set.add(Integer.parseInt(st.nextToken()));
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) sb.append(set.contains(Integer.parseInt(st.nextToken())) ? 1 : 0).append('\n');
        System.out.println(sb);
    }
}