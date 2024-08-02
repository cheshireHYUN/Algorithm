import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 뒤집기
 * 0001100은 010과 같고, 0과1중 더 작은것의 갯수만큼만 결과가 나오면됨
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        int cnt0=0, cnt1=0;
        char pre=arr[0];
        if(pre=='0') cnt0++;
        else cnt1++;

        for(int i=1; i<arr.length; i++){
            if(pre!=arr[i]){
                pre = arr[i];
                if(pre=='0') cnt0++;
                else cnt1++;
            }
        }

        int result = Math.min(cnt1, cnt0);
        System.out.println(result);

    }
}