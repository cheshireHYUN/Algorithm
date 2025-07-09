import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/** LCS 2
 * 두 수열이 주어졌을때 모두의 부분수열이 되는 수열중 가장 긴 것을 찾아라.
 *
 * 풀이
 * 그냥 LCS 이차원 배열우선 채웠을때 규칙은 다음과같이 정리할 수 있다
 * (1) 같은 문자열일때 : 현재dp에 들어갈 값은 대각선 왼쪽위의 값+1이다.
 * (2) 다른 문자열일떄 : 현재dp에 들어갈 값은 왼쪽과 위쪽값중 더 큰값이다.
 * 결국 (1)번이 발생시키는 값이 LCS길이가 될텐데, 이 값이 계산되는 지점을 역추적하면 LCS를 구할 수 있게 된다.
 * (dp를 완성시킨 뒤 역추적해야하는데, 그 이유는 dp채우는 분기문에선 결국 이전경로를 몰라서, 가져온 대각선값의 경로를 모르므로)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] dp = new int[str1.length()+1][str2.length()+1];

        for(int i=1; i<str1.length()+1; i++) {
            for(int j=1; j<str2.length()+1; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        String result = getLcs(dp, str1, str2).toString();
        if(result.isEmpty()) System.out.println(0);
        else {
            System.out.println(result.length());
            System.out.println(result);
        }
    }

    //역추적 : 백트래킹 - 대각선에서 온 값(=왼쪽위+1)인지 확인한다.
    private static StringBuilder getLcs(int[][] dp, String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        int i=str1.length(), j=str2.length();
        while(i>0 && j>0) {
            //위쪽에서 왔다면 윗쪽 따라가고, 왼쪽이면 왼쪽 따라가고...
            if(dp[i][j] == dp[i-1][j]) i--;
            else if(dp[i][j] == dp[i][j-1]) j--;
            else {
                //대각선에서 온거라면 LCS에 해당하므로 추가
                sb.append(str1.charAt(i-1)); //dp는 1부터 시작했으므로 -1 index 해줘야 정상char
                i--;
                j--;
            }
        }

        return sb.reverse();
    }
}
