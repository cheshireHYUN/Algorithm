import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//흠... 입력을 먼저 쭉 받고 저장해뒀다가 한번에 출력해야하니까
		//배열객체를 만들어서 저장을 해둬야겠다!
		int T = scanner.nextInt();
		int[] resultArray = new int[T];
		
		int result;
		for(int i=0;i<T;i++) {
			int var1 = scanner.nextInt();
			int var2 = scanner.nextInt();
			result = var1+var2;
			resultArray[i] = result; //배열에 덧셈값 다저장
		}

			for(int i=0;i<T;i++) {
			System.out.println("Case #"+(i+1)+": "+resultArray[i]);
			}
			
	
	
	
	}
	

}