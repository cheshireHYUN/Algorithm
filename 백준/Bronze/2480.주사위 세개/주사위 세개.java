import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int dice1 = scanner.nextInt();
		int dice2 = scanner.nextInt();
		int dice3 = scanner.nextInt();
		int result = 0;

		if (dice1 == dice2 && dice1 == dice3 && dice2 == dice3) {
			result = 10000 + dice2 * 1000;
		}
		else if (dice1 == dice2 || dice1 == dice3) {
			result = 1000+dice1*100;
		}
		else if (dice2==dice3) {
			result = 1000+dice2*100;
		}
		else {
			result = Math.max(dice3, Math.max(dice1, dice2))*100;
		}
		
		System.out.println(result);

	}
}