import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/* #2309. 일곱난쟁이
 * 난쟁이가 일곱이 아니라 아홉..
 * 일곱난쟁이의 키 합은 100, 일곱난쟁이를 찾아라!
 * 입력 : 20 7 23 19 10 15 25 8 13
 * 출력 : 일곱난쟁이 키를 오름차순으로 출력
 * 
 * 반례: 답이 여러개일경우
 * */

public class Main {
	public static void main(String[] args) {
		
		// 1. 입력받기
		Scanner sc = new Scanner(System.in);
		List <Integer> allDwarf = new ArrayList<> ();
		int total = 0;
		for(int i=0; i<9; i++) {
			int input = sc.nextInt();
			allDwarf.add(input);
			total += input;
		}
		
		// 2. 미리 정렬
		allDwarf.sort(Comparator.naturalOrder());
		
		// 3. for문 돌며 합이 100인조합 찾아내기
		for(int i=0; i<8; i++) {
			int end = 0;
			for(int j=i+1; j<9; j++) {
				int sum = total;
				sum = sum-allDwarf.get(i)-allDwarf.get(j);
				if(sum==100) {
					// 3. 가짜 빼고 오름차순 출력
					for(int a = 0; a<9; a++) {
						if( (a!=i) && (a!=j) ) {
							System.out.println(allDwarf.get(a));
						}
					}
					end = 1 ;
					break;
				}
			}
			if(end == 1) {
				return;
			}
		}
	}


}
