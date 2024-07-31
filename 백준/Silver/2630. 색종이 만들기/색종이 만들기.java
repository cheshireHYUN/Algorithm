import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 색종이 만들기
 * 한 색으로 꽉찰때까지 종이를 사분할 한다.
 * 딱봐도 분할정복
 */
public class Main {
    static int arr[][], white=0, blue=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solution(0,0,N);
        System.out.println(white+"\n"+blue);
    }

    private static void solution(int x, int y, int n) {
        int sum = 0;
        for(int i=x; i<x+n; i++) {
            for(int j=y; j<y+n; j++) sum += arr[i][j];
        }

        //모두 흰색
        if(sum == 0) white++;
        //모두 파란색
        else if(sum == n*n) blue++;
        else{
            int half = n/2;
            solution(x,y,half);
            solution(x+half,y,half);
            solution(x,y+half,half);
            solution(x+half,y+half,half);
        }
    }

}