import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

/** 나무자르기
 * 나무 M미터가 필요하다.
 * 절단기에 높이 H를 지정하면 톱날이 땅으로부터 H미터의 나무를 자른다.
 * 즉 높이가 H보다 큰 나무는 잘리고 낮으면 안잘린다
 * 그리고 자르고 남은 자투리를 가져가는것임
 * 적어도 M미터의 나무를 가져가기 위해 절단기에 설정하는 높이의 최댓값?
 *
 * 제한
 * 1<=N(나무의 수)<=100만
 * 1<=M(가져갈 나무의 길이)<=20억
 * 나무의 높이들의 합은 항상 M보다 크거나 같고, 각 나무는 10억보다 작다.
 *
 * 풀이
 * 적절한 절단기 높이 X값을 이분탐색으로 설정해 정답을 구한다.
 * (1) min=1, max=제일 높은 나무높이로 두고 mid 구한다
 * (2) mid값을 통해 M미터 나무를 가져갈 수 있는지 찾는다.
 *      => 나무높이-X를 했을때 남은길이를 누적함(음수면 0으로 취급)
 * (3) 최소 M미터니까 M미터 이상의 자투리를 만드는 X중 가장 높은 X값
 *      => 즉 X가 M을 초과하는 UpperBound를 찾아 -1한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //나무의 수
        int M = Integer.parseInt(st.nextToken()); //집에 가져갈 나무길이

        st = new StringTokenizer(br.readLine());
        int max = 0;
        int[] treeArr = new int[N];
        for(int i=0; i<N; i++) {
            treeArr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(treeArr[i], max);
        }

        int min = 1;
        while(min<max) {
            int mid = (max+min)/2;
            long returnTree = 0;
            for(int tree : treeArr) returnTree += (Math.max(tree - mid, 0));
            //UpperBound는 초과하는 값을 찾는것!
            //자른 나무의 합계가 M보다 작다 = 기준높이가 높으니 높이를 줄인다 => max=mid;
            //자른 나무의 합계가 M보다 크다 = 기준높이가 낮으니 높이를 키운다 => min=mid-1
            if(returnTree < M) max = mid; //기준높이가 높아서 범위안에 못들어가는 부분으로, mid를 저장
            else min = mid+1;
        }
        //최소 M미터니까 7미터 이상의 자투리를 만드는 X중 가장 높은 X값
        System.out.println(min-1);
    }
}