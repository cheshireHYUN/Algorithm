import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 세 용액
 * 용액의 특성값이
 * 산성은 1부터 10억까지
 * 알칼리는 -1부터 -10억까지
 * 세가지 용액을 혼합해 0에 가장 가까운 용액을 만든다.
 * - 여러개면 하나만 출력, 특성값은 오름차순으로 출력
 *
 * 풀이
 * 전체 용액의 수 N이 5000까지니까 NC3 = 5000!/3!(4997)! = 208억정도.... 완탐은 안됨
 * 정렬하고 이분탐색
 * 기준점 하나의 값을 지정하고, 남은 두 용액을 투포인터를 통해 선택한다.
 * (주의) 특성값의 합은 int를 넘을 수 있으므로 long으로 선언한다.
 * 1. i = 0~n-3의 구간에서 기준점 arr[i]를 설정한다.
 * 2. left = i+1, right=n-1, i구간 이후로 양끝점에 투포인터를 지정해 세 합이 0에 가까운 지점을 찾는다
 *  2-1. min>Max.abs(sum) 구해둔 최솟값이 현재 sum값보다 더 크면 min값을 갱신
 *  2-2. sum==0 합이 0이라면 더이상 탐색할 필요가 없으므로 값 입력 후 탐색 종료
 *  2-3. sum>0 합이 0보다 크면 오른쪽 포인터를 줄임 right--;
 *  2-4. sum<0 합이 0보다 작으면 왼쪽 포인터를 늘림 left++;
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        int[] result = new int[3];
        long min= Long.MAX_VALUE;
        for(int i=0; i<arr.length-2; i++) {
            int num1 = arr[i];
            int left=i+1, right=N-1;
            while(left<right) {
                int num2 = arr[left];
                int num3 = arr[right];
                long sum = (long)num1 + num2 + num3;
                if(min>Math.abs(sum)) {
                    min = Math.abs(sum);
                    result[0] = num1;
                    result[1] = num2;
                    result[2] = num3;
                }
                if(sum == 0) break;
                if(sum>0) right--;
                if(sum<0) left++;
            }
        }

        Arrays.sort(result);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3; i++) sb.append(result[i]).append(' ');
        System.out.println(sb);
    }
}
