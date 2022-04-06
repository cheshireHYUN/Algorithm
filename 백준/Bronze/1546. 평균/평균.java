import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 List<Integer> scoreList = new ArrayList<Integer>();
		 
		 int subject = sc.nextInt();
		 for(int i=0; i<subject; i++) {
			 int score = sc.nextInt();
			 scoreList.add(score);
		 }
		 
		Collections.sort(scoreList);	 
		int M = (scoreList.get(subject-1));
		
		// 평균구해서 나누기M곱하기100 하면 돼ㅑ
		
		int sum=0;
		for(int i=0; i<scoreList.size();i++) {
			sum += (scoreList.get(i));
		}
		
		
		double average = (double) sum/(scoreList.size()*M)*100;
		System.out.println(String.format("%.6f", average));


	}
}