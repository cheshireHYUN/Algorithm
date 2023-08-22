import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 서로소 집합
 * {1},{2},..{n}이 각각 n개의 집합을 이루고있다
 * 합집합 연산과 두 원소가 같은집합인지 확인하는 연산을 수행하려 한다.
 * 
 * input : 입력테케 T
 * 첫째줄에 n(집합갯수),m(입력으로 주어지는 연산갯수)
 * 둘쨋줄부터 m줄에 각각 연산 주어짐
 * 0 a b => a가 포함된 집합과 b포함된 집합을 합친다(합집합)
 * 1 a b => 두원소가 같은집합안에 있는지 확인한다 => output : 같은집합이면 1 아니먄 0 출력
 * 
 * 
 */
public class Solution {
	static int[] arr, rank;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			sb.append('#').append(t).append(' ');
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			//초기 집합 셋팅(자신이 자신의 루트인 독립적인 집합 생성)
			arr = new int[n+1];
			rank = new int[n+1];
			for(int i=1; i<n+1; i++) arr[i] = i;
			
			//입력에 따른 union/find 연산
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int type = Integer.parseInt(st.nextToken());
				
				if(type==0) { //합집합 연산
					union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				}else if(type==1) { //동일집합인지 확인 - 루트노드가 같은지 확인
					if(find(Integer.parseInt(st.nextToken()))
							== find(Integer.parseInt(st.nextToken()))) sb.append(1);
					else sb.append(0);
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	private static int find(int var) {
		if(var == arr[var]) return var; //배열값(루트노드값)이 본인이면 최상단이므로 리턴..
		
		//Path Compression
		return arr[var] = find(arr[var]); //현재 루트노드가 아니라면 자신의 부모값으로 더 윗부모를 찾음..
	}

	//합집합 연산
	private static boolean union(int var1, int var2) {
		int root1 = find(var1);
		int root2 = find(var2);
		
		//이미 같은 집합이면 합집합 불가
		if(root1 == root2) return false;
		
		arr[root2] = root1;
		
//		link(var1,var2);
		return true;
	}
	
	//서로 다른 집합일경우 합집합 실행 - 대표자 집합을 rank값 기준으로 합친다. 즉 깊은쪽을 대표자로 설정한다.
//	private static void link(int var1, int var2) {
//		if(rank[var1] > rank[var2]) arr[var2] = var1;
//		else if(rank[var1] < rank[var2]) arr[var1] = var2;
//		else if(rank[var1] == rank[var2]) rank[var2]++;
//	}
}