import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** 회의실 배정
 * 한개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의실에 대해 회의실 사용표를 만든다.
 * 시작시간과 끝나는시간이 주어지고 회의가 겹치지 않게 하며 회의실을 사용할 수 있는 최대 개수를 찾아라.
 * 
 * 풀이 : 수업때 대강 말해줬던거같은데...
 * 음 회의 리스트를 끝나는 시간이 빠른 순서대로 정렬한 뒤에 겹치지 않게 뽑으면 됐을걸?
 * 
 */
public class Main {
	static List<Meeting> list = new ArrayList<>();
	static int N;
	static Meeting current;
	static class Meeting implements Comparable<Meeting>{
		int start, end;
		public Meeting(int s, int e) {
			start = s;
			end = e;
		}
		//끝나는시간이 빠른 순서대로... 즉 숫자가 작을수록 굿이니까 오름차순
		@Override
		public int compareTo(Meeting o) {
			if(this.end != o.end) return this.end - o.end;
			else return this.start-o.start; //끝나는 시간이 같으면 시작시간이 늦을수록.. 즉 숫자가 클수록 굿이니까 내림차순
		}
		
		//파라미터로 주어진 미팅과 비교하여 본 미팅과 겹치는 시간이 있는지 확인하는 메소드
		public boolean checkDuplicate(Meeting m) {
			//시작시간이 겹치는경우
			if(m.start<this.start || m.start<this.end) return false;
			//끝시간이 겹치는경우
			if(m.end<this.start || m.end<this.end) return false;
			return true;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Meeting(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);

		System.out.println(solution());
	}

	private static int solution() {
		current = list.get(0); //current와 겹치지 않는 회의를 뽑는다.
		int cnt = 1;
		
		for(int i=1; i<N; i++) {
			if(current.checkDuplicate(list.get(i))) {
				//가능한 시간대로면 정답에 추가한 뒤 current를 갈아낀다
				cnt++;
				current = list.get(i);
			}
		}
		
		return cnt;
	}
}