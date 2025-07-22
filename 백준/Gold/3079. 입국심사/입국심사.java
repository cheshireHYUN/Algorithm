import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 입국심사
 * 총 M명이 입국심사대 N개가 있다.
 * 사람마다 심사에 걸리는 시간이 다르다.
 * k번 심사대에 앉은 심사관이 한명을 심사하는데 드는 시간이 주어진다(Tk)
 * 모든 사람이 심사를 받는데 걸리는 시간의 최솟값?
 *
 * 풀이
 * 기다렸다가 심사받을 수 있으므로(예를들어 비어있는 10초짜리 심사대 VS 2초기다리는 3초짜리 심사대 = 후자선택)
 * 그냥 역으로 매개변수 탐색을 하는것이다.
 * 즉 이분탐색을 통해서 미리 시간을 정해두고, 그안에 처리할 수 있는 인원수를 체크해서 최소시간을 찾는다.
 * (주의)심사시간은 최대 10억, 인원수M명도 최대 10억이므로 한 심사대에서 처리할 최악의 경우 10억x10억..?
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //입국심사대 갯수
        int M = Integer.parseInt(st.nextToken()); //총 인원수
        int[] arr = new int[N]; //각 심사대의 시간
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        long max = (long)M*arr[N-1], min=0, mid, result=0;
        while(min<=max) {
            mid = (min+max)/2;
            //mid안에 입국심사 가능하면 - result 업데이트, max=mid-1
            //mid안에 입국심사 불가능하면 - min=mid+1
            if(isPossible(arr, M, mid)) {
                result = mid;
                max = mid-1;
            } else min=mid+1;
        }
        System.out.println(result);
    }

    private static boolean isPossible(int[] arr, int M, long time) {
        long sum = 0;
        for(int i : arr) {
            sum += time/i;
            if(sum>=M) break;
        }
        return sum>=M;
    }
}
