import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 배열돌리기 3
 * 크기가 NXM배열이 있을때, 배열에 연산을 R번 적용하려고 한다. 연산은 총 6가지가 있다.
 * 1번 : 상하반전
 * 2번 : 좌우반전
 * 3번 : 오른쪽으로 90도 회전
 * 4번 : 왼쪽으로 90도 회전
 * 5번 : 4분할 후 시계방향으로 각 분할을 한칸씩 돌림
 * 6번 : 4분할 후 반시계방향으로 각 분할을 한칸씩 돌림
 */
public class Main {
	static int[][] arr;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}			
		}

		st = new StringTokenizer(br.readLine()); //수행 연산
		while(st.hasMoreTokens()) {
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				updown();
				break;
			case 2:
				leftRight();
				break;
			case 3:
				right90();
				break;
			case 4:
				left90();
				break;
			case 5:
				square4up();
				break;
			case 6:
				square4down();
				break;
			}
		}
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) System.out.print(arr[i][j]+" ");
			System.out.println();
		}

	}
	
	//상하반전
	private static void updown() {
		N = arr.length;
		M = arr[0].length;
		int[][] tmp = new int[N][M];
		for(int i=N-1, x=0; i>=0; i--,x++) {
			for(int j=0, y=0; j<M; j++,y++) {
				tmp[x][y] = arr[i][j];
			}
		}
		arr = tmp;
	}
	//좌우반전
	private static void leftRight() {
		N = arr.length;
		M = arr[0].length;
		int[][] tmp = new int[N][M];
		for(int i=0, x=0; i<N; i++,x++) {
			for(int j=M-1,y=0; j>=0; j--,y++) {
				tmp[x][y] = arr[i][j];
			}
		}
		arr = tmp;
	}
	//오른쪽으로 90도
	private static void right90() {
		N = arr.length;
		M = arr[0].length;
		int[][] tmp = new int[M][N];
		for(int j=0, x=0; j<M; j++,x++) {
			for(int i=N-1,y=0; i>=0; i--,y++) {
				tmp[x][y] = arr[i][j];
			}
		}
		arr = tmp;
	}
	//왼쪽으로 90도 회전
	private static void left90() {
		N = arr.length;
		M = arr[0].length;
		int[][] tmp = new int[M][N];
		for(int j=M-1,x=0; j>=0; j--,x++) {
			for(int i=0, y=0; i<N; i++, y++) {
				tmp[x][y] = arr[i][j];
			}
		}
		arr = tmp;
	}
	//4분할 후 시계방향으로 각 분할을 한칸씩 돌림
	private static void square4up() {
		N = arr.length;
		M = arr[0].length;
		// ex. 0행을 읽고싶으면 arr의 0+N/2행을 읽는데, M/2-1 까지 읽는다
		// 그리고 0행의 M/2부터 M까지 읽어서 합쳐준다
		int[][] tmp = new int[N][M];
		for(int i=0, x=0; i<N/2; i++, x++) {
			int y=0;
			for(int j=0; j<M/2; j++) tmp[x][y++] = arr[N/2+i][j];
			for(int j=0; j<M/2; j++) tmp[x][y++] = arr[i][j];
		}
		for(int i=0, x=N/2; i<N/2; i++,x++) {
			int y=0;
			for(int j=M/2; j<M; j++) tmp[x][y++] = arr[N/2+i][j];
			for(int j=M/2; j<M; j++) tmp[x][y++] = arr[i][j];
		}
		arr = tmp;
	}
	//4분할 후 반시계방향으로 각 분할을 한칸씩 돌림
	private static void square4down() {
		N = arr.length;
		M = arr[0].length;
		int[][] tmp = new int[N][M];
		for(int i=0, x=0; i<N/2; i++, x++) {
			int y=0;
			for(int j=M/2; j<M; j++) tmp[x][y++] =arr[i][j];
			for(int j=M/2; j<M; j++) tmp[x][y++] =arr[N/2+i][j];
		}
		for(int i=0, x=N/2; i<N/2; i++, x++) {
			int y=0;
			for(int j=0; j<M/2; j++) tmp[x][y++] = arr[i][j];
			for(int j=0; j<M/2; j++) tmp[x][y++] = arr[N/2+i][j];
		}
		arr = tmp;
	}
}