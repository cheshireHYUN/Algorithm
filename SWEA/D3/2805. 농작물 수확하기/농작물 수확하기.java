
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * NXN크기의 농장이 있고 다음 규칙이 있다
 * 1. 농장의 크기는 항상 홀수이다. (49X49)
 * 2. 수확은 항상 농장의 크기에 딱 맞는 정사각형 마름모 형태로만 가능하다.
 * 농장이 주어질때 규칙에 따라 얻을 수 있는 수익을 구해라.
 * @author SSAFY
 *
 */
public class Solution {
	static int[][] map;
	static int N, result, middleIdx;
	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("src/sub/2805_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			middleIdx = N/2;//가운데의 인덱스값
			
			map = new int[N][N];
			for(int j=0; j<N; j++) {
				String line = br.readLine();
				for(int k=0; k<N; k++) {
					map[j][k] = line.charAt(k)-'0';
				}
			}
			
			//System.out.println(Arrays.deepToString(map));
			solution(0);
			
			System.out.println("#"+i+" "+result);
			
		}
	}

	private static void solution(int row) { //행별로 탐색, 반복횟수와 같음
		if(row == N) return;
		
		result += map[row][middleIdx];
		
		if(row <= N/2) {
			for(int i=1; i<=row; i++) {
				result += map[row][middleIdx+(i)];
				result += map[row][middleIdx-(i)];
			}
		} else {
			for(int i=N-row-1; i>=1; i--) {
				result += map[row][middleIdx+(i)];
				result += map[row][middleIdx-(i)];
			}
		}
		solution(row+1);
		
	}

}
