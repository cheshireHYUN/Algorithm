import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 배열돌리기4
 * N*M인 배열 A
 * 
 * 배열값 = 각 행의 모든수의 합중 최솟값
 * 
 * 회전 = (r,c,s)주어지며 가장왼위가 (r-s,c-s), 오른아래가(r+s,c+s)이며 이를 시계방향으로 한칸씩 돌린다.
 * 이때 회전연산이 두개 주어지면 순서를 임의로 정하여 돌려보고, 그중 배열값이 최솟값인것을 구한다.
 *
 * 메소드 1. 배열을 시계방향으로 한칸씩 돌리는 메소드
 * 메소드 2. 배열값을 구해주는 메소드
 * 메소드 3. RCS의 순서를 정할 순열을 구해주는 메소드
 */
public class Main {
	static int N,M,K,map[][], answer=Integer.MAX_VALUE; //배열 크기, 회전연산갯수, 배열
	static int[] pm;
	static boolean[] check;
	static class Rcs{
		int r,c,s;
		int[] start,end;
		public Rcs(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
			getStart();
			getEnd();
		}
		
		public void getStart() {
			int[] result = new int[2];
			result[0] = this.r-this.s;
			result[1] = this.c-this.s;
			this.start =  result;
		}
		public void getEnd() {
			int[] result = new int[2];
			result[0] = this.r+this.s;
			result[1] = this.c+this.s;
			this.end = result;
		}
	}
	static Rcs[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		arr = new Rcs[K];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Rcs(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		pm = new int[arr.length];
		check = new boolean[arr.length];
		getPermutation(0); //arr의 length의 순열을 구하면 됨
		System.out.println(answer);
	}
	

	//RCS 순열을 구하는 메소드
	static int[][] tmpMap;
	private static void getPermutation(int cnt) {
		if(cnt == arr.length) {
			cloneMap();
			//System.out.println(Arrays.toString(pm));
			//이 순열은 rcs순열인 arr의 인덱스 순열이다. 즉 순열값인 인덱스 순서대로 rcs를 선택하여 90도 회전하자
			for(int i=0; i<arr.length; i++) turn(arr[pm[i]]);
			answer = Math.min(answer,getNum());
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(check[i]) continue;
			check[i] = true;
			pm[cnt] = i;
			getPermutation(cnt+1);
			check[i] = false;
		}
	}
	
	private static void cloneMap(){
		tmpMap = new int[map.length][map[0].length];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
	}
	
	//rcs의 start와 end를 기준으로 배열을 잘라서 바꾼후 다시 집어넣는다
	private static void turn(Rcs rcs) {
		int startX = rcs.start[0], startY = rcs.start[1];
		int endX = rcs.end[0], endY = rcs.end[1];
		
		while(true) {
			if(startX==endX && startY==endY) break;
			int tmp = tmpMap[startX][endY];
			//상부 값 바꿔주기
			for(int y=endY; y>startY; y--) tmpMap[startX][y] = tmpMap[startX][y-1];
			//왼쪽 값 바꿔주기- 상부 빈자리 채워줌
			for(int x=startX; x<endX; x++) tmpMap[x][startY] = tmpMap[x+1][startY];
			//하부 값 바꿔주기 - 왼쪽 빈자리 채워줌
			for(int y=startY; y<endY; y++) tmpMap[endX][y] = tmpMap[endX][y+1];
			//오른쪽 값 바꿔주기 - 하부 빈자리 채워줌
			for(int x=endX; x>startX; x--) tmpMap[x][endY] = tmpMap[x-1][endY];
			tmpMap[startX+1][endY] = tmp;
			
			startX++;
			startY++;
			endX--;
			endY--;
		}


	}

	//배열값 구하는 메소드 : 각행의 합중에서 최솟값을 리턴
	private static int getNum() {
		int result = Integer.MAX_VALUE;
		for(int i=1; i<tmpMap.length; i++) {
			int sum = 0;
			for(int j=1; j<tmpMap[0].length; j++) {
				sum += tmpMap[i][j];
			}
			result = Math.min(result, sum);
		}
		return result;
	}
	
	
}