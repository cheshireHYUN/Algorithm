import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
	    int var1 = 1;
		int var2 = 1;
		Scanner sc = new Scanner(System.in);
		List list1 = new ArrayList();
		
		
		while(var1 !=0 && var2 != 0) {
			var1 = sc.nextInt();
			var2 = sc.nextInt();
			int var = var1+var2;
			list1.add(var);
		}
		
		for(int i=0; i<list1.size()-1; i++) {
			System.out.println(list1.get(i));
		}
		
	}

}