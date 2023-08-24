import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * N*N개의 벌통이 정사각형 모양으로 배치되어있다
 * 각 칸의 숫자는 벌통의 꿀 양을 나타내며 서로 다르다.
 * 
 * 각 벌통에 있는 꿀 양이 주어졌을때 벌꿀을 채취해 최대한 많은 수익을 얻으려고한다.
 * 
 * 두명의 일꾼이 있다. 
 * 한명당 꿀을 채취할 수 있는 벌통의 수 M이 주어질때 각 일꾼은 가로로 연속M개를 선택해 채취(겹치면 안됨)
 * 두 일꾼은 벌통에서 꿀을 채취해 담는다. 최대C만 채취할 수 있으므로 M개의 합이 C이하면 다 얻을수있다.
 * 다만 M개의 합이 C초과면 그중 합이 C이하인것들만 선택해서 뽑아야한다. 
 * 
 * 꿀의양의 제곱만큼 수익을 책정한다. 6을 채취하면 수익은 36인것임
 * 
 * [풀이]
 * 1. M연속 위치를 두개 선택해주는 모든 조합을 찾아야함. 어떻게?
 * => 행렬 자체를 행별로 3개씩 자리는 모든 경우의수들을 구해둔다. 그리고 이중에서 안겹치는걸로 2개 뽑으면 되자나!!
 * 2-1. 그리고 각 경우의수에 대해 총합이 C이하이면 다 더해서 제곱해 저장하고,
 * 2-2. C초과이면 부분집합을 구해 C이하인 조합중 최대값을 구해 제곱해 저장한다.
 * 3. 그러면 모든 조합에 대해 최대수익이 결정됨. 이때 내림차순 정렬한 뒤 가장 큰 조합을 찾는다.
 * 3-1. 겹치면 안되니까 다른행이면 무조건 통과, 같은행이면 끝과 시작이 겹쳐있는지 확인하기
 * 
 * [로직]
 * 같은행인지 다른행인지 찾아야하니.. 각 조합을 구한뒤 판매가를 구하는 메소드를 실행시키고,
 * 끝난뒤엔 판매가를 리턴받아 좌표와 함께 List에 저장해두면 되겠다. 이걸 내림차순
 */
public class Solution {
	static int N,M,C,map[][];
	static List<Node> list;
	static StringBuilder sb = new StringBuilder();
	static class Node implements Comparable<Node>{
		int row, col, maxPrice;

		public Node(int row, int col, int maxPrice) {
			super();
			this.row = row;
			this.col = col;
			this.maxPrice = maxPrice;
		}

		@Override
		public int compareTo(Node o) {
			//return this.maxPrice - o.maxPrice;
			return o.maxPrice - this.maxPrice;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //배열크기
			M = Integer.parseInt(st.nextToken()); //인당 벌통 커버리지
			C = Integer.parseInt(st.nextToken()); //채취 최대값
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			isSelected = new boolean[M];
			cutMap();
			sb.append('#').append(tc).append(' ').append(getAnswer()).append('\n');
		}
		System.out.println(sb);
	}

	private static int getAnswer() {
		Collections.sort(list); //내림차순 정렬
		//가장 큰 두개를 꺼내준다. 다만 행이같으면 시작점 겹치는지 체크해주고, 행이 다르면 무조건 꺼내면됨
		Node select1 = list.get(0);
		Node select2 = null;
		list.remove(0);
		for(Node n : list) {
			if(n.row != select1.row) {
				select2 = n;
				break;
			} else {
				//시작점 겹치면 continue, 안겹치면 break
				
				//select1의 시작점과 끝점
				int s1 = select1.row;
				int e1 = select1.row+M-1;
				//n의 시작점과 끝점
				int s2 = n.row;
				int e2 = n.col;
				
				// select1의 시작점과 끝점 사이에 n의 시작점 또는 끝점이 없으면됨 
				if( (s2 >= s1 &&  s2 <= e1) || (e2 >= s1 &&  e2 <= e1)) continue;
				else {
					select2 = n;
					break;
				}
			}
		}
		
		return select1.maxPrice+select2.maxPrice;
	}

	//맵을 행별로 M길이만큼 자르는 모든 경우의수 찾기
	private static void cutMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(j+(M-1) >= N) continue; //열값이 N을 넘어서면 안됨
				//해당 연속M에 대해 최대 판매가 찾기
				blockMax = 0;
				getMaxPrice(0, i,j);
				//이제 blockMax 전역변수에 해당 블록의 최대수익이 저장되어있음
				//따라서 시작점 좌표정보와 함께 리스트에 저장
				list.add(new Node(i,j,blockMax));
			}
		}
	}

	//연속M의 시작점이 주어질때 최대판매가를 찾아야함 -> 부분집합 사용
	static boolean[] isSelected;
	static int blockMax; //한블럭에 대해 최대판매가를 저장함
	
	//M개의 연속점에서 모든 부분집합 만드는 메소드
	private static void getMaxPrice(int cnt, int x, int y) {
		if(cnt == M) {
			//부분집합 완성
			int sum = 0;
			int flag = 0;
			for(int j=y, index=0; j<y+M; j++) {
				if(isSelected[index]) {
					flag+=map[x][j];
					sum+=Math.pow(map[x][j],2);
				}
				index++;
			}
			if(flag <= C) blockMax = Math.max(blockMax, sum);
			return;
		}
		
		isSelected[cnt] = true;
		getMaxPrice(cnt+1, x, y);
		isSelected[cnt] = false;
		getMaxPrice(cnt+1, x, y);
		
	}
}