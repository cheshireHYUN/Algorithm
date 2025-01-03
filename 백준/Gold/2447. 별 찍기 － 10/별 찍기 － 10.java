import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 별찍기 -10
 * 재귀적인 패턴으로 별을 찍어보자.
 * N이 3의 거듭제곱이라고 할때 N의 패턴은 NxN 정사각형 모양이다.
 * 크기 3의 패턴은 가운데에 공백이 있고, 가운데 제외한 모든 칸에 별이 하나씩 있는 패턴이다.
 * N이 3보다 클 경우, 쿠기 N의 패턴은 공백으로 채워진 가운데의 N/3*N/3 정사각형을
 * 크기 N/3패턴으로 둘러싼 형태이다.
 *
 * 입력
 * N이 주어질때(N은 3의 거듭제곱이다) 어떤 정수 k에 대해 N=3^k이며 1<=k<8
 * 즉 최대 3^7까지 주어지는것이다...
 *
 * 풀이
 * N=27이라고 가정할때, 이차원 배열은 27*27로 만들어진다
 * 또한 9*9 형태의 정사각형이 9개 있는 형태인것이다.
 *
 */
public class Main {
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //27
        arr = new char[N][N];
        divide(0,0,N,false);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++) sb.append(arr[i][j]);
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void divide(int x, int y, int N, boolean blank) {
        //비워야 하는 칸일때
        if(blank) {
            for(int i=x; i<x+N; i++) {
                for(int j=y; j<y+N; j++) arr[i][j] = ' ';
            }
            return;
        }

        //더이상 쪼갤 수 없을때
        if(N==1) {
            arr[x][y] = '*';
            return;
        }

        //N=27일때 한블록은 9
        int size = N/3;
        int count = 0;
        for(int i=x; i<x+N; i+=size) {
            for(int j=y; j<y+N; j+=size) {
                count++;
                if(count == 5) divide(i,j,size,true);
                else divide(i,j,size,false);
            }
        }
    }
}