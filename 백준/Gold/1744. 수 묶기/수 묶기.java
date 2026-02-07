import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 수열의 두수는 묶어서 지들끼리 곱하고 나머지 전부 더한다.
 * 수열의 모든 수는 단 한번만 묶거나 묶지 않아야한다(즉 겹치게 묶기 불가능)
 * 각 수를 적절히 묶어 그 합이 최대가 되는 프로그램을 작성하라.
 *
 * 풀이
 * 완전탐색을 할 경우, 숫자 4개만 선택하는 순열을 구하는 경우의수 -> 10000C4 절대안됨
 * 최대를 구하는 문제니까
 * - 양수의 경우 큰수를 곱하는게 이득
 * - 음수의 경우 절댓값만 크면 음수끼리 곱하는게 이득
 * - 0의 경우 음수랑 곱해서 뺄셈을 없앨때 활용..
 *
 * 양수일땐 내림차순, 음수(0포함)일땐 오름차순 정렬되는 PQ를 각자 만들어서 입력을 넣어두고
 * (1) 음수일때 최댓값을 구한다
 *     - pq를 이용해 오름차순으로 꺼내며, 자기자신과 다음 값을 꺼낸다(다음값이 없으면 더하고 종료)
 *     - 만약 다음값이 0일경우 곱셈하면 손해이므로 그냥 빼버림(어차피 0이니까 덧셈 할필요x)
 *     - 만약 다음값이 있을경우 자기자신과 곱해서 answer에 누적
 * (1) 양수일때 최댓값을 구한다
 *      - 위와 마찬가지로 내림차순으로 꺼내며 자기자신과 다음값을 꺼낸다(다음값 없으면 더하고 종료)
 *      - 만약 1을 곱하면 오히려 1 손해이므로 1이나올경우엔 그냥 더해준다(현재, 다음값 모두 마찬가지)
 *      - 그외엔 그냥 자기자신과 곱해서 answer에 누적
 */
public class Main {
    static PriorityQueue<Integer> positive = new PriorityQueue<>(Comparator.reverseOrder());
    static PriorityQueue<Integer> negative = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num<=0) negative.add(num);
            else positive.add(num);
        }

        int answer = 0;
        while(!negative.isEmpty()) {
            int curr = negative.poll();
            if(negative.isEmpty()) {
                answer += curr;
                break;
            }
            if(negative.peek() == 0) negative.poll();
            else answer += curr* negative.poll();
        }

        while(!positive.isEmpty()) {
            int curr = positive.poll();
            if(positive.isEmpty()) {
                answer += curr;
                break;
            }
            if(curr == 1) answer += 1;
            else if(positive.peek() ==1) answer += curr+positive.poll();
            else answer += curr*positive.poll();
        }

        System.out.println(answer);
    }
}
