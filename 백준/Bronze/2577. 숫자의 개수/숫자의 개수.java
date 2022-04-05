import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		int result = A*B*C;
		
		String strResult = Integer.toString(result);
		List list = new ArrayList();
		//최소 1,000,000에서 997,000,000
		for(int i=0; i<strResult.length(); i++) {
			String arr = strResult.substring(i,i+1); //0번
			list.add(arr);
		}
		
		System.out.println(Collections.frequency(list, "0"));
		System.out.println(Collections.frequency(list, "1"));
		System.out.println(Collections.frequency(list, "2"));
		System.out.println(Collections.frequency(list, "3"));
		System.out.println(Collections.frequency(list, "4"));
		System.out.println(Collections.frequency(list, "5"));
		System.out.println(Collections.frequency(list, "6"));
		System.out.println(Collections.frequency(list, "7"));
		System.out.println(Collections.frequency(list, "8"));
		System.out.println(Collections.frequency(list, "9"));

		
		
	}	

}