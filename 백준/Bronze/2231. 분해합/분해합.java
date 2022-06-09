import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.omg.PortableInterceptor.SUCCESSFUL;

public class Main {
	public static void main(String[] args) {
		
		// N이 주어졌을때 그의 가장 작은 생성자를 구하는 프로그램을 작성
		
		// 문자 입력받기
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// 분해합을 구하는 부분
		// M을 한글자씩 잘라 int형태로 리스트에 넣기
		for(int i=1; i<=1000000; i++) {
			String M = Integer.toString(i);
			String[] StringMArray = M.split("");
			List<Integer> IntMArray = new ArrayList<>();
			for(String n : StringMArray) {
				int n2 = Integer.parseInt(n);
				IntMArray.add(n2);
			}
			// 더하기
			int sum = Integer.parseInt(M);
			for(int j : IntMArray) {
				sum += j;
			}
			
			if(sum==N) {
				System.out.println(M);
				System.exit(0);
			}
		}
		
		System.out.println("0");
	}
	
}

