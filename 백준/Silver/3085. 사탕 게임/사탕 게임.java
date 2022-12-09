
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* # 3085. 사탕게임
 * 처음에 NxN 크기 사탕을 채워놓는다. 색은 모두 같지않을수도 O
 * 사탕의 색이 다른 인접한 두칸을 고른다
 * 그다음 고른 칸에 들어있는 사탕을 서로 교환한다.
 * 이제 모두 같은색으로 이뤄진 가장 긴 연속부분(행/열)을 고른 후 모두 먹는다
 * 사탕이 채워져있을때 먹을수있는 사탕의 최대개수?
 * */

public class Main {
	
	public static int max=0;
	public static char [][] arr;
	public static int n;
		
	public static void main(String[] args) throws IOException {
		
		// N입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		// n x n 크기의 행렬 만들기
		arr = new char[n][n];
		// 색깔 입력받아서 이차원행렬에 넣기
		for(int i=0; i<n; i++) {
			arr[i] = (br.readLine()).toCharArray();
		}
		
		// 인접한 두칸 고르기 - 완전탐색
		// 가로로 교환하기, 마지막칸은 더이상 교환할게 없음
		for(int i=0; i<n; i++) {
			// i는 행, j는 열
			for(int j=0; j<n-1; j++) {
				char temp = arr[i][j];
				arr[i][j] = arr[i][j+1];
				arr[i][j+1] = temp;
				// 가장 긴 연속 찾기
				search();
				// 원상복귀
				temp = arr[i][j];
				arr[i][j] = arr[i][j+1];
				arr[i][j+1] = temp;
			}
		}
		// 세로로 교환하기, 마지막칸은 더이상 교환할게 없음
		for(int i=0; i<n; i++) {
			// i는 행, j는 열
			for(int j=0; j<n-1; j++) {
				char temp = arr[j][i];
				arr[j][i] = arr[j+1][i];
				arr[j+1][i] = temp;
				// 가장 긴 연속 찾기
				search();
				// 원상복귀
				temp = arr[j][i];
				arr[j][i] = arr[j+1][i];
				arr[j+1][i] = temp;
			}
		}
		
		System.out.println(max);
		
	}

	// 가장 긴 연속을 찾는 메소드
	private static void search() {
		
		// 행에서 가장 긴 연속 찾기
		for(int i=0; i<n; i++) {
			int cnt=1;
			for(int j=0; j<n-1; j++) {
				if(arr[i][j] == arr[i][j+1]) {
					cnt++;
				} else {
					cnt = 1;
				}
				max = Math.max(max, cnt);
			}
		}
		
		// 열에서 가장 긴 연속 찾기
		for(int i=0; i<n; i++) {
			int cnt=1;
			for(int j=0; j<n-1; j++) {
				if(arr[j][i] == arr[j+1][i]) {
					cnt++;
				} else {
					cnt = 1;
				}
				max = Math.max(max, cnt);
			}
		}
		
	}
		


}
