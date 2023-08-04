import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 햄버거다이어트
 * 햄버거의 맛은 유지하면서 정해진 칼로리를 넘지 않는 햄버거를 주문해서 먹으려고 한다.
 * 재료에 대한 맛에 매겨놓은 점수와 칼로리가 주어졌을때, 햄버거를 조합해보자
 * 같은재료를 여러번 사용할수는 없으며, 조합의 제한은 칼로리를 제외하곤 없다.
 */

/*
문제해결 프로세스

1. 햄버거 식재료의 모든 조합

       ⇒ 부분집합

1. 해당 조합의 칼로리, 맛의 총합
2. 총 칼로리합이 L이하인 맛의 최대치?

시간복잡도?

부분집합의 시간복잡도는 2^N

N의 최댓값이 20

2^20 약 100만.. 1억 이하이므로 가능핟.
*/
class Food{
	int score;
	int kcal;
	Food(int score, int kcal){
		this.score = score;
		this.kcal = kcal;
	}
	@Override
		public String toString() {
			return "x:"+score+",y:"+kcal;
		}
}
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int maxKcal, N, result;
	static Food[] foodArr;
	static boolean[] check;
	
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("src/algo0823/input_5215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++) {
			// 1. 재료의 수와 제한 칼로리가 주어진다
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			maxKcal = Integer.parseInt(st.nextToken());
			// 2. 각 재료의 맛과 칼로리
			foodArr = new Food[N];
			
			check = new boolean[N];
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				foodArr[j] = new Food(Integer.parseInt(st.nextToken()), 
									Integer.parseInt(st.nextToken()));
			}
			
			result = 0;
			solution(0);
			sb.append("#"+i+" "+result+"\n");
		}
		System.out.println(sb);
	}
	
	// 칼로리 제한 안에서 가장 맛있는 햄버거를 만들어서 맛 점수를 출력해보자
	// 같은 재료를 여러번 사용할 수 없으므로 (중복이 아닌) 조합문제인가 했지만
	// 뽑는갯수가 정해져있지 않으므로 부분집합 이로군요
	private static void solution(int idx) { //포함한다/안한다 두개만 검사하므로 고려해본 원소의 갯수를 사용
		
		if(idx == N) {
			int sumKcal=0, sumScore=0;
			for(int i=0; i<N; i++) {
				if(check[i]) {
					sumKcal += foodArr[i].kcal;
					sumScore += foodArr[i].score;
				}
			}
			
			if(sumKcal <= maxKcal && sumScore>result) result = sumScore;
			return;
		}
		
		check[idx] = true;
		solution(idx+1);
		
		check[idx] = false;
		solution(idx+1);
		
	}
}
