import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		List list = new ArrayList();
		for (int i=0; i<N; i++) {
			int n = sc.nextInt();
			list.add(n);
		}
		Collections.sort(list);
		
		System.out.println(list.get(0)+" "+list.get(N-1));
		
		
		
		
	}	

}