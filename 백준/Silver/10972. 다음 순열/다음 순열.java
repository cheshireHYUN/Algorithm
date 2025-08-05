import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 다음순열
 * 1부터 N까지 순열이있다. 사전순으로 다음에 오는 순열을 구하는 프로그램을 작성하라.
 * 사전순으로 가장 앞서는 순열은 오름차순으로 이뤄지는 순열이고,
 * 가장 마지막에 오는 순열은 내림차순으로 이뤄지는 순열이다.
 *
 * 제한
 * 1<=N<=10,000 (백트래킹 터짐)
 *
 * 풀이
 * 넥스트퍼뮤테이션
 * - 오름차순에서 내림차순으로 갈수록 뒤인것. 따라서 현재 정렬에서 내림차순인 부분을 찾는다(이미 최대순열, 더이상 큰 순열 불가능)
 * - 감소수열 바로 앞의수(pivot)을 바꿔야 다음 순열로 갈 수 있음(즉 pivot은 감소구간 직전값이자 지금 바꿔야할 최소단위)
 * - 따라서 감소수열중 arr[pivot]보다 큰 수들중에서 가장 작은수를 찾아서 swap해주는것!!
 * - 그리고 나머지를 다시 오름차순 정렬해야 가장 작은값으로 돌아옴
 * 
 * 따라서 다음 순서대로 구현한다
 * (1) for(int i=N; i>0; i--) arr[i-1]<arr[i]을 만족하는 첫 i를 저장 startIndex=i; pivot=(i-1);
 * (2) for(int j=N; j>=startIndex; j--) if(arr[j]>arr[pivot]) minIndex = Math.min(minIndex, arr[j]);
 * (3) swap(arr[minIndex], arr[pivot])
 * (4) Arrays.sort(startIndex ~ N)
 */
public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
        System.out.println(solution());
    }

    private static String solution() {
        //1.가장 긴 감소수열 찾기
        int startIndex=0, pivot=0;
        for(int i=N-1; i>0; i--) {
            if(arr[i-1]<arr[i]) {
                startIndex = i;
                pivot = i-1;
                break;
            }
        }

        //exception : 이미 가장 긴 감소수열일경우
        if(startIndex == 0) return "-1";

        //2.감소수열중 arr[pivot]값보다 큰 수들중 가장 작은 수의 index찾기
        int minIndex = -1, minValue=Integer.MAX_VALUE;
        for(int i=N-1; i>=startIndex; i--) {
            if(arr[i]>arr[pivot]) {
                if(minValue > arr[i]) {
                    minValue = arr[i];
                    minIndex = i;
                }
            }
        }

        //3.pivot값과 minIndex값을 swap하기
        swap(pivot, minIndex);

        //4.기존 감소수열을 오름차순 정렬하기
        Arrays.sort(arr, startIndex, N);

        StringBuilder sb = new StringBuilder();
        for(int a : arr) sb.append(a).append(' ');
        return sb.toString();
    }

    private static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
