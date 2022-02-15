import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int H = scanner.nextInt();
		int M = scanner.nextInt();
		int wakeupHour =0;
		int wakeupMin =0;
		//0시 45분 부터는 ㄱㅊ... 그럼 0시 44분까지는? 만약 0시 15분이면 30분, 0시10분이면 25분이 나와야
		if(H==0 && M<45) {
			wakeupHour = 23;
			wakeupMin = M+15;
			}
		else {
			// 분 기준으로 바꾸기
			int totalTime = H * 60 + M;
			int wakeupTime = (totalTime - 45);
			wakeupHour = wakeupTime/60;
			wakeupMin = wakeupTime%60;
			
		}
		
		System.out.println(wakeupHour+" "+wakeupMin);

	}
}
