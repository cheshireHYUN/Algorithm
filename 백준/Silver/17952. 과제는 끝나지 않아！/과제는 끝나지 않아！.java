import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/** 업무평가
 * 
 * 업무는 가장 최근에 주어진 순서대로 한다. 업무를 받으면 바로 시작한다
 * 업무를 하던 중 새로운 업무가 추가되면 하던걸 중단하고 새로운 업무를 한다.
 * 새로운 업무가 끝나면 이전에 하던 업무를 이전에하던 부분부터 이어서 한다.
 * 
 * 업무를 받자마자 몇분걸릴지 알 수 있고, 마감시엔 무조건 만점받는다.
 * 업무평가를 점수로 예상해보자.
 * 
 * input : 이번분기가 몇분인지 나타내는 정수 N이 주어지고, 2번째줄부터 N줄동안 N분째에 주어진 업무의 정보가 주어진다
 * - 1 A T : 만점은 A점이고, 과제해결하는데 T분이 걸린다 A와 T는 모두 정수이다.
 * - 0 : 해당 시점에는 업무가 주어지지 않는다.
 * 
 * 풀이 : 1->2->3번 과제를 순서대로진행할때, 3번을 끝내면 이전에 하던 2를 이어서 하므로.. 선입후출구조이다.
 * 따라서 Stack을 이용하여 풀이할 수 있을듯. 대신 Stack에 넣는 데이터를 클래스를 구현해서 사용한다.
 * A와 T뿐만아니라 진행도.. 즉 지금껏 진행한 시간을 같이 설계하고, T=진행도이면 점수를 올려준다.
 * - index가 0일때 : stack의 peek의 진행도를 바꿔준다. 그리고나서 완료되었는지도 확인한다.
 * - index가 1일때
 * 		:t가 1분일때 = 바로 끝나는 업무이므로 스택에 넣지않고 점수올린다
 * 		:t가 2이상일때 = 진행도(1)를 기록하여 스택에 넣는다.
 *
 */
public class Main {
	static class Node{
		int score; //평가
		int time; //시간
		int proceed; //진행도
		public Node(int a, int t) {
			score = a;
			time = t;
			proceed = 1; //처음 노드를 생성할때 진행도는 무조건 1
		}
		
		private boolean isSuccess() { //업무가 완료되었는지 계산하는 메소드
			if(time-proceed == 0) return true; //총 걸리는 시간 - 현재진행도가 0이면 완료된것이니 true를 리턴한다.
			return false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		Stack<Node> stack = new Stack<>(); //스택을 이용하여 업무순환구조를 관리한다.
		int answer = 0; //업무평가점수를 저장할 변수
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			
			if(index==0) { //0으로 시작하는 입력은 업무가 없는것이다. 즉 직전업무를 이어서 하므로 peek의 진행도를 업데이트
				if(!stack.isEmpty()) {
					Node curr = stack.peek();
					curr.proceed += 1;
					if(curr.isSuccess()) { //진행도를 올렸으니 업무가 끝났다면 점수누적 후 해당업무 pop
						answer += curr.score;
						stack.pop();
					}
				}
			}
			else if(index==1) { //1로 시작하는 입력은 업무가 있는것, 즉 새 노드를 만들어 스택에 넣어준다.
				Node curr = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				if(curr.isSuccess()) answer+=curr.score; //만약 곧바로 끝나는 업무였다면 바로 점수를 누적해주며, 스택엔 넣지 않는다.
				else stack.add(curr);
			}
		}
		
		System.out.println(answer);
	}
}