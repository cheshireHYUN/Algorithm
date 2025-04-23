import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 용액 합성하기
 * 용액의 특성값이 -1억 ~ 1억
 * 두 용액 혼합하면 특성값은 두 용액 특성값의 합이 된다
 * 두개의 용액을 혼합해 만들 수 있는 0에 가장 가까운 특성값 B를 출력하라.
 *
 * 풀이
 * 용액문제와 마찬가지로, 투포인터를 사용해서 비교한다
 * min = Integer.MAX_VALUE, left=0, right=N-1
 * (1) 양끝의 특성값을 더한다
 * (2) 특성값 0보다 크면 => 양의정수를 줄여야 하므로 right--
 *     특성값이 0보다 작으면 => 음의정수를 줄여야 하므로 left++
 *     0이면 바로 리턴
 * 범위가 1억인데,,, 두개 합해도 2억아닌가? 그럼 int로 될거같죠?
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE, value = 0, left = 0, right = N-1;
        while(left < right) {
            int sum = arr[left]+arr[right];
            if(sum<0) left++;
            else right--;

            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                value = sum;
                if(min == 0) break;
            }
        }

        System.out.println(value);
    }
}
