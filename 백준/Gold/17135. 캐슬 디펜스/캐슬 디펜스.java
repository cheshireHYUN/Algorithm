import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 캐슬디펜스
 * 
 * 성을향해 몰려오는 적을 잡자! NXM격자판에서 각 칸에 최대 하나의 적이있다.
 * N번행의 바로 아래 (즉 N+1)번행의 모든칸엔 성이 있다
 * 
 * 성을 적에게서 지키기 위해 궁수 3명을 배치한다. 성의 모든 칸에 배치할수있으며 한칸당 최대 한명이다.
 * 각 턴마다 궁수는 적 하나를 공격하며, 모든 궁수는 동시에 공격한다.
 * 궁수가 공격하는 적은 거리가 D 이하인 적중 가장 가까운 적이다. (거리:|행 차|+|열차|)
 * 그런 적이 여러명이면 가장 왼쪽적을 공격한다. 같은적이 여러 궁수에게 공격당할 수 있다.
 * 공격이 끝나면 적이 이동한다. 적은 아래로 한칸 이동하며, 성이 있는 칸으로 이동한 경우 게임에서 제외된다.
 * 
 * 궁수를 배치한 이후의 게임은 정해져있다. 따라서 궁수위치가 중요하다. 궁수공격으로 제거할수있는 최대 적의 수?
 * 
 * 입력 : N*M상태와 0:빈칸 1:적
 * 
 * 풀이방법 고민!!
 * 
 * 아이디어 1) 궁수를 놓는 모든 조합을 찾아서.. 각 조합마다 가까운 적을 죽이도록 DFS해서 시뮬레이션..?
 * 조합찾는다 -> 한 조합에 대해 메소드를 돌린다
 * 어떤 메소드냐면 -> 각 궁수의 위치에서 DFS를 통해 가장 가까운 적을 찾는다. 죽인다. (여기까지 세명의 궁수행동 시행)
 * 그리고 적위치 업데이트 후 다시 DFS해서 죽인다.
 * 이러다가 더이상 가까운 적을 찾을 수 없으면 정지...
 *
 * 다음 조합에서 다시 반복한다.
 * 
 * 전역변수로 최대 킬수 업데이트 하면 되지 않을까.. 싶네요
 * 
 */
public class Main {
	// 행 N, 열 M, 공격거리제한 D, 각 궁수배치마다 사용할 map, 원본모양을 저장할 originMap, 궁수배치 조합을 저장할 comb, 가장 많은킬수 저장하는 maxKill 
	static int N, M, D, map[][], originMap[][], comb[] = new int[3], maxKill=0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		originMap = new int[N+1][M]; //N행에 성이있으므로 DFS하기위해 N+1로 만들어준다
		map = new int[N+1][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				originMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		getComb(0,0);
		System.out.println(maxKill);
	}
	
	//궁수를 배치하는 인덱스의 모든 조합 구하는 메소드
	private static void getComb(int index, int cnt) {
		if(cnt == 3) {
			//comb가 완성되면 게임을 실행한 후, 결과값인 tmpKill중 최댓값을 저장
			int tmpKill = gameStart(comb);
			maxKill = Math.max(tmpKill, maxKill);
			return;
		}
		for(int i=index; i<M; i++) {
			comb[cnt] = i;
			getComb(i+1, cnt+1);
		}
	}


	/** 3명의 궁수의 열값 조합이 주어지면 게임을 시작하는 메소드
	 * @param comb2 (즉 [N,comb[i]]위치에 궁수가 존재)
	 * @return 각 스테이지의 누적킬수
	 */
	private static int gameStart(int[] comb2) {
		initializeMap(); //사용할 map을 초기화
		int sumKill=0; //각 배치마다 킬수를 초기화
		
		while(!isMapEmpty()) { //판위에 더이상 타겟이 없으면 종료한다.
			int[][] targets = targeting(comb2); //궁수의 목표물 타겟팅한다. 즉 목표물의 위치좌표를 리턴
			sumKill += killTarget(targets); //한 스테이지마다 얻은 킬수를 누적	
			updateStage(); //목표물을 한칸씩 앞으로 당겨오고 반복
		}

		return sumKill;
	}

	/** 사용할 map을 originMap으로 초기화해주는 메소드
	 */
	private static void initializeMap() {
		for(int i=0; i<originMap.length; i++) {
			map[i] = originMap[i].clone();
		}
	}
	

	/** 맵에 타겟이 더 있는지 확인하는 메소드
	 * @return 타겟이 있다면 false
	 */
	private static boolean isMapEmpty() {
		for(int i=0; i<map.length-1; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j]==1) return false;
			}
		}
		return true;
	}

	/** 다음스테이지로 업데이트하는 메소드
	 */
	private static void updateStage() {
		//당겨오게되면 map의 0행은 tmp의 1행이 된다.
		int[][] tmp = new int[map.length][map[0].length];
		for(int i=1; i<map.length; i++) tmp[i] = map[i-1];
		map = tmp;
	}

	/** 결정된 타겟을 죽이는 메소드
	 * 
	 * @param targets (타겟의 위치값)
	 * @return 해당 스테이지의 킬수
	 */
	private static int killTarget(int[][] targets) {
		int kill = 0;
		for(int[] t : targets) {
			if(t[0]<0 || t[1]<0 || t[0]>=N || t[1]>=M) continue;
			if(map[t[0]][t[1]] == 1) { //타겟이 겹칠수있으니 if로 확인해서 누적한다
				map[t[0]][t[1]]=0;
				kill++;
			}
		}
		return kill;
	}


	/** 각 궁수가 죽일 타겟을 BFS를 통해 찾는 메소드
	 * @param comb2
	 * @return 타겟의 위치배열
	 */
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	private static int[][] targeting(int[] comb2) {
		//반드시 왼,위,오른 순서대로 + 거리제한은 D
		boolean[][] check = new boolean[N][M];
		int[][] selected = new int[3][2]; //각 궁수의 타겟좌표를 저장
		for(int i=0; i<3; i++) Arrays.fill(selected[i], -1); //타겟을 찾지못하면 -1로 저장되도록 초기화
 		
		Queue<int[]> q = new LinkedList<>();
		
		for(int g=0; g<3; g++) {
			q.offer(new int[] {N,comb2[g],0}); //각 궁수의 초기 위치(즉 탐색 시작노드)
			checkFillFalse(check); //각 궁수의 타겟팅은 독립적이니 체크배열 초기화
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				if(cur[2]>D) {
					q.clear();
					break; //거리제한 넘어서면 더이상 검색하지 않음
				}
				if(map[cur[0]][cur[1]]==1) {
					selected[g] = new int[] {cur[0],cur[1]};
					q.clear();
					break;
				}
				for(int i=0; i<4; i++) {
					int nx = cur[0]+dx[i];
					int ny = cur[1]+dy[i];
					if(nx<0 || ny<0 || nx>=N || ny>=M || check[nx][ny]) continue; //마지막행 볼필요없으니 N일때도 무시
					check[nx][ny] = true;
					q.offer(new int[] {nx,ny,cur[2]+1});
				}
			}
		}

		return selected;
	}

	/** 체크배열을 전부 false로 초기화하는 메소드
	 * @param check
	 */
	private static void checkFillFalse(boolean[][] check) {
		for(int i=0; i<N; i++) {
			Arrays.fill(check[i], false);
		}
	}

}