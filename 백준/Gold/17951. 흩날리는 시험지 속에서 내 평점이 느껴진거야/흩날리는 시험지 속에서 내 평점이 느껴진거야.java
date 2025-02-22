import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 흩날리는 시험지 속에서 내 평점이 느껴진거야
 * 시험지가 뒤섞여있을때, <현재 순서 그대로> K개 그룹으로 나눈 뒤,
 * 각 그룹에서 맞은 문제 갯수의 합을 구해 그중 최솟값을 시험 점수로 한다.
 * 이번 시험에서 받을 수 있는 최대 점수를 계산해보자.
 * (현수는 모르는 문제를 아예 풀지 않으므로 푼 문제는 모두 맞았다.)
 *
 * 풀이
 * 시험지 갯수가 8개고 그룹의 수가 2개
 * 각 시험지마다 맞은 갯수 : 12 7 19 20 17 14 9 10
 * 순서를 그대로 두면서, 둘로 나누는 모든 경우의 수를 탐색 해야함.
 * 우선 나올 수 있는 점수는 최솟값인 7이상, 108미만에 있을것임.
 * 또한 가장 높은 점수는 그 중앙값에 있을 확률이 높음. 즉 57(중앙값)에서 가장 가까운 합이 나오는 파티셔닝이 정답임.
 * 따라서 7부터 108사이 이분탐색을 돌리면서, 앞에서부터 더해가면서 정해놓은 값보다 커지는 순간 = 파티셔닝한다.
 * (108+7)/2=57이고, 12+7+19+20=58이므로 커지는순간 파티셔닝하면, 108-58=50이므로 최솟값은 50이 됨
 *
 * 다른 예시로, 58 17 14 9 10을 셋으로 나누는 경우
 * 중앙값은 (9+108)/2 = 36 이고,
 * 58에서 넘으니까 파티셔닝 58 // 17 14 9 10
 * 9에서 넘으니까 파티셔닝 58 // 17 14 9 // 10 근데 최적값이 아님! 한번 더 시도해야겠다.(mid값을 줄임)
 *
 * 중앙값은 (9+36)/2 = 22 이고,
 * 58에서 넘으니까 파티셔닝 58 // 17 14 9 10
 * 14에서 넘으니까 파티셔닝 58 // 17 14 // 9 10 // 한번 더 시도 (mid값을 줄임)
 *
 * 중앙값은 (9+22)/2 = 15 이고,
 * 58에서 넘으니까 파티셔닝 58 // 17 14 9 10
 * 17에서 넘으니까 파티셔닝 58 // 17 // 14 9 10
 * 9에서 넘으니까 파티셔닝 58 // 17 // 14 9 // 10 >>파티션 너무 많음. (mid값을 늘림)
 *
 * 중앙값은 (15+22)/2 = 18.5 이고,
 * 58에서 넘으니까 파티셔닝 58 // 17 14 9 10
 * 14에서 넘으니까 파티셔닝 58 // 17 14 // 9 10 //
 * 뭐 이런식으로 반복이겠네~~!
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        int start=Integer.MAX_VALUE, end=0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            end += arr[i];
            start = Math.min(start, arr[i]);
        }

        int ans = 0;
        while(start<=end) {
            int mid = (start+end)/2;
            int count=0, partition=0, min=Integer.MAX_VALUE;
            for(int i=0; i<N; i++) {
                count+=arr[i];
                if(count >= mid) {
                    min = Math.min(min, count);
                    count = 0;
                    partition++;
                }
            }
            if(partition >= K) { //파티션이 많다 = mid값 키운다(최솟값을 크게 만들어야하므로)
                ans = min;
                start = mid+1;
            }else { //파티션이 같거나 적다 = mid값 줄인다
                end = mid-1;
            }
        }

        System.out.println(ans);

    }
}