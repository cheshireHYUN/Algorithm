import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 이차원 배열과 연산
 * 3x3인 배열 A가 있고, 인덱스는 1부터 시작한다.
 * 1초가 지날때마다 배열에 연산이 적용된다.
 * R연산 : 배열 A의 모든행에 대해 정렬 수행, 행갯수>=열갯수인 경우 적용.
 * C연산 : 배열 A의 모든열에 대해 정렬 수행, 행갯수<열갯수인 경우 적용.
 *
 * [풀이]
 * 연산은 행/렬의 크기를 계산해서 R연산 또는 C연산을 수행하는것 => map[r][c]==k일때까지!
 * 1. 100초간의 연산을 시작한다. 행=3, 열=3에서 시작
 * 2. 행>=열이면 R연산, 아니면 C연산을 수행한다
 *
 * [R/C연산 정렬 아이디어]
 * 1. 숫자 갯수를 카운팅해서 Map<Number, COunt>에 저장
 * 2. 우선순위를 적용한 Pair 클래스에 넣어 우선순위큐에 저장
 * 3. 우선순위 큐에서 순서대로 값을 꺼내 배열을 다시 갱신, 해당되지 않는 배열은 0으로 초기화
 *
 * [R연산 메소드]
 * 1. for(1~yLength) arr[key][i] 하면서 0이 아닌 값의 number와 value를 map에 저장
 * 2. map을 순회하며 pq에 저장
 * 3. pq가 빌때까지 순회하며 (number, count)를 꺼내서 arr에 저장 (할때마다 index를 키워서 순서대로 저장,,)
 * //C연산 메소드의 경우 xLegth를 사용해서 arr[i][key]를 진행하면 됨
 *
 * [주의할점]
 * 무조건 연산이 길어지는건 아님... [1,1,1,1]은 [1,4]가 되는것처럼
 * 1-base 행렬을 사용해서 헷갈리지 않게 계산하자
 *
 */
public class Main {
    static class Pair implements Comparable<Pair> {
        int number;
        int count;

        Pair(int n, int c) {
            this.count = c;
            this.number = n;
        }

        //count 오름차순, 같다면 number 오름차순
        public int compareTo(Pair o) {
            if(this.count == o.count) return this.number- o.number;
            else return this.count - o.count;
        }
    }

    static int r,c,k;
    static int[][] arr = new int[101][101];
    static int xLength=3, yLength=3;
    public static void main(String[] args) throws IOException {
        input();
        int answer = solution();
        System.out.println(answer);
    }

    private static int solution() {
        for(int time=0; time<=100; time++) {
            if(arr[r][c] == k) return time;
            operate();
        }
        return -1;
    }

    // 행>=열:R연산(행에대해 정렬), 행<열:C연산(열에대해 정렬)
    private static void operate() {
        if(xLength>=yLength) {
            for(int i=1; i<=xLength; i++) operateR(i);
        } else {
            for(int i=1; i<=yLength; i++) operateC(i);
        }
    }

    //R연산 : 각 숫자 갯수 구해서 해쉬맵에 담고 우선순위큐를 통해 정렬
    private static void operateR(int key) {
        PriorityQueue<Pair> pq = new PriorityQueue();
        Map<Integer, Integer> map = new HashMap<>(); //number, count

        for(int i=0; i<=yLength; i++) {
            if(arr[key][i] == 0) continue;
            map.compute(arr[key][i], (num,count) -> count==null ? 1: count+1);
        }

        map.forEach((k,v) -> pq.add(new Pair(k,v)));

        int i=1;
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            arr[key][i++] = p.number;
            arr[key][i++] = p.count;
        }

        yLength = Math.max(yLength, i);

        while (i <= 100)  arr[key][i++] = 0;
    }

    //R연산 : 각 숫자 갯수 구해서 해쉬맵에 담고 우선순위큐를 통해 정렬
    private static void operateC(int key) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>(); // <number, count>

        for (int i = 1; i <= xLength; i++) {
            if (arr[i][key] == 0) continue;
            map.compute(arr[i][key], (num, count) -> count == null ? 1 : count + 1);
        }

        map.forEach((k, v) -> pq.add(new Pair(k, v)));

        int i = 1;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            arr[i++][key] = p.number;
            arr[i++][key] = p.count;
        }

        xLength = Math.max(xLength, i);

        while (i <= 100)  arr[i++][key] = 0;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
    }
}
