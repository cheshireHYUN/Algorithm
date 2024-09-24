import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** Z
 * 크기가 2^N X 2^N인 2차원 배열을 Z모양으로 탐색한다.
 * N>1인경우 4등분 후 재귀적으로 방문한다.
 * r행 c열을 몇번째로 방문했는지 출력한다.
 *
 * 풀이 : 분할정복을 사용해서 풀이한다.
 * 사분면으로 분할해서 생각하면 됨, Size가 1이될때까지 Size/2하며 재귀돈다,
 * 타겟인 x,y가 상대적으로 어떤 사분면에 있는지 Size를 통해서 찾는다.
 * x<newSize, y<newSize라면 1번
 * x<newSize, y>=newSize라면 2번
 * x>=newSize, y<newSize라면 3번
 * x>=newSize, y>=newSize라면 4번
 * 주의할점은 각 위치에서 다음 재귀를 돌릴때, 상대좌표로 돌아야한다는거다.
 * 즉, 2,3,4번도 다음재귀에선 시작점이 0,0이어야하니까
 * 1번 => find(x,y,newSize);
 * 2번 => find(x,y-newSize,newSize);
 * 3번 => find(x-newSize,y,newSize);
 * 4번 => find(x-newSize,y-newSize,newSize);
 * 또한 2,3,4번의 경우 이전번호를 지나쳐왔으므로 count값을 그 갯수만큼 추가해준다. count += (size*size/4)+앞에거 갯수
 */
public class Main {
    static int N,r,c, count=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        solution(r,c,(int)Math.pow(2,N));
    }

    private static void solution(int x, int y, int size) {
        if(size == 1){
            System.out.println(count);
            return;
        }

        int newSize = size/2;
        if(x<newSize && y<newSize) solution(x,y,newSize);
        else if(x<newSize && y>=newSize) {
            count += (size*size/4*1);
            solution(x,y-newSize,newSize);
        } else if(x>=newSize && y<newSize) {
            count += (size*size/4*2);
            solution(x-newSize,y,newSize);
        } else if(x>=newSize && y>=newSize) {
            count += (size*size/4*3);
            solution(x-newSize,y-newSize,newSize);
        }
    }
}