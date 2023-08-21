import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/** 줄세우기
 * 
 * N명 키순서대로 줄세운다. 두 학생의 키를 비교하는 식으로,,
 * N,M이 주어지며 각각 학생의 번호와 키를 비교한 횟수이다.
 * 즉 출석번호는 1~N까지고 M줄에 걸쳐 비교결과가 주어지며 1,3이 주어지면 1이 3보다 크다는 의미이다
 *
 */
public class Main {
	static int[] numbers;
	static List<Integer>[] adjList;
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //번호
		M = Integer.parseInt(st.nextToken()); //키잰횟수
		
		numbers = new int[N+1]; //1~N까지 학생들이 가지는 앞사람 인원수를 저장. 즉 내앞에 몇명??을 저장
		adjList = new ArrayList[N+1]; //adjList[1]은 1번학생 이후에 설 수 있는 학생번호를 저장. 즉 내뒤에 누구?를 저장
		for(int i=1; i<N+1; i++) adjList[i] = new ArrayList<Integer>(); //인접리스트 초기화
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			// 1 3이 주어지면 1이 3번 앞에서야함, 즉 list[1]은 1번 뒤에 설수있는 번호를 저장한다.
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			adjList[front].add(back);
			numbers[back]++;
		}
		
		solution();
		System.out.println(sb);
	}
	
	
	private static void solution() {
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1; i<N+1; i++) {
			if(numbers[i]==0) q.offer(i); //앞에 설 사람 아무도 없는 요소만 큐에 넣는다
		}
		while(!q.isEmpty()) {
			int front = q.poll(); //앞에 설 사람이 없는, 즉 맨앞사람이 될 수 있는 학생을 세운다
			sb.append(front).append(' ');
			
			for(int back : adjList[front]) {//방금 세운 학생의 뒤에 설수있는 후보
				if(--numbers[back]==0) q.offer(back); //front를 세웠으므로 앞사람 인원수가 줄고, 만약 0이되면 그 학생의 순서이므로 큐에 넣는다
			}
		}
		
		
		
		
	}
}