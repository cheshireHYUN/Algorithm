import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*** 최적경로
 * N명의 고객을 방문하고 집에 돌아가려 한다.
 * 회사와 집의 위치, 그리고 각 고객의위치는 이차원 정수 좌표 (x,y)로 주어지고
 * 두 위치 사이의 거리는 (x1,y1) (x2,y2) => |x1-x2|+|y1-y2|
 *
 * 회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로중 가장 짧은것?
 * 이동거리만 출력하면 되며, 효율적으로 찾는것이 목적이 아님. 모든 가능한 경로를 찾아서 해를 찾아도 ㅇㅋ
 * 
 * 시간복잡도 :
 * 1. 고객 방문순서 정하기 => 순열
 * 2. 정해진 방문순서에 따라 이동거리 계산 => 회사->고객방문->집
 * 3. 해당 이동거리가 최소인경우 갱신한다.
 * 순열의 시간복잡도는 (N개중 N개를 뽑아 나열하는 것이므로) N!
 * N최댓값은 10이므로 10! = 약 400만이므로 문제없음
 */

class Node {
	int x,y;
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
	@Override
		public String toString() {
			return "x:"+x+", y:"+y;
		}
}
public class Solution {
	static Node[] map;
	static Node[] tmp;
	static boolean[] check;
	static int N, result, tcCnt;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("src/algo0823/input_1247.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		for(tcCnt =1; tcCnt<=tc; tcCnt++) {
			N = Integer.parseInt(br.readLine()); //고객의 수
			
			StringTokenizer tk = new StringTokenizer(br.readLine());
			// 회사좌표, 집좌표, 고객 N명의 좌표가 map에 들어감
			map = new Node[N+2];
			tmp = new Node[N+2];
			
			check = new boolean[N+2];
			for(int j=0; j<N+2; j++) {
				map[j] = new Node(Integer.parseInt(tk.nextToken()),Integer.parseInt(tk.nextToken()));
			}
			check[0] = true; //맨 처음엔 회사를 체크..
			tmp[0] = map[0]; //출발지는 회사로 설정
			tmp[1] = map[1]; //출발지는 회사로 설정
			result = Integer.MAX_VALUE; //결과 초기화
			solution(2);
			sb.append("#"+tcCnt+" "+result+"\n");
		}
		System.out.println(sb);
	}

	private static void solution(int index) { //이번에 채울 순서인덱스
		// 모든 경로를 찾는 방법 -> 순열이겠네.. 
		if(index == N+2) {
			//손님탐색 끝
			int tmpResult = 0;

			// 0번 인덱스에서 출발 후 2~N+1번째 인덱스를 순회후 마지막에 1번 인덱스에 도착함
			//출발하기
			tmpResult += (Math.abs(tmp[2].x - tmp[0].x)+Math.abs(tmp[2].y - tmp[0].y));
			//2번부터 N+1번째 손님집 돌아다니기
			for(int i=3; i<=N+1; i++) { // i=3의 의미는 그전경로에서 3번노드까지 가는 경로의 거리이다. 즉 N+1번째(손님 마지막집)까지 돌아야 N+1번째 집에 도착하는것.
				tmpResult += (Math.abs(tmp[i].x - tmp[i-1].x)+Math.abs(tmp[i].y - tmp[i-1].y));
			}
			//집으로 돌아가기
			tmpResult += (Math.abs(tmp[N+1].x - tmp[1].x)+Math.abs(tmp[N+1].y - tmp[1].y));
			
			result = Math.min(result, tmpResult);
		}
		
		
		// 손님이 N명이면 1부터 N자리에 정보가 있음
		for(int i=2; i<N+2 ;i++) {
			if(check[i]) continue;
			check[i] = true;
			tmp[index] = map[i];
			solution(index+1);
			check[i] = false;
		}
	}
}
