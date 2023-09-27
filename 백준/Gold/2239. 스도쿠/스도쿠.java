import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 2239.스도쿠
 * 
 * 0을 채워라
 * 
 * 풀이 : 재귀를 타고타고타고...
 * 그러면 81! 나오니까(13! 넘어가면 안된대) 백트래킹으로 효율성을 챙겨주자
 * 
 * 즉 0인곳에 숫자를 채우고, 행과열을 체크하여 가능한 숫자일때 전개해나가면된다.
 *
 */
public class Main {
	static int[][] map = new int[9][9];
	static int blank = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<9; i++) {
			String[] line = br.readLine().split("");
			for(int j=0; j<9; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				if(map[i][j]==0) blank++;
			}
		}
		
		solution(0,0);
	}
	
	private static void solution(int cnt, int startX) {
		// 기저조건 : 0의갯수만큼 0을 채우면 멈춘다.
		if(cnt == blank) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		for(int i=startX; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(map[i][j] == 0) {
					// (1) 값이0인 map[i][j]를 찾아서 값 k를 하나씩 대입해본다.
					for(int k=1; k<=9; k++) {
						//(2)헹,열,그리드검사를 통과했을때 재귀로 전개해나간다.
						if(validate(i,j,k)) {
							map[i][j] = k; //k값을 확정시킨뒤
							solution(cnt+1, i);//재귀 실행
							
							//잘못된 경로(재귀중지)일때 이영역이 실행됨
							//즉 넣어뒀던 값을 초기화하고 계속해서 for문을 진행한다.
							//초기화해줘야 이 경로또한 정답이 없을때 아래 if문에서 return이 가능해짐!
							map[i][j] = 0;
						}
					}

					//만약 재귀를 실행하다 더이상 넣을수있는 값이 없는경우, 
					//즉 기저조건에 도달하지 못하고 재귀가 멈춘경우(잘못된 경로)엔 더이상 탐색하지않고 리턴해준다
					if(map[i][j]==0) return;
				}
			}			
		}
	}
	
	//유효성 검사하기
	private static boolean validate(int x, int y, int val) {
		//행 검사
		for(int i=0; i<9; i++) if(map[x][i]==val) return false;
		
		//열 검사
		for(int i=0; i<9; i++) if(map[i][y]==val) return false;
		
		//그리드 검사 : 나눗셈을 이용한다. 각 x와 y를 3으로 나눈 몫에 3을 곱하면 자기가 속한 그리드의 시작점값이 나옴..
		for(int k1=(x/3)*3, i=0; i<3; i++) {
			for(int k2=(y/3)*3, j=0; j<3; j++) {
				if(map[k1+i][k2+j]==val) return false;
			}
		}
		//세 검사를 통과한다면.. 
		return true;
	}
	
	
}