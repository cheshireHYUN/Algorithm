
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int year = scanner.nextInt();
	int judge=0;
	if(((year%4==0)&&(year%100!=0))||(year%400==0)) {
		judge = 1;
	}else {judge = 0;}

	System.out.println(judge);
}
	}
