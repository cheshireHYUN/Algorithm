import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 거짓말
 * 사람의 수 N과 진실을 아는 사람이 주어진다.
 * 거짓말쟁이로 알려지지 않으면서 과장된 이야기를 할 수 있는 파티 개수의 최댓값?
 * 즉, 진실을 아는 사람이 있을때는 과장이 불가능함.
 * 또한 파티를 두번 참석하는 사람이 있을때 다르게 얘기하면 안됨.
 *
 * 제한
 * 1<= N,M(사람수, 파티수) <=50
 *
 * 풀이
 * 최대한 과장되어야 하므로, (1)진실아는사람 있는 파티는 전부 진실을 말하고,
 * (2) 진실을 말한 파티에 함께 있는 사람이 있는 파티도 전부 진실을 말해야함 ㅇㅇ
 *
 * 이걸 효율적으로 하려면, 우선 count = 파티갯수로 하고,
 * boolean배열 만들어서 people[1] = true라면 1번사람은 진실을 아는 사람이라고 둠
 * 예제 5의 경우, 1,2,3,4일떄 true일것임
 * 그리고 파티를 입력받는데
 * 1,5 -> people[1]=true니까 진실을 말함, 즉 count--, 그리고 people[5]=true가 됨
 * 2,6 -> people[2]=true니까 count--, people[1]이랑 people[5]=true
 * 근데 이렇게하면 people[10]=false였는데, 이후에 people[10]=true가 되어 오류가 생김..
 *
 * 결국 문제는, 진실을 아는 애들 파티에서 모르던애들이 깨닫는 연쇄작용에 대해 거짓말을 못하게해야돼.
 * 아 큐를 통해서 하면??
 * 1. 1,2,3,4가 참여한 파티 false로 바꿈
 * 2. 1,2,3,4가 참여한 파티에 같이 참여한 녀석들의 다른 파티를 false로 바꿈.
 *      (체크배열 만들어서 이미 취소한 녀석들은 다시 안들어가게)
 * 효율적으로 탐색하려면 내가 참여했던 파티들을 graph에 저장하는게 좋겠다.
 *
 * 답:3
 * 1,2,3,4가 진실
 * 1 5 X
 * 2 6 X
 * 7
 * 8
 * 7 8
 * 9 X
 * 10 9 X
 * 3 10 X
 * 4 X
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //사람수
        int M = Integer.parseInt(st.nextToken()); //파티수

        st = new StringTokenizer(br.readLine());
        int know = Integer.parseInt(st.nextToken()); //진실을 아는 사람 수
        Queue<Integer> q= new ArrayDeque<>();
        for(int i=0; i<know; i++) {
            q.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer>[] graph = new ArrayList[N+1];
        List<Integer>[] parties = new ArrayList[M];
        for(int i=0; i<N+1; i++) graph[i] = new ArrayList<>();
        for(int i=0; i<M; i++) parties[i] = new ArrayList<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            for(int j=0; j<p; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph[num].add(i);
                parties[i].add(num);
            }
        }

        boolean[] check = new boolean[N+1];
        boolean[] party = new boolean[M];
        int cnt = M;
        while(!q.isEmpty()) {
            int curr = q.poll();
            if(check[curr]) continue;

            check[curr] = true;
            //curr가 참여했던 파티들을 true로 변환, 그리고 같은 파티에 참여한 다른 녀석들을 큐에 넣기
            for(int partyNum : graph[curr]) {
                if(!party[partyNum]) {
                    party[partyNum] = true;
                    cnt--;
                    for(int i : parties[partyNum]) q.add(i);
                }
            }
        }

        System.out.println(cnt);
        //System.out.println(Arrays.toString(party));
    }
}