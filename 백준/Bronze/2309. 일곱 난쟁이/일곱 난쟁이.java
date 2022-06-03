import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List list = new ArrayList<>();
		
		int sum = 0;
		for(int i=0; i<9; i++) {
			int a =sc.nextInt();
			list.add(a);
			sum += a; 
		}
		Collections.sort(list);
		
		int spy1=0;
		int spy2=0;
		// point : 일곱난쟁이의 키의 합은 100이니까 전체합에서 두명을뺀게 100인 경우를
		// 완전탐색! 브루트포스한다~
		for(int i=0; i<list.size()-1; i++) {
			for(int j=i+1; j<list.size(); j++) {
				if(sum-((int)list.get(i)+(int)list.get(j))==100) {
					spy1 = i;
					spy2 = j;
					break;
				}
			}
		}
		
		for(int i=0; i<list.size(); i++) {
			if(i==spy1 || i==spy2) {
				continue;
			}
			System.out.println(list.get(i));
		}
		
	}
}

