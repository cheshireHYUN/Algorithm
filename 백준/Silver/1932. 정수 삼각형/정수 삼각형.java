import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 정수 삼각형
 * 맨 위층부터 대각선 왼쪽 또는 아래쪽의 숫자를 선택해 내려올때
 * 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성하라.
 *
 * 제한
 * 1<=n(삼각형의 높이)<=500
 * 각 수는 0이상 9999이하
 *
 * 풀이
 * 처음엔 위에서부터 최댓값을 확장하며 내려가려고 했다.
 * 근데 이러면 DP배열을 어떻게 만들지 모르겠는거임 가지가 생길때 각 값 사이사이에 끼워넣어야되는데??
 * => 그렇다면 발상의 전환! 아래서부터 최댓값을 확정해가며 올라가면 된다 ㅇㅇ
 * 그럼을 그려보면, 결국 DP[i][j] = Math.max(DP[i+1][j], DP[i+1][j+1])+COST 방식으로 채워진다.
 * 탑다운 형식으로 재귀탐색한다.
 */
public class Main {
    static int[][] arr;
    static int[][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][N];
        for(int[] d : dp) Arrays.fill(d, -1);
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<i+1; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        //마지막줄을 dp에도 저장
        for(int i=0; i<N; i++) dp[N-1][i] = arr[N-1][i];
        //가장꼭대기(0,0)에 최댓값이 저장될거임
        System.out.println(getMaxCost(0,0));
    }

    private static int getMaxCost(int depth, int index) {
        if(dp[depth][index] != -1) return dp[depth][index];
        else return dp[depth][index] = Math.max(
                getMaxCost(depth+1,index),getMaxCost(depth+1,index+1))
                +arr[depth][index];
    }
}