import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 설탕배달
 * 사탕가게에 N그램을 배달한다.
 * 전체 N그램중 5와 3그램으로 나눌때 가장 적은 봉지 수가 나와야 하는것
 * 즉, 5의 배수일땐 5로 나누면됨
 * 그러나 5의배수가 아닐땐 그안에 3kg을 사용할일이 있다는 이야기이고,
 * 따라서 5의배수로 나눌 수 있을때까지 3을 빼야한다는 이야기이다.
 * 만약 3을 계속빼다가 5 미만이 되어버렸으면 3을 빼서 0이 될 수 있는지 확인하고 안되면 -1
 * 
 * DP로도 풀수있다~
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int pocket5=0, pocket3=0;
		
		while(true) {
			if(N<=0) break;
			if(N%5==0) {
				pocket5 += N/5;
				N = 0;
				break;
			}
			else {
				N -= 3;
				pocket3++;
			}
		}
		
		if(N<0) System.out.println(-1); 
		else if(N==0) System.out.println(pocket3+pocket5);
		
	}
}