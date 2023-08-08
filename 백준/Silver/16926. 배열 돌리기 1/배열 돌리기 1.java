import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/* 배열돌리기1
 * 크기가 NXM인 배열을 반시계 방향으로 돌린다.
 * 배열과 정수 R이 주어질때 R번 회전시킨값을 구하자.
 */
public class Main {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken()); //회전할 횟수
        
        arr = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //몇개의 사각형이 나오냐면 행,열중 작은거의 /2!! 왜냐면 한 턴 만들때마다 두칸씩 줄어들거든요...
        int cnt = Math.min(N, M)/2;
        for(int turn=0; turn<R; turn++) {
            for(int index=0; index<cnt; index++) { //각 사각형을 N만큼 반복회전, 즉 j는 사각형의 인덱스역할
                //사이드에 있는걸 하나씩 밀면(순서중요), 결국 맨처음 하나가 없어지므로 미리 저장해둔다
            	// 순서 :  윗변 밀고, 윗변의 불변부분은 오른쪽높이를 밀어 채우고, 오른쪽높이의 불변부분은 아랫변을 밀어 채우고, 아랫변의 왼쪽높이를 밀어 채우고,
            	//       마지막으로 왼쪽높이의 불변부분은 tmp에 저장했다가 채워준다.
                int tmp = arr[index][index];
                
                // 1. 윗변
                for(int y=index, x=index; y<M-index-1; y++) arr[x][y] = arr[x][y+1];
                
                // 2. 오른쪽높이
                for(int x=index, y=M-index-1; x<N-1-index; x++) arr[x][y] = arr[x+1][y];    
                
                // 3.아랫변
                for(int y=M-index-1, x=N-index-1; y>=index+1; y--) arr[x][y] = arr[x][y-1];
                
                // 4. 왼쪽높이
                for(int x=N-index-1, y=index; x>=index+1; x--) arr[x][y] = arr[x-1][y];
                arr[index+1][index] = tmp;
            }
        }
        
        for(int[] i : arr) {
        	for(int j : i) System.out.print(j+" ");
        	System.out.println();
        }
        
    }

}