import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/** 달팽이 숫자
 * 달팽이는 1부터 N*N까지의 숫자가 시계방향으로 이뤄져있다
 * 정수 N을 입력받아 N크기의 달팽이를 출력하시오
 *
 * 오른쪽, 아래, 왼쪽, 윗쪽 순서대로 탐색
 */
public class Solution {
	static int[][] arr;
	static boolean[][] check;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int num;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("src/sub/1954_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		for(int i=1; i<=tc; i++) {
			num = Integer.parseInt(br.readLine());
			arr = new int[num][num];
			check = new boolean[num][num];
			
			solution();
			System.out.println("#"+i);
			for(int r=0; r<arr.length; r++) {
				for(int c=0; c<arr[0].length; c++) {
					System.out.print(arr[r][c]+" ");
				}
				System.out.println();
			}
		}
	}
	
	
	//오른쪽으로 갈 수 있다 => 더 갈수없을때까지 반복
	//아래로 갈 수 있다 => 더 갈 수 없을때까지 반복
	//왼, 위로도 마찬가지로 반복됨
	private static void solution() {
		int cnt = 2;
		int x=0, y=0;
		arr[0][0]=1;
		check[0][0]=true;
		
		while(true) {
			if(cnt > num*num) return;
			while(x+dx[0]>=0 && x+dx[0]<arr.length && y+dy[0]>=0 && y+dy[0]<arr.length
					&& !check[x+dx[0]][y+dy[0]]) {
				x=x+dx[0];
				y=y+dy[0];
				check[x][y]=true;
				arr[x][y] = cnt;
				cnt++;
			}
			while(x+dx[1]>=0 && x+dx[1]<arr.length && y+dy[1]>=0 && y+dy[1]<arr.length
					&& !check[x+dx[1]][y+dy[1]]) {
				x=x+dx[1];
				y=y+dy[1];
				check[x][y]=true;
				arr[x][y] = cnt;
				cnt++;
			}
			while(x+dx[2]>=0 && x+dx[2]<arr.length && y+dy[2]>=0 && y+dy[2]<arr.length
					&& !check[x+dx[2]][y+dy[2]]) {
				x=x+dx[2];
				y=y+dy[2];
				check[x][y]=true;
				arr[x][y] = cnt;
				cnt++;
			}
			while(x+dx[3]>=0 && x+dx[3]<arr.length && y+dy[3]>=0 && y+dy[3]<arr.length
					&& !check[x+dx[3]][y+dy[3]]) {
				x=x+dx[3];
				y=y+dy[3];
				check[x][y]=true;
				arr[x][y] = cnt;
				cnt++;
			}
		}
		
	}
	
}