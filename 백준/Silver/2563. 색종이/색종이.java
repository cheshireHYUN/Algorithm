import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 색종이
 * 가로, 세로 크기가 100인 정사각형 도화지에 10x10인 색종이를 붙인다.
 * 겹칠수도 있을때, 색종이 영역의 넓이?
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] arr = new boolean[100][100];
		int cnt = Integer.parseInt(br.readLine()); //색종이의 수
		int answer = 0;
		StringTokenizer st;
		for(int i=0; i<cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int j=x; j<x+10; j++) {
				for(int k=y; k<y+10; k++) {
					if(!arr[j][k]) {
						arr[j][k] = true;
						answer++;
					}
				}
			}
		}
		System.out.println(answer);
	}
}