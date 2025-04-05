import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 두 수의 합
 * 정수배열과 K가 주어졌을때 S의 두 정수합이 K에 가장 가까운 두 정수를 구하세요.
 * 투포인터 알고리즘을 사용해서 구한다. 일단 정렬부터 하고...
 * 정수배열은 모두 다른정수를 갖는게 키이다. 왜냐면 정답만들때 한 요소에 대해 여러짝을 구할 필요가 없기때문,,
 * min보다 절대값이 작으면 min=절댓값, cnt=1로 갱신
 * min보과 절대값이 같으면 cnt++
 * min보다 절대값이 크면 ...
 * 공통 : 두수합이 K보다 작으면 left++ 크면 right--
 * (예를들어 (-7,12)로인해 min=5,cnt=1일때 다음을 구하려면 (-4,12)또는(-7,9)를 봐야함.
 * 근데 K에 가까운걸 구하는중이니까, 두값의합(12-7)이 K(4)보다 크면 더 작은 결과를 원하니까 right--가 됨.)
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
            sb.append(getCount(k,arr)).append('\n');
        }
        System.out.print(sb);
    }

    private static int getCount(int k, int[] arr) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1, cnt=0, min=Integer.MAX_VALUE;
        while(left<right) {
            int sum = arr[left] + arr[right];
            if(min == Math.abs(sum-k)) {
                cnt++;
            }else if(min > Math.abs(sum-k)) {
                min = Math.abs(sum-k);
                cnt = 1;
            }

            if(sum>k) right--;
            else left++;
        }
        return cnt;
    }
}