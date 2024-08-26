import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** N퀸
 * N*N인 체스판 위에 퀸 N개를 공격할수없게 놓는다.
 * 참고로 퀸은 상하좌우대각선 아무데나 가고픈만큼 갈수있음
 * (풀이)
 */
public class Main {
    static boolean[][] map;
    static int count, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        solution(0);
        System.out.println(count);
    }

    //N개의 퀸을 놓을때까지 반복한다
    private static void solution(int start){
        if(start == N) {
            count++;
            return;
        }

        //한 행당 한개의 말만 놓을 수 있다
        for(int i=0; i<N; i++){
            map[start][i] = true;
            if(valid(start, i)) solution(start+1); //가능한 배치이면 다음으로
            map[start][i] = false; //취소후 백트래킹
        }
    }

    //가로 세로 대각선중에 퀸이 이미 있는지 확인한다. 근데 가로는 어차피 한행당 한개라서 할필요 X
    private static boolean valid(int x, int y) {
        for(int i=0; i<N; i++){
            if(i==x) continue;
            if(map[i][y]) return false;
        }

        //x행까지 놓인 상태니까 그만큼만 확인
        for(int i=1; i<=x; i++){
            if(y+(i)>=0 && y+(i)<N && map[x-(i)][y+(i)]) return false;
            if(y-(i)>=0 && y-(i)<N && map[x-(i)][y-(i)]) return false;
        }

        return true;
    }
}