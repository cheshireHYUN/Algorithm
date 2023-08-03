import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*** 요리사
 * 두명의 손님에게 음식을 제공한다. 둘은 최대한 비슷한 맛의 음식을 만들어야한다.
 * N개의 식재료가 있고, 이를 반으로 나눠 두개의요리를 만든다.
 * A음식과 B은식의 맛의 차이가 최소가 되도록 재료를 배분하라.식재료의 조합에따라 시너지가 발생한다.
 * 식재료 i와 싣재료 j를 같이요리하면 주어지는 시너지 Sij가 주어진다.
 * 
 * 두 음식간의 맛차이가 최소가 되는 경우를 찾고 그 최솟값을 정답으로 출력하라!
 */
public class Solution {
	static int[][] map;
	static int[] select;
	static boolean[] isFoodA;
	static int N,result;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("src/algo0823/input_4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int j=0; j<N; j++) {
				StringTokenizer tk = new StringTokenizer(br.readLine());
				for(int k=0; k<N; k++) {
					map[j][k] = Integer.parseInt(tk.nextToken());
				}
			}
			
			select = new int[N/2];
			isFoodA = new boolean[N];
			result = Integer.MAX_VALUE;
			solution(0,0);
			sb.append("#"+i+" "+result+"\n");
		}
		System.out.println(sb);
	}
	
	//조합문제인듯?
	//식재료가 N개, 즉 4개일땐 N/2개, 즉 2개씩 재료를 할당한다.
	//예를들면 (1,2)조합과 (3,4)조합을 만들어 각자 계산해서 뺀다.
	private static void solution(int cnt, int idx) { //선택된 갯수(=선택인덱스), 검색 시작인덱스
		if(cnt == N/2) {
			int s1 = 0, s2=0;
			int[] aIndex = new int[N/2];
			int[] bIndex = new int[N/2];
			for(int i=0, a=0, b=0; i<isFoodA.length; i++) {
				if(isFoodA[i]) aIndex[a++]=i;
				else bIndex[b++]=i;
			}
			
			// 각 인덱스를 이용해서 시너지를 구해준다
			for(int i=0; i<aIndex.length; i++) {
				for(int j=i+1; j<aIndex.length; j++) {
					s1 += (map[aIndex[i]][aIndex[j]]+map[aIndex[j]][aIndex[i]]);
				}
			}
			for(int i=0; i<bIndex.length; i++) {
				for(int j=i+1; j<bIndex.length; j++) {
					s2 += (map[bIndex[i]][bIndex[j]]+map[bIndex[j]][bIndex[i]]);
				}
			}
			result = Math.min(result, Math.abs(s1-s2));
			return;
		}
		for(int i=idx; i<N; i++) {
			isFoodA[i] = true;
			solution(cnt+1, i+1);
			isFoodA[i] = false;
		}
		
	}

}