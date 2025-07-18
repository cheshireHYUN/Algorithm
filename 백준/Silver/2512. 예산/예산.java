import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 예산
 * 정해진 총액 이하에서 가능한 최대의 예산을 배정한다.
 * 모든 요청이 배정될수있으며 그대로 배정, 모든 요청 불가능하면 특정 정수 상한액을 계산해 그이상의 요청엔 상한액이하엔 그대로 배정
 * 위 조건을 만족하도록 하는 프로그램에서 배정된 예상들 중 최댓값인 정수를 출력한다.
 *
 * 풀이
 * 일단 총합은 long으로 선언해야될거고
 * 이분탐색의 전형 문제로... 인풋 받을때 합도 구해서 총액보다 크면 이분탐색을 진행함
 * 1. max=최대, min=0으로 정하고 mid 계산
 * 2. mid에 대해서 총합을 구함(메소드로 따로 빼기)
 * 3. 총합>예산, mid(상한)를 더 줄여야함 -> max=mid-1
 *    총합<=예산, mid를 더 키워도 괜찮음 -> min=mid+1
 *    이를 while(min<=max)일때까지 반복
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int min=0, max=0;
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        int M = Integer.parseInt(br.readLine());

        int mid=0, answer=0;
        while(min<=max) {
            mid = (min+max)/2;
            long sum = calculate(mid, arr);
            if(sum > M) max = mid-1;
            else {
                answer = mid;
                min = mid+1;
            }
        }

        System.out.println(answer);
    }

    private static long calculate(int mid, int[] arr) {
        long sum = 0;
        for(int a : arr) sum += Math.min(mid, a);
        return sum;
    }
}
