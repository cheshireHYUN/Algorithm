import java.util.Arrays;
import java.util.Scanner;

/**
 * N과 M이 주어졌을때 조건을 만족하는 길이가 M인 수열
 * 조건 1. 1부터 N까지 자연수중 중복없이 M개를 고른 수열
 * 조건 2. 고른 수열은 오름차순이어야 함
 *
 */
public class Main {
	static int M,N;
	static int[] numbers;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		numbers = new int[M];
		
		solution(0, 1);
		System.out.println(sb);
	}

	private static void solution(int cnt, int idx) { //현재까지 조합 원소 갯수, 조합시도할 시작인덱스
		// 1부터 n까지 중복없이 m개를 고른다..
		if(cnt == M) {
			for(int n : numbers) sb.append(n+" ");
			sb.append("\n");
			return;
		}
		for(int i=idx; i<=N; i++) {
			numbers[cnt] = i;
			solution(cnt+1, i+1);
		}
	}

}