import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 int C = sc.nextInt();
		 List<Integer> scoreList = new ArrayList<Integer>(); // 점수 넣어둠
		 List percentList = new ArrayList();
		 
		 double subScore = 0; // 각 반의 평균점수
		 int score = 0;
		 double percent=0; //비율
		 int count=0; //평균이상인 점수들 세는 변수
		 int scoreAll = 0;
		 
		 for(int i=0; i<C; i++) {
			 scoreList.clear();
			 subScore = 0;
			 count = 0;
			 score = 0;
			 percent=0;
			 scoreAll =0;
			 
			 int N = sc.nextInt();
			 for(int j=0; j<N; j++) {
				 score = sc.nextInt();
				 scoreList.add(score);
				 scoreAll += score;
			 }
			 
			 subScore = scoreAll/N;

			 // scoreList에서 subScore보다 큰 점수인 학생의 비율 계산 (평균이상세는 변수 만들어서 N으로 나누면될듯)
			 for(int k=0; k<scoreList.size(); k++) {
				 if(scoreList.get(k)>subScore) {
					 count += 1;
				 }
			 }
			 percent = (double)count/N*100;
			 String percents = String.format("%.3f",percent);
			 percentList.add(percents);
			 
		 }
		 
		 for(int i=0; i<percentList.size();i++) {
			 System.out.println(percentList.get(i)+"%");
		 }

	}
}