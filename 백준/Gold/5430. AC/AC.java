import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/** AC
 * R은 순서를 뒤집고 D는 첫번째 수를 버린다
 * 배열이 비어있는데 D를 쓰면 에러가 발생한다. (error 출력)
 * 입력 : T<=100, 1<=p<=10만, 0<=n<=10만
 * 풀이 : 문자열을 압축한 뒤 구현했으나 시간초과 -> O(T*p*n) = 10^12
 * 정답 : Deque를 통해 푼다. 양방향으로 접근 가능한 큐 이므로
 * R : 순서를 순회하며 뒤집을 필요 없이 시작점 자체를 왼/오로 boolean관리 하면됨
 * D : pollFirst()와 pollLast()를 이용하면 된다.
 */
public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayDeque<Integer> deque;

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            char[] command = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");
            deque = new ArrayDeque<>();
            for (int i = 0; i < n; i++) deque.add(Integer.parseInt(st.nextToken()));

            solution(command, deque);
        }
        System.out.println(sb);
    }

    private static void solution(char[] command, ArrayDeque<Integer> deque) {
        boolean isRight = true; //순방향으로 초기화
        for(char cmd : command) {
            if(cmd == 'R') {
                isRight = !isRight;
                continue;
            }
            //D인경우 순방향이면 앞을, 역방향이면 뒤를 삭제
            if(isRight) {
                if(deque.pollFirst() == null) {
                    sb.append("error").append('\n');
                    return;
                }
            } else {
                if(deque.pollLast() == null) {
                    sb.append("error").append('\n');
                    return;
                }
            }
        }
        makePrint(deque, isRight);
    }

    private static void makePrint(ArrayDeque<Integer> deque, boolean isRight) {
        sb.append('[');
        if(deque.size()>0){
            if(isRight) {
                sb.append(deque.pollFirst());
                while(!deque.isEmpty()) sb.append(',').append(deque.pollFirst());
            } else {
                sb.append(deque.pollLast());
                while(!deque.isEmpty()) sb.append(',').append(deque.pollLast());
            }
        }
        sb.append(']').append('\n');
    }

}