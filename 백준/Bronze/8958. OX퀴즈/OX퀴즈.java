import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		ArrayList quizList = new ArrayList();
		String[] splitList = null;
		ArrayList countList = new ArrayList();
		
		
		int testCase = sc.nextInt();
		// quizList 라는 리스트에 xxxxoooo xoxoxoxo 이런애들이 들어가게 된다
		for(int i=0; i<testCase; i++) {
			String quiz = sc.next();
			quizList.add(quiz);
		}
		
		for(int i=0; i<quizList.size(); i++){
			int count=0;
			int sum =0;
			splitList = ((String) quizList.get(i)).split("");
			for(int j=0; j<splitList.length; j++) {
				// i번째 splitList의 요소(OOXXOXOXX)의 길이만큼 돌면서 O의 갯수를 카운트해 리스트에 넣자
				if(splitList[j].equals("O")) {
					count += 1;
					countList.add(count);
				}else {
					count = 0;
				}
			}
			for(int k=0; k<countList.size();k++) {
				sum += (int)countList.get(k);
			}
			System.out.println(sum);
			countList.clear();
			splitList = null;
		}
		

		
	}
}