import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	public static void main(String[] args) {
		
		// set 이용하면 되겠다
		Scanner sc = new Scanner(System.in);
		Set hs = new HashSet();
		
		for(int i=0; i<10; i++) {
			int var = sc.nextInt();
			var = var%42;
			hs.add(var);
		}
		
		System.out.println(hs.size());
		
	}	

}