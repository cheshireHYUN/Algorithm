import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		int number = sc.nextInt(); // number = 26
		int newNumber = 100;
		int count=0;
		
		while (number != newNumber) {												//26 != 68		26 != 84
			if(newNumber == 100) {newNumber = number;} // newNumber = number = 26				안함								
			int firstNum = newNumber/10; // 2											6
			int secondNum = newNumber%10; //6											8
			int thirdNum = firstNum + secondNum; //8 									14
			newNumber = secondNum*10+(thirdNum%10); // newNumber = 68, number = 26 		84

			count += 1;

		}
		System.out.println(count);
		


}}