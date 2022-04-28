import java.util.Scanner;

public class Main {
	// 매년 A만원 고정비용
	// 노트북 한한대당 B만원 들고
	// 대당 C만원에 팔아
	// 총 수입(n*C)이 비용(A+n*B)보다 많아지는 n?
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();  //고정비용
		int B = sc.nextInt();  //한대당 생산비용
		int C = sc.nextInt();  // 판매가
		sc.close();
		
		int n=0; // 최초 이익 생기는 판매량
		
		//sum = (n*C); // 총 수입
		//cost = (n*B+A); // 비용
		// 즉, n=A/(C-B)+1
		
		if(B<C) {
			System.out.println(A/(C-B)+1);
		}else {
			System.out.println("-1");
		}
	
	
	}

}