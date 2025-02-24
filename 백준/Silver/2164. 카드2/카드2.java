import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

/** 카드2
 * 1~N까지의 카드가 있고, 1번이 제일 위 N이 제일 아래에 있다.
 * 카드가 한장이 될때까지 다음을 반복한다
 * (1) 제일 위에 있는 카드를 바닥에 버린다
 * (2) 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
 *
 * 풀이
 * Deque로 양방향 큐를 구현해 값을 넣어두고,
 * 1~N이 순서대로 들어가있으니
 * (1) pop()해서 제거
 * (2) pop()한값을 addFirst()해서 가장 아래에 삽입
 * 반복해서 size()가 1이되면 종료
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++) q.offer(i);
        while(q.size() > 1) {
            q.pop();
            q.addLast(q.pop());
        }
        System.out.println(q.pop());
    }
}