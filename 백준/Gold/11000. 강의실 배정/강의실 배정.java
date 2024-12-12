import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 강의실 배정
 * Si에 시작해서 Ti에 끝나는 N개의 수업이 주어진다.
 * 최소의 강의실을 사용해 모든 수업을 가능하게 해야한다.
 * 참고로 수업이 끝난 직후에 다음 수업을 시작할 수 있다.
 *
 * 제한
 * 1<=N<=20만
 *
 * 출력
 * 강의실의 갯수를 출력하라
 *
 * 풀이
 * (1) 모든 수업을 리스트에 담고 정렬(종료시간이 빠른순, 시작시간이 빠른순)
 * (2) 회의실배정 로직으로 한 강의실에 들어갈 수 있는 수업의 경우 list에서 remove
 * (3) list가 비어있지 않다면 강의실을 하나 더 늘리고, list에서 remove
 * => 시간초과!!
 *
 * 풀이2
 * 우선순위 큐에 강의실의 갯수를 저장하는 방식을 활용한다.
 * 즉, 가장 빨리시작해서 빨리 끝나는 수업을 큐에 넣어두고,
 * 다른 수업들을 탐색하면서 우선순위 큐의 peek값(가장 빨리 시작하고 빨리끝나는 값)과 비교해서
 * 들어갈 수 있다면 넘어가고, 없다면 우선순위큐에 추가한다.
 */
public class Main {
    private static class Lesson implements Comparable<Lesson>{
        private int startTime;
        private int endTime;
        public Lesson(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Lesson o) {
            if(this.startTime == o.startTime) return this.endTime - o.endTime;
            else return this.startTime - o.startTime;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        List<Lesson> list = new ArrayList<>();
        for(int i=0; i<cnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Lesson(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int endTime = 0;
        for(Lesson lesson : list) {
            endTime = lesson.endTime;
            if(!pq.isEmpty() && pq.peek()<=lesson.startTime) pq.poll(); //이전 강의실 쓸수있는 경우 이전강의를 빼고 새강의를 넣는다
            pq.add(endTime);
        }

        System.out.println(pq.size());
    }
}