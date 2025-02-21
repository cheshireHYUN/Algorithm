import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 합이 0
 * 모든 학생의 코딩실력이 주어질때, 세 팀원의 코딩실력의 합이 0이 되는 팀을 만든다.
 * 이때 그녀가 대회에 출전할 수 있는 팀을 얼마나 많이 만들 수 있는지 계산하라.
 *
 * 풀이
 * 일단 완전탐색.. 그러니까 3개 뽑는 조합으로 구해볼 수 있으나,
 * 시간 복잡도 => C(N,3) => N!/3!*(N-3)! = N*(N-1)*(N-2)/3! => O(N^3)으로, N이 1만이니까 무조건 시간초과
 *
 * 이분탐색(LogN)을 통해서 문제를 해결해보자.
 * (1) 정렬을 하고
 * (2) 세 수의 합이 0인것을 찾는데, 3중포문(완탐)은 결국 N^3이므로.. x+y=-z처럼 2중포문만 돌 수 있도록 변형
 * (3) 첫번째 수와 두번째 수를 선택한뒤 세번째수를 이분탐색으로 찾는다. (여러개일수 있으므로 upper - lower를 한다)
 * 
 * 근데 이러면 N^2으로 1억이 딱 되는데 괜찮나 ㄷㄷ 정확히는 N^2*logN
 * 해쉬맵쓰면 더 빠를거같은데
 * (주의) 1만명중에 3개를 뽑는 경우의수이므로 N^3, 21억의 범위를 한참 넘음 => 결과는 long타입
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
        Arrays.sort(arr);

        long result = 0;
        for(int i=0; i<N-2; i++) {
            for(int j=i+1; j<N-1; j++) {
                int target = (arr[i]+arr[j])*(-1);
                int lower = binarySearch(target,j+1,0);
                int upper = binarySearch(target,j+1,1);
                result += upper-lower;
            }
        }
        System.out.println(result);
    }

    //upper는 초과값. lower는 이상값
    private static int binarySearch(int target, int start, int type) {
        int mid, end = N;
        while(end>start) {
            mid = (end+start)/2;
            if(type==0 && target<=arr[mid]) end = mid; //lower bound, 즉 찾는값이 처음 발생하는 위치
            else if (type==1 && target<arr[mid]) end = mid; //upper bound, 즉 찾는값 초과값이 처음 발생하는 위치
            else start = mid+1;
        }
        return start;
    }
}