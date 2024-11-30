import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 주사위 굴리기
 * NxM인 지도가 존재하고, 주사위가 하나 놓여져있다. r행 c열
 * 주사위는 지도 위에 윗면이 1이고 동쪽을 바라보는 방향이 3인 상태로 (x,y)에 놓여져있다.
 * 처음에 주사위는 모든면에 0이 적혀있다.
 *
 * 지도의 각 칸에는 정수가 쓰여져 있으며, 주사위를 굴렸을때 이동한 칸에 쓰여있는 수가 0이면
 * 주사위의 바닥면에 쓰여있는 수가 칸에 복사된다. 0이 아닌 경우 칸에 쓰인 수가 주사위 바닥면
 * 으로 복사되며, 칸에 쓰인 수는 0이 된다.
 *
 * 주사위를 놓은 곳의 좌표와 이동시키는 명령이 주어졌을때 주사위가 이동했을때마다
 * 상단에 쓰인 값을 구하는 프로그램을 작성하시오. (주사위는 지도밖으로 갈 수 없다)
 *
 * 출력
 * 이동할때마다 주사위 윗면에 쓰인 수를 출력한다. 바깥으로 이동시키려고 하는 경우
 * 해당 명령을 무시해야 하며, 출력도 하면 안된다.
 *
 * 풀이
 * 정의마다 방향벡터(?)를 만들어둔 후
 * 각 방향마다 
 * (1)숫자 쉐이킹 
 * (2)지도칸의 숫자가 0이면 주사위 바닥면(마지막항)을 지도에 복사, 지도칸의 숫자가 0이 아니면 주사위 바닥면에 복사되고 지도칸은 0이됨
 *
 * 지도 밖으로 넘어서는 명령이 있음에 주의한다.
 *
 * 참고로 숫자쉐이킹은 그냥 새로운 배열 만들고 각자 매핑해주면 될듯.
 * int[] newArr = new int[]
 * int[] dx = {5,2,3,1,6,4}
 * for(int i=0; i<6; i++) newArr[i] = originArr[dx[i]]
 */
public class Main {
    //오른쪽(1), 왼쪽(2), 위(3), 아래(4)
    static int[][] diceDx = {{},{2,0,5,3,4,1},{1,5,0,3,4,2},{3,1,2,5,0,4},{4,1,2,0,5,3}};
    static int[][] mapDx = {{},{0,1},{0,-1},{-1,0},{1,0}};
    static int[][] map;
    static int[] commands, location, dice= new int[6];
    static int N,M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        init();

        for(int command : commands) {
            rollDice(command);
        }
        System.out.println(sb);
    }

    private static void rollDice(int directionIndex) {
        //해당 자리가 지도 위에 존재 하는지 체크
        int[] newLocation = new int[]{location[0]+mapDx[directionIndex][0],
                                     location[1]+mapDx[directionIndex][1]};
        if(!valid(newLocation)) return;

        //주사위 이동
        location = newLocation;

        //숫자 쉐이킹
        int[] newDice = new int[6];
        for(int i=0; i<6; i++) newDice[i] = dice[diceDx[directionIndex][i]];

        //지도칸의 숫자가 0이면 주사위 바닥면(마지막항)을 지도에 복사, 지도칸의 숫자가 0이 아니면 주사위 바닥면에 복사되고 지도칸은 0이됨
        if(map[location[0]][location[1]] == 0) {
            map[location[0]][location[1]] = newDice[5];
        } else {
            newDice[5] = map[location[0]][location[1]];
            map[location[0]][location[1]] = 0;
        }
        dice = newDice;

        sb.append(newDice[0]).append('\n');
    }

    private static boolean valid(int[] location) {
        return location[0] >= 0 && location[1] >= 0 && location[0] < N && location[1] < M;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        location = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        commands = new int[Integer.parseInt(st.nextToken())];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i< commands.length; i++) commands[i] = Integer.parseInt(st.nextToken());
    }
}