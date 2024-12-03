import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/** 톱니바퀴
 * 8개의 톱니를 가진 톱니 4개가 있다. 각 톱니는 N또는 S를 나타내고 있다.
 * 또한 각 톱니바바퀴엔 번호가 매겨져 있는데, 가장 왼쪽 톱니가 1번, 오른쪽은 2번, 그 오른쪽은 3번, 가장 오른쪽은 4번
 * 이때 톱니바퀴를 K번 회전시킨다. 시계반향/시계반대방향으로 할 수 있다.
 * 톱니바퀴를 돌리면 양옆의 톱니바퀴가 영향을 받는다.
 * 맞물리는곳의 톱니의 극이 <다르면>, 원인톱니의 반대방향으로 영향톱니들이 회전한다.
 *
 *초기상태와 회전방법이 주어질때 최종 톱니바퀴의 상태를 구하는 프로그램을 작성하라.
 * 마지막 상태에서 톱니바퀴 점수의 합을 출력하면 됨!
 *
 * 풀이
 * 서로 영향을 퍼트리는건 BFS처럼,, 큐룰 사용해서 돌리면 될듯함
 * 톱니는 배열로 구현하고, 0번과 4번 인덱스를 맞물리는 극이라고 보면 됨.
 *
 * (1)길이가 8인 배열을 만든 뒤, 시계방향과 반시계방향으로 회전(배열 밀기)하는 메소드를 각각 구현
 * (2)돌리는 반복문을 진행하며 큐에 (맞물리는 극이 다를때) 넣으며 전이를 진행
 * (3)최종에서 점수를 계산
 */
public class Main {
    static int[][] wheels = new int[5][8];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=1; i<5; i++) {
            String str = br.readLine();
            for(int j=0; j<8; j++) {
                wheels[i][j] = str.charAt(j) - '0';
            }
        }
        int k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int wheelNum = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            simulation(wheelNum, direction);
        }

        //결과를 계산
        System.out.println(calculate());
    }

    private static int calculate(){
        int sum = 0;
        sum += wheels[1][0] == 0 ? 0 : 1;
        sum += wheels[2][0] == 0 ? 0 : 2;
        sum += wheels[3][0] == 0 ? 0 : 4;
        sum += wheels[4][0] == 0 ? 0 : 8;
        return sum;
    }

    //n극은 0, s극은 1
    private static void simulation(int wheelNum, int direction) {
        boolean[] check = new boolean[5];
        int[] start = {wheelNum, direction};
        Queue<int[]> q = new ArrayDeque<>();
        q.add(start);

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            check[curr[0]] = true;
            
            //맞물리는 극(2번 인덱스, 6번인덱스)이 서로 다른 이웃들을 시뮬레이션에 미리 추가
            if(curr[0]+1<5 && !check[curr[0]+1] && (wheels[curr[0]][2] != wheels[curr[0]+1][6])) {
                q.add(new int[]{curr[0]+1, curr[1]==1? -1 : 1});
            }
            if(curr[0]-1>0 && !check[curr[0]-1] && (wheels[curr[0]][6] != wheels[curr[0]-1][2])) {
                q.add(new int[]{curr[0]-1, curr[1]==1? -1 : 1});
            }


            //회전처리
            if(curr[1] == 1) turnClock(curr[0]); //시계방향 회전
            else turnDeClock(curr[0]); //반시계방향 회전
        }
    }

    //시계방향으로 회전(오른쪽으로 한칸씩 이동)
    private static void turnClock(int wheelNum){
        int[] newWheel = new int[8];
        for(int i=0; i<8; i++) {
            if(i==7) newWheel[0] = wheels[wheelNum][i];
            else newWheel[i+1] = wheels[wheelNum][i];
        }
        wheels[wheelNum] = newWheel;
    }

    //반시계방향으로 회전(왼쪽으로 한칸씩 이동)
    private static void turnDeClock(int wheelNum){
        int[] newWheel = new int[8];
        for(int i=0; i<8; i++) {
            if(i==0) newWheel[7] = wheels[wheelNum][i];
            else newWheel[i-1] = wheels[wheelNum][i];
        }
        wheels[wheelNum] = newWheel;
    }
}