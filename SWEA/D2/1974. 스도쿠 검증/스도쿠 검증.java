import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 스토쿠검증
 * 행, 열, 각 3*3단위배열이 조건을 만족하는지 검사한다.
 * 음.. boolean배열 쓰면 될듯
 */
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int map[][] = new int[9][9];
	static boolean check[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("src/algo0816/input (7).txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			for(int i=0; i<9; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<9; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			sb.append('#').append(t).append(' ');
			if(!checkRow() || !checkCol() || !checkSquare()) sb.append(0);
			else sb.append(1);
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	private static boolean checkSquare() {
		int start = 0;
		while(true) {
			// 9번 반복해야함 (0,0),(0,3),(0,6) / (3,0),(3,3),(3,6) / (6,0),(6,3),(6,6)
			check = new boolean[10];
			for(int x=start; x<start+3; x++) {
				for(int y=0; y<3; y++) {
					if(check[map[x][y]]) return false;
					else check[map[x][y]] = true;
				}
			}
			
			check = new boolean[10];
			for(int x=start; x<start+3; x++) {
				for(int y=3; y<6; y++) {
					if(check[map[x][y]]) return false;
					else check[map[x][y]] = true;
				}
			}
			
			check = new boolean[10];
			for(int x=start; x<start+3; x++) {
				for(int y=6; y<9; y++) {
					if(check[map[x][y]]) return false;
					else check[map[x][y]] = true;
				}
			}

			start+=3;
			if(start > 6) break;
		}
		return true;
	}
	
	private static boolean checkCol() {
		for(int i=0; i<9; i++) {
			check = new boolean[10];
			for(int j=0; j<9; j++) {
				if(check[map[j][i]]) return false;
				else check[map[j][i]] = true;
			}
		}
		return true;
	}
	
	private static boolean checkRow() {
		for(int i=0; i<9; i++) {
			check = new boolean[10];
			for(int num : map[i]) {
				if(check[num]) return false;
				else check[num] = true;
			}
		}
		return true;
	}
}