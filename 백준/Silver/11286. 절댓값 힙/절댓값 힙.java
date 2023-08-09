import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 절댓값 힙
 * 다음을 지원하는 자료구조이다.
 * 1. 배열에 정수 x(!=0)를 넣는다
 * 2. 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.
 *    작은값이 여러개라면 가장 작은 수를 출력하고 그 값을 배열에서 제거한다.
 * 3. 0이 입력되면 답을 출력한다. 비어있을때 0이 입력되면 0을출력하면 된다.
 * 
 */
public class Main {
	static PriorityQueue<Node> q;
	static class Node implements Comparable<Node>{
		int num;
		int absNum;
		public Node(int x, int absNum) {
			num = x;
			this.absNum = absNum;
		}
		@Override
		public int compareTo(Node o) {
			if(Math.abs(this.num) == Math.abs(o.num)) return this.num - o.num;
			return Math.abs(this.num)-Math.abs(o.num);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		q = new PriorityQueue<Node>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			if(tmp==0) { //답을 프린트한다
				//만약 큐가 비었다면 0을 출력
				if(q.isEmpty()) sb.append("0\n");
				else { //큐가 비어있지 않다면
					sb.append(q.poll().num+"\n");
				}
			}else { //큐에 넣는다
				q.offer(new Node(tmp, Math.abs(tmp)));
			}
		}
		System.out.println(sb);
	}
}