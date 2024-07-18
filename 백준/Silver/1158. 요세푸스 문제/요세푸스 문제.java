import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 요세푸스 문제
 * (1) 1~N까지 N명이 원으로 앉아있고
 * (2) K번째 사람을 제거한다. 그러면 K+1번부터 다시 1로 세는거임
 * (3) (2)를 반복
 *
 * 풀이 : 결국 계속 순회해야하므로 큐를 사용하면 편하지 않을까?
 * if(cnt==N) list.add(poll());, cnt=1;
 * else q.add(poll()) cnt++
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1; i<=N; i++) q.offer(i);
        int cnt = 1;

        sb.append("<");
        while(!q.isEmpty()){
            if(cnt==K){
                sb.append(q.poll()).append(", ");
                cnt = 1;
            } else{
                q.offer(q.poll());
                cnt++;
            }
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append(">");
        System.out.println(sb);
    }
}