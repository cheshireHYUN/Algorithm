
import java.util.Scanner;

/* # 1018. 체스판 다시 칠하기
 * MN개의 정사각형으로 이뤄진 MxN크기 보드
 * 어떤건 검정 어떤건 흰색임 8x8크기의 체스판으로 바꾸려고 한다
 * 
 * 색은 번갈아서 있어야함 즉 각 칸이 검흰중 하나이고 변을 공유하는 두 사각형은 다른색을 갖고있음
 * 따라서 체스판을 색칠하는 경우의수는 단 두가지 : 맨위왼쪽이 검/흰
 * 보드가 체스판처럼 되어있는 보장이 없어서 8x8로 잘라낸 후 몇몇개 정사각형을 다시 칠할것임
 * 지민이가 다시 칠해야하는 정사각형의 최소개수를 구하는 프로그램을 작성하라
 * 
 * 입력 : N,M이 첫째줄에 주어진다. 8보다 크거나 같고 50보다 작거나 같다.
 * 둘쨰줄부터 N개의 줄에는 보드의 각 행 상태가 주어진다. B는 검정 W는 흰색이다.
 * */

public class Main {
	public static boolean [][] board;
	public static int min = 64;
	
	public static void main(String[] args) {
		//1.체스판 입력
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		board = new boolean [n][m];
		//2. 색 입력 - WB 그대로 넣으면 나중에 반복문 돌릴때 W일때, B일때 설정해주기 귀찮으니 boolean으로
		for(int i=0; i<n; i++) {
			String str = sc.nextLine();
			for(int j=0; j<m; j++) {
				if(str.charAt(j) == 'W') {
					board[i][j] = true;
				}else {
					board[i][j] = false;					
				}
			}
		}
		
		//3. 8x8로 자르기 - 완전탐색
		for(int i=0; i<=n-8; i++) {
			for(int j=0; j<=m-8; j++) {
				search(i,j);
			}
		}
		
		System.out.println(min);
		
	}

	private static void search(int x, int y) {
		int cnt = 0;
		boolean TF = board[x][y];
		
		for(int i=x; i<x+8; i++) {
			for(int j=y; j<y+8; j++) {
				
				if(board[i][j] != TF) {
					cnt++;
				}
				
				TF = !TF;
			}
			TF = !TF;
		}
		cnt = Math.min(cnt, 64-cnt);
		min = Math.min(min, cnt);
	}
}
