import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 부분합
 * 1000 이하의 자연수로 이뤄진 길이 N짜리 수열이 주어진다.
 * 수열에서 연속된 수들의 부분합중 그 합이 S 이상이 되면서 가장 짧은것의 길이를 구하라.
 *
 * 풀이
 * 투포인터로 목표값까지 right++, 넘으면 left++ 반복하며 끝까지 계산하면 될득
 * (주ㅠ의) S그자체가 아닌 S이상인것!
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());


        int left=0, right=0, sum=arr[left], answer=Integer.MAX_VALUE;
        while(true) {
            if(sum >= target) answer = Math.min(answer, right-left+1);

            if(sum < target) {
                if(right == N-1) break;
                sum+=arr[++right];
            } else {
                sum-=arr[left++];
            }
        }

        if(answer == Integer.MAX_VALUE) answer=0;
        System.out.println(answer);

    }
}
