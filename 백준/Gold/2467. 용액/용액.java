import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 용액
 * 용액의 특성을 정수로 표현한다. 1부터 10억까지가 산성, -1부터 -10억까지가 염기성이다.
 * 같은양의 두 용액을 혼합한 용액은 특성값의 합으로 정의한다. 혼합으로 0에 가까운 용액을 만드려 한다.
 * 두종류의 산성/ 두종류의 알카리성으로만으로도 0에 가장 가까운 경우일수도 있다.
 * 특성값이 정렬된 순서로 주어졌을때, 서로 다른 용액을 혼합해 특성값이 0에 가까운 용액을 만드는 프로그램
 *
 * 풀이
 * 모든 조합에 대한 경우의수를 구하면 시간복잡도 터질거고...
 * 풀이는 두가지가 있음
 * (1) 이진탐색 -> arr[i]를 하나 고르고, Math.abs(arr[i]+또다른수)=0에 가까운 수를 찾는다.
 * (2) 투포인터 -> 투 포인터를 사용해 서로 가운데로 움직이게 하면서 0에 가까운수를 구함
 * 투포인터로 풀어본다...
 * 1. left=0, rught=n-1
 * 2. 합이 0에 가까운 구간을 탐색
 *    - min > Math.abs(arr[left]+arr[right])이면 저장
 *    - sum > 0 합이 0보다 크면 더 작아져야 하므로 오른쪽 포인터를 이동 right--
 *    - sum < 0 합이 0보다 작으면 더 커져야 하므로 왼쪽 포인터를 이동한다 left++
 *    - 같으면 바로 출력
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int left=0, right=N-1, min=Integer.MAX_VALUE;
        int[] resultArr = new int[2];
        //투포인터
        while(left<right) {
            int sum = arr[left]+arr[right];
            if(Math.abs(sum) < min) {
                min = Math.abs(sum);
                resultArr[0] = arr[left];
                resultArr[1] = arr[right];
            }
            if(sum > 0) right--;
            else if(sum < 0) left++;
            else {
                resultArr[0] = arr[left];
                resultArr[1] = arr[right];
                break;
            }
        }
        System.out.println(resultArr[0]+" "+resultArr[1]);
    }
}