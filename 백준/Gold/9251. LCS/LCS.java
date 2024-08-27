import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/** LCS
 * 최장 공통 부분수열 : 두 수열이 주어졌을때 모두의 부분수열이 되는 수열중 가장 긴것
 * 이차원 배열을 만들어 LCS 크기를 저장한다.
 * DP 점화식은
 * map[i][j]에서 arr1[i]==arr2[j]라면 map[i][j] = map[i-1][j-1]+1
 * map[i][j]에서 arr1[i]!=arr2[j]라면 Math.max(map[i][j-1], map[i-1][j])
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();
        int max = 0;
        int[][] map = new int[arr1.length+1][arr2.length+1]; //0행0열은 DP를 위한 패딩값
        for(int i=1; i<=arr1.length; i++){
            for(int j=1; j<=arr2.length; j++){
                if(arr1[i-1]==arr2[j-1]) map[i][j] = map[i-1][j-1]+1;
                else map[i][j] = Math.max(map[i-1][j], map[i][j-1]);
                max = Math.max(max, map[i][j]);
            }
        }
        System.out.println(max);
    }
}
