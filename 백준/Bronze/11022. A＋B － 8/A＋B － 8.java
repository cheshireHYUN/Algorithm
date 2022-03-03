import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//흠... 입력을 먼저 쭉 받고 저장해뒀다가 한번에 출력해야하니까
		//배열객체를 만들어서 저장을 해둬야겠다!
		int T = scanner.nextInt();
		int[] resultArray = new int[T];
		int[] varArray1 = new int[T];
		int[] varArray2 = new int[T];
		
		int result = 0, var1 = 0, var2 = 0;
		for(int i=0;i<T;i++) {
			var1 = scanner.nextInt();
			varArray1[i] = var1;
			var2 = scanner.nextInt();
			varArray2[i] = var2;
			result = var1+var2;
			resultArray[i] = result; //배열에 덧셈값 다저장
			
		}

			for(int i=0;i<T;i++) {
			System.out.println("Case #"+(i+1)+": "+varArray1[i]+" + "+varArray2[i]+" = "+resultArray[i]);
			}
			
	
	
	
	}
	

}