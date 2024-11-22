import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 저울
 * 양 팔 끝에는 물건이나 추를 올려두는 접시가 달려있고, 양팔의 길이가 같다.
 * 저울의 한쪽에는 저울추만 놓을수있고, 다른쪽엔 물건만 올려둘 수 있다.
 * 무게가 양의정수인 N개의 저울 추가 주어질때 측정할 수 없는 양의 정수무게중
 * 최솟값을 구하는 프로그램을 작성하라.
 *
 * 제한
 * 1<=N<=100
 * 1<=무게<=100만
 *
 * 풀이
 * 누적합으로 푼다.
 * 3 1 6 2 7 30 1을 정렬하면
 * 1 1 2 3 6 7 30
 * 1번째 추까지 만들 수 있는 새로운 무게는(1) 1
 * 2번째 추까지 만들 수 있는 새로운 무게는(1) 2
 * 3번째 추까지 만들 수 이쓴 새로운 무게는(2) 3,4
 * 4번째 추까지 만들 수 있는 새로운 무게는(3) 5,6,7
 * 5번째 추까지 만들 수 있는 새로운 무게는(6) 8,9,10,11,12,13
 * 6번째 추까지 만들 수 있는 새로운 무게는(7) 14,15,16,17,18,19,20
 * 7번째 추까지 만들 수 있는 새로운 무게는(30) 31 ..
 * 따라서 정답은 21
 *
 * 규칙을 찾아보면....
 * arr =    [1,1,2,3, 6, 7,30]
 * result = [1,2,4,7,13,20,31] 인것에 착안하여
 * 현재 측정이 가능한 무게+1과 다음 추의 무게를 비교했을때, 다음추가 더 크면 측정불가
 * 즉, 1kg까지 측정이 가능할때, 1+1은 다음 추인 2와 같으므로 측정가능
 * 또한 4kg까지 측정이 가능할때, 4+1은 다음 추인 3kg보다 크므로 측정 가능
 * 그러나 20kg까지 측정이 가능할때 20+1은 다음 추인 31보다 작으므로 측정 불가
 *
 * 만약 주어진 추가 3부터 시작하면
 * arr =      [3, 4, 5]
 * result =   [3, ]
 * 앞에 패딩줘서 0에서부터 시작하면 깔끔한 코드가 될듯
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[N];
        for(int i=0; i<N; i++) numbers[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(numbers);

        int sum = 0;
        for(int i=0; i<N; i++) {
            if(sum+1 < numbers[i]) {
                break;
            }
            sum += numbers[i];
        }

        System.out.println(sum+1);
    }
}