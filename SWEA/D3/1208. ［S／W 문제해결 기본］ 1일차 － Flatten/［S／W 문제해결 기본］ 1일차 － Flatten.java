import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 한쪽 벽면에 노란색 상자가 쌓여있다. 높은곳의 상자를 낮은곳으로 옮기는 방식으로 높이차를 줄이는 평탄화를 한다.
 * 모두 수행하고나면 가장 높은곳 낮은곳 차가 최대 1이내가 된다.
 * 평탄화를 통해 상자를 옮기는 횟수에 제한이 있을때 제한횟수만큼 옮긴 뒤 최고점과 최저점 차이를 반환해라.
 * 
 * 가장 높은곳 -> 낮은곳 옮기기 : 덤프, 덤프횟수를 정해줌
 *
 */
public class Solution {
	
	public static void main(String[] args) throws IOException {

		//System.setIn(new FileInputStream("src/test/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=1; i<=10; i++) {
			int dump = Integer.parseInt(br.readLine());
			
			int[] arr = new int[100];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<100; j++) arr[j] = Integer.parseInt(st.nextToken());
			
			System.out.println("#"+i+" "+recursive(dump,arr));
		}

	}

	// dump만큼 반복 - 가장높은부분-1 가장낮은부분+1 해준다. - 재귀
	private static int recursive(int dump, int[] arr) {
		
		// 1. 현재 arr에서 가장 높은 idx와 가장 낮은 idx를 찾는다
		int maxIdx = 0;
		int minIdx = 0;
		for(int i=0; i<100; i++) {
			if(arr[i]>arr[maxIdx]) maxIdx = i;
			else if(arr[i]<arr[minIdx]) minIdx = i;
		}
		
		// 4. dump값이 0이되면 더이상 덤프 불가하므로 값을 뱉고 리턴한다.
		if(dump==0) {
			return arr[maxIdx]-arr[minIdx];
		}
		
		// 2. 덤프해준값으로 arr을 변경해준다
		arr[maxIdx] -= 1;
		arr[minIdx] += 1;
		
		// 3. 재귀함수 실행
		return recursive(dump-1, arr);
		
		
	}

}