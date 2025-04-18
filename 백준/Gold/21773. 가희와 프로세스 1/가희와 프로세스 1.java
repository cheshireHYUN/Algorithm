import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 가희와 프로세스
 * 프로세스 선택 기준: 우선순위 값이 제일 큰 프로세스, 여러개면 id가 가장 작은 프로세스
 * 1. 기준에따라 프로세스를 선택 => ids라고 명명함, 실행
 * 2. 1초가 지난 후 프로세스 id가 ids인 프로세스를 제외한 나머지 프로세스의 우선순위가 1상승
 *      프로세스 id가 ids인 프로세스의 실행을 마치는데 필요한 시간은 1 감소
 * 3. 실행시간이 남은 프로세스가 있다면 1로 돌아가고, 그렇지 않으면 종료함.
 * 동시에 실행되는 프로세스는 1개임. 1초일때부터 T초일때까지의 선택 프로세스id를 출력해라.
 * 
 * 풀이
 * A,B,C = id, 시간, 우선순위
 * 예제1은 8초동안 2개의 프로세스를 비교한다
 * (id=1, time=5, prio=1)
 * (id=2, time=5, prio=1)
 *
 * T=1일때 : id=1을 선택해서 실행 => (id=1, time=5, prio=1), (id=2, time=5, prio=1) => 2초가 되면서 id=1의 time--, id=2의 prio++
 * T=2일때 : id=2를 선택해서 실행 => (id=2, time=5, prio=2), (id=1, time=4, prio=1) => 3초가 되면서 id=2의 time--, id=1의 prio++
 * T=3일때 : id=1를 선택해서 실행 => (id=1, time=5, prio=2), (id=2, time=4, prio=2) => 4초가 되면서 id=1의 time--, id=2의 prio++
 * T=4일때 : id=2를 선택해서 실행 => (id=2, time=5, prio=3), (id=1, time=4, prio=2)
 *
 * (0) T초니까 1~T초까지 반복한다.
 * (1) 프로세스 선택 : 우선순위큐를 이용해서 prio값이 큰것을 꺼낸다(같으면 id가 작은것)
 * (2) 꺼낸 프로세스의 id값을 result에 누적
 * (3) 꺼낸 프로세스의 time--, prio--(해당 프로세스 제외한 모든 prio를 키우는것보다, 이것만 -를 주는것이 효율적)
 * (4) 만약 time==0이면 바로 다음 반복 실행, 0이 아니면 다시 큐에 넣고 반복..
 */
public class Main {
    private static class Process implements Comparable<Process>{
        int id;
        int time;
        int prio;
        public Process(int id, int time, int prio) {
            this.id = id;
            this.time = time;
            this.prio = prio;
        }

        @Override
        public int compareTo(Process o) {
            if(this.prio == o.prio) return this.id-o.id;
            else return o.prio-this.prio;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<Process> pq = new PriorityQueue<>();
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            Process p = new Process(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            pq.add(p);
        }

        StringBuilder sb = new StringBuilder();
        while(T-->0 && !pq.isEmpty()) {
            Process curr = pq.poll();
            sb.append(curr.id).append('\n');
            curr.time -= 1;
            curr.prio -= 1;
            if(curr.time != 0) pq.add(curr);
        }

        System.out.println(sb);

    }
}
