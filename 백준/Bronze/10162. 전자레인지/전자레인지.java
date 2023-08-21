import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 베이킹
 * 오븐이 1개있고, 시간조절버튼 A,B,C(5분,1분,10초)가 있다
 * 레시피마다 구워야할 시간 T초가 초단위로 표시되어있고 ABC를 적당히 눌러 T가 되도록한다.
 * 단 버튼 누르는 횟수가 최소가 되어야한다.
 * 
 * 주어진 굽기시간 T초를 맞추기 위한 최소 버튼조작 방법을 구하는 프로그램을 작성해라.
 * 
 * input : T초
 * output : 최소 버튼 조작 ABC의 횟수, (버튼 안누르면 0, T가될수없으면 -1)
 * 
 * 풀이 : 자판기문제같은 반례가 있을까 했는데 세개의 버튼 시간이 300,60,10으로 배수관계라서 반례 없는듯..?
 * 시간이 큰것부터 순서대로 나눈다(확보한다). 또한 애초에 10의 배수가 아니면 -1을 출력한다.
 * 
 */

public class Main {
	static int a=0, c=0, b=0;
	static StringBuilder sb = new StringBuilder();;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		if(T%10!=0) sb.append(-1); //10의 배수가 아니면 불가능한 연산이므로 -1 출력
		else solution(T);
		
		System.out.println(sb);
	}

	// A는 300초, B는 60초, C는 10초
	private static void solution(int T) {
		if(T>=300) { //A버튼의 최대횟수 확보
			a = T/300;
			T %= 300;
		}
		if(T>=60) { //B버튼의 최대횟수 확보
			b = T/60;
			T %= 60;
		}
		if(T>=10) c = T/10; //C버튼의 최대횟수 확보
		
		sb.append(a).append(' ').append(b).append(' ').append(c);
	}
}