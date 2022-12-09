

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* # 10448. 유레카 이론
 * 삼각수 : 기하학적으로 일정 규칙을 갖는 점의 모음
 * Tn = 1 + 2 + 3 + ... + n = n(n+1)/2
 * 또한 가우스는 모든 자연수가 최대 3개의 삼각수 합으로 표현될 수 있다고 증명했다.
 * 자연수가 주어졌을때, 그 정수가 3개의 삼각수 합으로 표현될수 있는지 판단해주는 프로그램 만들어라 (있음 1 없음 0)
 * 단, 3개의 삼각수가 모두 달라야할 필요는 없다.
 * */

public class Main {
	public static void main(String[] args) {
		
		// 1. 자연수를 입력받고,

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int input;
		List<Boolean> result = new ArrayList<>();
		for(int i=0; i<t; i++) {
			// 궁금한 수 입력받고
			input = sc.nextInt();
			// 판단메소드 호출하여 결과를 result라는 리스트에 저장
			result.add(eureka(input));
		}
		for(int i=0; i<t; i++) {
			if(result.get(i) == true) {
				System.out.println("1");
			} else {
				System.out.println("0");
			}
		}
		
		
	}
	
	// 값이 세 삼각수의 합으로 표현되는지 판단하는 메소드
	private static boolean eureka(int input) {
		// 10 = T1 + T2 + T3 = T4
		// 10을 표현하는 삼각식은 무조건 T10보다 작겠지!!
		// T10보다 작은 모든 삼각식을 list에 저장해보자
		int tn = 0;
		List<Integer> tList = new ArrayList<>();
		for(int n=1; n<input; n++) {
			tn = n*(n+1)/2;
			tList.add(tn);
		}
		// 이제 궁금한 값에대한 가능성이 있는 삼각수 리스트가 생겼다
		// 리스트에서 3개를 뽑아 더했을때 궁금한값이 되는 조합이 있는지 판단하면 된다.
		// 세 조건식의 범위가 모두 똑같음 = 중복가능함
		for(int i=0; i<tList.size(); i++) {
			for(int j=0; j<tList.size(); j++) {
				for(int k=0; k<tList.size(); k++) {
					if(tList.get(i)+tList.get(j)+tList.get(k) == input) {
						return true;
					}
					
				}
			}
		}
		return false;
	}

}
