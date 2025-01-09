import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/** 요세푸스 문제
 * 1번부터 N번까지 N명의 사람이 원을 이루며 앉아있고, 양의정수 K(<=N)이 주어진다.
 * 순서대로 K번째 사람을 제거한다. 한사람이 제거되면 남은 원을 반복한다.
 * N명의 사람이 모두 제거되면 종료되며, 제거되는 순서를 요세푸스 순열이라고 한다.
 * N,K가 순서대로 주어진다.
 *
 * 풀이
 * Queue에 전부 넣고 돌면서 K번째 카운트마다 빼버리면됨
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for(int i=0; i<N; i++) q.add(i+1);
        int cnt = 0;
        while(!q.isEmpty()) {
            cnt++;
            int num = q.poll();
            if(cnt == K) {
                cnt = 0;
                sb.append(num).append(", ");
            }
            else q.add(num);
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append(">");
        System.out.println(sb);
    }
}