
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int nowHour = sc.nextInt();
		int nowMin = sc.nextInt();
		int waiting = sc.nextInt();
		
		int totalTime = nowHour*60+nowMin+waiting;
		int resultHour = totalTime/60;
		int resultMin = totalTime%60;
		
		if (totalTime>=24*60) {
			resultHour = (totalTime-24*60)/60;
			resultMin = (totalTime-24*60)%60;
		}
		else {
			resultHour = totalTime/60;
			resultMin = totalTime%60;
		}
		
		System.out.println(resultHour+" "+resultMin);



	}
}
