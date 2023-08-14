import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/** 쿼드트리
 */
public class Main {
	static int[][] map;
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		
		makeSpace(0,0,N);
		System.out.println(sb);
	}
	private static void makeSpace(int sr, int sc, int size) { //영역의 좌상단의 좌표(r,c)와 영역 크기
		if(sum(sr,sc,size) == 0) {
			//현재 영역이 모두 0임
			sb.append(0);
		}else if(sum(sr,sc,size) == size*size) {
			//현재 영역이 모두 1임
			sb.append(1);
		}else {
			//현재영역이 모두 같지 않으므로 분할한다
			int half = size/2;
			//괄호가 생기는 시점 = 분할할때!
			sb.append("(");
			//사분할하므로 시작점기준 sr, sr, sr+half, sr+half의 순서를 지켜야한다. (왼쪽부터 오른쪽으로 읽고 아래로 내려감)
			makeSpace(sr, sc, half);
			makeSpace(sr, sc+half, half);
			makeSpace(sr+half, sc, half);
			makeSpace(sr+half, sc+half, half);
			sb.append(")");
		}
		
	}
	
	//영역 내부의 내용이 같은지 검사한다. 다르면 false를 리턴
	private static int sum(int sr, int sc, int size) {
		int sum = 0;
		for(int i=sr; i<sr+size; i++) {
			for(int j=sc; j<sc+size; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
}