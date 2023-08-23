import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/** Contact
 * 비상연락망과 연락시작당번 정보가 주어질때 가장 나중에 연락을 받게되는 사람중 번호가 가장 큰 사람?
 * 
 * 연결리스트.. 만들어서 시작점부터 bfs하면 되지 않을까?
 * 1번 - 7,8,17
 * 2번 - 7, 15
 * 3번 - 22
 * 4번 - 2, 10
 * 6번 - 2
 * 7번 - 1
 * 8번
 * 9번
 * 10번
 * 11번 - 6
 * 15번 - 4
 * 17번
 * 22번
 * 
 * 시작점 2가 주어지면 7,15를 탐색해서 큐에 넣음 방문배열 필요
 * 7이 poll되면 1들어가고
 * 15가 poll되면 4들어가고
 * 1이 폴되면 7,8,17
 * 4가 폴되면 2,10
 * 7이 폴되면 1인데 체크된거니까 걍 다음
 * 8이 폴되면 다음
 * 17이 폴되면 다음
 * 2가 폴되면 7,15인데 15만 들어감
 * 10이 폴되면 다음
 * 15가 폴되면 4인데 체크됐으니까 끝
 *
 * 레벨별 탐색할때 젤 큰녀석을 계속 갱신해주면 되지 않을까 합니다
 */
public class Solution {
	static ArrayList<Integer>[] list = new ArrayList[101]; 
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=10; t++) {
			//입력데이터 길이와 시작점
			st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			//초기화
			for(int i=0; i<101; i++) list[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<length/2; i++) {
				int from = Integer.parseInt(st.nextToken()); //부모
				int to = Integer.parseInt(st.nextToken()); //자식
				list[from].add(to);
			}
			
			check = new boolean[101];
			sb.append('#').append(t).append(' ').append(solution(start)).append("\n");
		}
		System.out.println(sb);
	}
	
	//start를 기준으로 더이상 탐색 불가할때까지 bfs
	private static int solution(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		check[start] = true;
		q.offer(start);
		
		int lastMax = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			int tmpMax = 0;
			while(size-- > 0) {
				int curr = q.poll();
				tmpMax = Math.max(curr, tmpMax);
				for(int next : list[curr]) {
					if(!check[next]) {
						check[next] = true;
						q.offer(next);
					}
				}
			}
			//각 레벨별로 탐색 -> poll했던것중 가장 큰 숫자를 저장한다.
			lastMax = tmpMax;
		}
		
		return lastMax;
	}
}