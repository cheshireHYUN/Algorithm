

import java.util.Scanner;

/* #2231. 분해합
 * 자연수 N의 분해합 : N과 N을 이루는 각 자리수의 합
 * M의 분해합이  N이면, M은 N의 생성자.
 * 245의 분해합은 245+2+4+5, 즉 245는 256의 생성자
 * 
 * 생성자가 없는경우 0을 출력한다..
 * 
 * N이 주어졌을때 N의 가장 작은 생성자를 구하라
 * 1 <= N <= 1,000,000
 * 입력 : 216
 * */

public class Main {
	
	public static void main(String[] args) {
		
		// 반복문 돌려서 찾아야되겠지뭐
		// 분해합을 구하는 코드를 작성해
		// 분해합이 216일때 생성자를 프린트
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// 범위내 모든 자연수 i의 분헤합을 구하는 코드, 즉 i가 생성자!
		int sum=0;
		for(int i=1; i<N; i++) {
			
			String k = Integer.toString(i);
			String[] arr = k.split("");
			sum = i;
			for(int j=0; j<arr.length; j++) {
				sum += Integer.parseInt(arr[j]);
			}
			
			if(sum == N) {
				System.out.println(i);
				break;
			}
		}
		
		// 생성자가 없는경우 0을 출력
		if(sum != N) {
			System.out.println("0");
		}
	}

}
