import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 뒤집기
 * 다솜이는 0과 1로 이루어진 문자열 S를 갖고있고, 모두 같게 할거다.
 * 0001100 => 1
 * 11111 => 0
 * 0000001 => 1
 * 11001100110011000001 => 4
 * 11101101 => 2
 * 풀이 : 결국 더 적은 묶음을 선택하면 되는것이다. 어차피 한번에 뒤집을 수 있으니... 1과 0의 블록 갯수를 세면됨
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        char pre = arr[0];
        int cnt0=0, cnt1=0;

        if(pre == '0') cnt0++;
        else cnt1++;

        for(int i=1; i<arr.length; i++){
            if(arr[i] != arr[i-1]) {
                pre = arr[i];
                if(arr[i] == '0') cnt0++;
                else cnt1++;
            }
        }

        int result = Math.min(cnt0, cnt1);
        System.out.println(result);
    }
}