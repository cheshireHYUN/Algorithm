import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<Integer> list1 = new ArrayList<Integer>();
		for (int i=0; i<9; i++) {
			int n = sc.nextInt();
			list1.add(n);
		}
		
		int max=0;
		for (int arr : list1) {
			if(max < arr) {
				max=arr;
			}
		}
		System.out.println(max);
		System.out.println(list1.indexOf(max)+1);
		
	}	

}