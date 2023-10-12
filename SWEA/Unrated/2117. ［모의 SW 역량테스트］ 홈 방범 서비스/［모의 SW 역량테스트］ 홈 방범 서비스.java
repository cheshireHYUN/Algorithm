import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 홈 방범 서비스
 * 
 * 마름모모양의 영역에서만 방범서비스가 제공되며, 운영비용이 필요하다.
 * 서비스 영역의 크기 K가 커질수록 운영비용이 커진다.
 * 운영비용 = [K*K]+[(K-1)*(K-1)]
 * 
 * 운영 영역의 크기 K는 1이상의 정수이다.
 * 도시영역을 벗어난 영역에 서비스 제공해도 운영비용은 변경되지 않음
 * 
 * 홈서비스 제공받는 집은 M비용을 제공하므로 최대한 많은집에 서비스 제공하고싶다
 *
 * input : 도시크기 N 한집이 지불하는 비용 M 도시정보가 주어진다
 * output : 손해보지않으면서 홈방범 서비스를 가장 많은 집에 제공하는 서비스 영역을 찾고 집 수를 출력 =>
 * 
 * 풀이 : K=1부터 시작해서 돌건데,, 해당위치에서 또 K=2를 할때랑 연산이 중복된다.
 * 즉 맵의 각 위치를 중앙값으로 하는 서비스영역을 K=1부터 최악적자일때까지 한둘레씩 추가하여
 * 서비스 되는 홈 갯수(이득이 0이상일때만)를 계산해본다.
 * 이때 최악적자 : 집의갯수XM보다 운영비용이 커질때임!
 * 
 * 풀이 + ) 모든 집의 좌표를 우선순위큐에 넣어두고 순서대로 정렬. 중앙값 정하고 그 기준으로 마름모안에 있는 집들만 계산하기...
 * 즉 중앙값 기준으로 최단거리가 일정숫자인 집들만 계산하기! 나는 DP를 이용해서 풀거임 ㅅㄱ
 */
public class Solution {
	static int N, M, map[][], answer;
	static StringBuilder sb = new StringBuilder();
	static List<Node> list;
	static class Node{
		int x,y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int getDistance(Node center){
			return Math.abs(center.x-x)+Math.abs(center.y-y)+1;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int tc = Integer.parseInt(br.readLine());
	    for(int t=1; t<=tc; t++) {
	    	answer = 0;
	    	list = new ArrayList<>();
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	N = Integer.parseInt(st.nextToken()); //도시크기
	    	M = Integer.parseInt(st.nextToken()); //집당지불비용
	    	map = new int[N][N];
	    	
	    	for(int i=0; i<N; i++) {
	    		st = new StringTokenizer(br.readLine());
	    		for(int j=0; j<N; j++) {
	    			map[i][j]=Integer.parseInt(st.nextToken());
	    			if(map[i][j]==1) list.add(new Node(i,j));
	    		}
	    	}
	    	
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) solution(new Node(i,j));
			}
	    	
			sb.append('#')
			.append(t)
			.append(" ")
			.append(answer)
			.append('\n');
	    }
	    System.out.println(sb);
	}
	
	
	//현재 center에서 손해보는지 안보는지 체크하고 손해안보면 집갯수 카운트하기
	//누적합을 통해 계산할것임..
	private static void solution(Node center) {
		int K=1;
		int[] dist = new int[100]; //걍 충분히 크게 만들어줌..
		while(true) {
			//현재 center를 기준으로 K=1, K=2, K=3.. 계속해서 계산해본다
			int sum = 0;
			for(Node home : list) {
				if(K==home.getDistance(center)) sum++; //거리가 K인 집들 갯수의 합
			}
			dist[K] = dist[K-1]+sum;
			updateAnswer(K, dist[K]);
			
			if(dist[K] == list.size()) break;
			else K++;
		}
	}


	private static void updateAnswer(int K, int dist) {
		//손해를 보지 않을때만 정답을 업데이트해준다.
		int cost = K*K+(K-1)*(K-1); //비용
		int sumM = dist*M;
		if(sumM-cost>=0) answer = Math.max(answer,dist);
	}

}