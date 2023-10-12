import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 숫자만들기
 * 
 * N개의 숫자가 적혀있는 게임판이 있고, 연산자카드를 숫자사이에 끼워넣어 다양한 결과를 구한다.
 * 수식을 계산할때 연산자 우선순위는 고려하지 않고 왼쪽에서 오른쪽으로 차례대로 계산한다.
 * 
 * 즉 숫자는 그자리에 있고, 연산자 순서를 결정해서 최소와 최대를 찾는문제이다.
 * 
 * input : 연산자의 갯수가 순서대로(+,-,*,/) 주어짐, 숫자카드도 주어짐
 * output : 연산자카드를 사용하여 수식을 계산했을때 그 결과가 최대가 되는 수식과 
 * 최소가 되는 수식 찾고 그 차이를 출력해라.
 * 
 * 풀이 : 백트래킹 이용해서 수열로 풀면 되지 않을까..
 * 
 */
public class Solution {
	static int N, operators[], numbers[], tmp[], max,min;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int t=1;t<=TC;t++) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			numbers = new int[N];
			tmp = new int[N-1];
			operators = new int[4]; //더하기,빼기,곱하기,나누기
			
			//각 연산자의 갯수
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) operators[i] = Integer.parseInt(st.nextToken());
			
			//수식에 사용되는 숫자
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) numbers[i] = Integer.parseInt(st.nextToken());
			
			
			solution(0);
			sb.append('#').append(t).append(' ').append(max-min).append('\n');
		}
		System.out.println(sb);
	}
	
	private static void solution(int cnt) {
		if(cnt == N-1) {
			//연산자 순열 완성 -> 연산식 계산하기
			calc();
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(operators[i] == 0) continue;
			operators[i]--;
			tmp[cnt] = i;
			solution(cnt+1);
			operators[i]++;
		}
	}

	private static void calc() {
		int num = numbers[0];
		for(int i=0; i<N-1; i++) {
			if(tmp[i]==0) num+= numbers[i+1];
			else if(tmp[i]==1) num-= numbers[i+1];
			else if(tmp[i]==2) num*= numbers[i+1];
			else if(tmp[i]==3) num/= numbers[i+1];
		}
		
		if(num>max) max = num;
		if(num<min) min = num;
	}


}