
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* # 2503. 숫자야구
 * 위치와값이 맞으면 스트라이크, 값만맞으면 볼
 * 게임중간부터 시작. 뭘 물어봤고 어떤 대답을했는지 입력으로 주어진다
 * 입력을 바탕으로 영수가 생각하고있는 경우의수를 맞혀야한다.
 * */
public class Main {
	
	public static int [][] arr;
	public static int pre;
	
	public static void main(String[] args) {
		
		// 1. 이전의 게임내역 입력받기
		Scanner sc = new Scanner(System.in);
		pre = sc.nextInt();
		
		int player = 0;
		int strike = 0;
		int ball = 0;
		arr = new int[pre][3];
		for(int i=0; i<pre; i++) {
			player = sc.nextInt();
			strike = sc.nextInt();
			ball = sc.nextInt();
			arr[i] = new int[] {player,strike,ball};
		}
		
		// 2. 게임로직에 의해 발생하는 정답 경우의수를 찾는다..
		// 아하 그냥 100 ~ 999까지 돌면서 이전기록에 부합하는거 찾으면 되곘네!
		List<Integer> result = new ArrayList<>();
		int c = 0;
		for(int i=100; i<=999; i++) {
			c = candidate(i);
			if(c!=0) {
				result.add(c);
				//System.out.println("답:"+c);
			}
		}
		
		// 3. 출력
		System.out.println(result.size());
		

	}

	// 자연수c가 조건에 부합하는지 검사하는 메소드
	private static int candidate(int c) {
		// ex. c가 328
		int[] candi = calc(c);
		if(candi[0] == candi[1] || candi[1] == candi[2] || candi[0]== candi[2]) {
			return 0;
		}else if(candi[0] ==0 || candi[1]==0 || candi[2]==0){
			return 0;
		}
		
		// 조건 검사
		int[] arr2 = new int[3];
		int ball;
		int strike;
		int ok = 0;
		for(int i=0; i<pre; i++) {
			ball=0;
			strike=0;
			//arr[i][0]랑 비교했을때 arr[i][1] arr[i][2]와 갯수가 맞는지.... 
			// 즉 arr2와 arr[i][0]의 숫자야구 결과를 알아야한다.
			arr2 = calc(arr[i][0]); // 123, 356, 327...이 각각 한자리씩(1,2,3) 들어가있음
			
			if(arr2[0] == candi[0]) {
				strike++;
			}
			if(arr2[1] == candi[1]) {
				strike++;
			}
			if(arr2[2] == candi[2]) {
				strike++;
			}
			if(arr2[0]==candi[1] || arr2[0]==candi[2]) {
				ball++;
			}
			if(arr2[1]==candi[0] || arr2[1]==candi[2]) {
				ball++;
			}
			if(arr2[2]==candi[0] || arr2[2]==candi[1]) {
				ball++;
			}
			
			if(arr[i][1] == strike && arr[i][2] == ball) {
				ok = 1;
				continue;
			}else {
				ok = 0;
				break;
			}
		}
		
		if(ok==1) {
			return c;
		}else {
			return 0;
		}
		
	}
	
	private static int[] calc(int c) {
		// ex. c가 328
		int c1 = Math.round(c/100);
		int c2 = Math.round((c - 100*c1)/10);
		int c3 = Math.round(c-100*c1-10*c2);
		int candi[] = new int[3];
		candi[0] = c1;
		candi[1] = c2;
		candi[2] = c3;
		return candi;
	}
}












