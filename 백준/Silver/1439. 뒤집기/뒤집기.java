import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

/** 뒤집기
 * 0과 1로만 이뤄진 문자열 S의 숫자를 전부 같게할것임
 * 연속된 하나이상의 숫자를 잡고 모두 뒤집는다. 최소 행동 횟수를 구하라
 * 풀이 : 그리디래요
 * 쉽게 생각해보면... 결국 1100처럼 서로다른 숫자를 묶어서 뒤집으면 결국 현상유지밖에 안됨
 * 그렇다면? 0세트와 1세트중에서 더 작은걸 다 뒤집으면 된다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] arr = new int[str.length()];
        for(int i=0; i<str.length(); i++) arr[i] = Integer.parseInt(str.substring(i,i+1));

        int cnt1 = 0, cnt2 = 0, pre=arr[0];
        for(int i=0; i<arr.length; i++){
            if(i==0 || pre!=arr[i]){
                pre=arr[i];
                if(arr[i] == 1) cnt2++;
                else cnt1++;
            }
        }

        int result = Math.min(cnt1, cnt2);
        System.out.println(result);
    }
}